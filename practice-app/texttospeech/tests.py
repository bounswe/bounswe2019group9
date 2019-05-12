from django.test import TestCase
from django.urls import reverse

from django.conf import settings
# Create your tests here.

class Tests(TestCase):
    def test_en(self):
        response = self.client.get(reverse('texttospeech:texttospeech', args=("en","Hello")))
        self.assertEqual(response.status_code,200)
        self.assertEqual(response['content-type'],'audio/wav')
        

    def test_fr(self):
        response = self.client.get(reverse('texttospeech:texttospeech', args=("fr","Bonjour")))
        self.assertEqual(response.status_code,200)
        self.assertEqual(response['content-type'],'audio/wav')

    def test_esp(self):
        response = self.client.get(reverse('texttospeech:texttospeech', args=("es","Hola")))
        self.assertEqual(response.status_code,200)
        self.assertEqual(response['content-type'],'audio/wav')
