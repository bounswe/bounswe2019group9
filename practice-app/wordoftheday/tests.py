from django.test import TestCase, Client, RequestFactory
from .views import index
from django.urls import reverse


class SimpleTest(TestCase):

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
    def test_something_that_will_pass(self):
        self.assertFalse(False)
'''