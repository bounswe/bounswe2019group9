from django.test import TestCase, Client, RequestFactory
from .views import index
from django.urls import reverse


class SimpleTest(TestCase):

    def test_response_status(self):
        request_en = RequestFactory().get('/en')
        request_tr = RequestFactory().get('/tr')
        request_aq = RequestFactory().get('/aq')
        self.assertEqual(index(request_en, 'en').status_code, 200)
        self.assertEqual(index(request_tr, 'tr').status_code, 200)
        self.assertEqual(index(request_aq, 'aq').status_code, 200)


    # I commented these tests since it would be a problem for us and will not be a useful test;
    # because the word of the day will change everyday.

'''
    def test_English(self):
        response = self.client.get(reverse("wordoftheday:index", args=("en",)))
        word_returned = response.json()['word']
        definition_returned = response.json()['definition']
        request = RequestFactory().get('/en')
        return_value = index(request, 'en')
        self.assertEqual(return_value.status_code, 200)
        self.assertEqual(word_returned, 'Purple')
        self.assertEqual(definition_returned, 'A colour intermediate between red and blue.')

    def test_Turkish(self):
        response = self.client.get(reverse("wordoftheday:index", args=("tr",)))
        word_returned = response.json()['word']
        definition_returned = response.json()['definition']
        request = RequestFactory().get('/tr')
        return_value = index(request, 'tr')
        self.assertEqual(return_value.status_code, 200)
        self.assertEqual(word_returned, 'Kırmızıbiber')
        self.assertEqual(definition_returned, 'Patlıcangillerden bir tür biber.')

'''
