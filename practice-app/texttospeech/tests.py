from django.test import TestCase
from django.urls import reverse

from django.conf import settings
# Create your tests here.

class Tests(TestCase):
    def test_en(self):
        response = self.client.get(reverse('texttospeech:texttospeech', args=("en","Hello")))
        result = response.json()
        expectedUrl = 'http://api.voicerss.org/?key='+settings.TTS_API_KEY+'&hl=en-us&src=Hello'
        self.assertEqual(result['audioUrl'], expectedUrl)
        

    def test_fr(self):
        response = self.client.get(reverse('texttospeech:texttospeech', args=("fr","Bonjour")))
        result = response.json()
        expectedUrl = 'http://api.voicerss.org/?key='+settings.TTS_API_KEY+'&hl=fr-fr&src=Bonjour'
        self.assertEqual(result['audioUrl'], expectedUrl)

    def test_esp(self):
        response = self.client.get(reverse('texttospeech:texttospeech', args=("es","Hola")))
        result = response.json()
        expectedUrl = 'http://api.voicerss.org/?key='+settings.TTS_API_KEY+'&hl=es-es&src=Hola'
        self.assertEqual(result['audioUrl'], expectedUrl)
