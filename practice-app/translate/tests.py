from django.test import TestCase
from django.urls import reverse
from django.http import JsonResponse

import json

from .models import Language


def add_languages():
    Language.objects.create(code="en", name="English")
    Language.objects.create(code="fr", name="French")
    Language.objects.create(code="tr", name="Turkish")
    Language.objects.create(code="de", name="German")
    return Language.objects.all()


class TranslateJSONTests(TestCase):
    def test_content_type(self):
        """
        when json request is made, json response returns
        """
        response = self.client.post(reverse('translate:index'), content_type='application/json', data={})
        self.assertEqual(response.status_code, 400)
        # 400 since missing all params
        self.assertIsInstance(response, JsonResponse)

    def test_successful_translation(self):
        """
        successful request is responded with successful translation key
        """
        add_languages()
        en = Language.objects.get(code='en')
        fr = Language.objects.get(code='fr')
        translation = fr.translate(en, 'To be or not to be')
        expected = {
            "errors": [],
            "from": "en",
            "to": "fr",
            "source": "To be or not to be",
            "translation": translation
        }
        response = self.client.post(reverse('translate:index'), content_type='application/json', data={
            'from': 'en',
            'to': 'fr',
            'source': 'To be or not to be'
        })
        self.assertEqual(response.status_code, 200)
        self.assertJSONEqual(response.content, expected)


class TranslateViewTests(TestCase):
    def test_language_list(self):
        """
        added languages are found in page
        """
        languages = add_languages()
        response = self.client.get(reverse('translate:index'))
        self.assertEqual(response.status_code, 200)
        self.assertEqual(str(response.context["languages"]), str(languages))
        for lang in languages:
            self.assertContains(response, lang.name, count=2)
            # count 2: (1 for from +1 for to lang)

    def test_submit_without_to_choice(self):
        """
        If no 'to language' is selected, an appropriate message is displayed
        """
        add_languages()
        response = self.client.post(reverse('translate:index'), data={
            'from': 'en',
            'source': 'Welcome'
        })
        self.assertContains(response, "Please select a language to translate to.", status_code=400)

    def test_submit_without_from_choice(self):
        """
        If no 'from language' is selected, an appropriate message is displayed
        """
        add_languages()
        response = self.client.post(reverse('translate:index'), data={
            'to': 'de',
            'source': 'Welcome'
        })
        self.assertContains(response, "Please select a language to translate from.", status_code=400)

    def test_submit_without_text(self):
        """
        If no 'source text' is given, an appropriate message is displayed
        """
        add_languages()
        response = self.client.post(reverse('translate:index'), data={
            'from': 'en',
            'to': 'de'
        })
        self.assertContains(response, "Please enter a text to translate.", status_code=400)

    def test_submit_with_unknown_from_language(self):
        """
        If an unknown 'from language' is selected, an appropriate message is displayed
        """
        add_languages()
        response = self.client.post(reverse('translate:index'), data={
            'from': 'xx',
            'to': 'de',
            'source': 'Welcome'
        })
        self.assertContains(response, "Defaulting from language to empty.", status_code=400)
        self.assertEqual(response.context["from"], None)

    def test_submit_with_unknown_to_language(self):
        """
        If an unknown 'to language' is selected, an appropriate message is displayed
        """
        add_languages()
        response = self.client.post(reverse('translate:index'), data={
            'from': 'en',
            'to': 'xx',
            'source': 'Welcome'
        })
        self.assertContains(response, "Defaulting to language to empty.", status_code=400)
        self.assertEqual(response.context["to"], None)

    def test_submit_with_same_from_to_language(self):
        """
        If from and to languages are chosen as same, an appropriate message is displayed
        """
        add_languages()
        response = self.client.post(reverse('translate:index'), data={
            'from': 'en',
            'to': 'en',
            'source': 'Welcome'
        })
        self.assertContains(response, "From language &amp; To language parts cannot be the same.", status_code=400)

    def test_display_translated_text(self):
        """
        Successfully translated text is shown in the page
        """
        add_languages()
        en = Language.objects.get(code="en")
        de = Language.objects.get(code="de")
        translation = de.translate(en, "Welcome")
        response = self.client.post(reverse('translate:index'), data={
            'from': 'en',
            'to': 'de',
            'source': 'Welcome'
        })
        self.assertContains(response, translation, status_code=200)
        self.assertEqual(response.context["translation"], translation)


class LanguageModelTests(TestCase):
    def translate_hello(self):
        """
        should translate "Hello" to "Merhaba", "Hallo" and "Bonjour"
        """
        expected = {
            "en": "Hello",
            "fr": "Bonjour",
            "tr": "Merhaba",
            "de": "Hallo"
        }
        languages = add_languages()
        for from_lang in languages:
            for to_lang in languages:
                if not from_lang == to_lang:
                    source = expected[from_lang.code]
                    translation = to_lang.translate(from_lang, source)
                    expected_translation = expected[to_lang.code]
                    self.assertIs(expected_translation, translation, "From({}) {} => To({}) {} <= Expected {}"
                                  .format(from_lang, source, to_lang, translation, expected_translation))
