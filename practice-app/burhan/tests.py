from django.test import TestCase
from django.urls import reverse

from .views import language
import json

class Tests(TestCase):
	def test(self):
		response = self.client.get(reverse("burhan:language", args=("134.205.255.164",)))
		data = response.json()
		ip = data['ip']
		language = data['Language']
		self.assertEqual(response.status_code, 200)
		self.assertEqual(language,"English")
