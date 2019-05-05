from django.test import TestCase

# Create your tests here.

from .detection import getresponse
import json

class API(TestCase):

	def is_expected(self, input_str, expected):
		response = getresponse(input_str)
		response_dict = json.loads(response)
		self.assertEqual(expected, response_dict)


	def test_with_english(self):

		expected_response = {"success":True,"results":[{"language_code":"en","language_name":"English","probability":26.034473983937758,"percentage":100,"reliable_result":True}]}

		self.is_expected("I like eating apples while I am reading a book.",
			expected_response)

	def test_with_spanish(self):

		expected_response = {"success":True,"results":[{"language_code":"es","language_name":"Spanish","probability":39.57666261978607,"percentage":100,"reliable_result":True}]}

		self.is_expected("Sus condenas largas y absurdas fueron fruto de la venganza y la mentira.",
			expected_response)

	def test_with_german(self):

		expected_response = {"success":True,"results":[{"language_code":"de","language_name":"German","probability":27.5519990100764,"percentage":100,"reliable_result":False}]}

		self.is_expected("Mein name ist Ibrahim und ich bin fünfzehn jahre alt.",
			expected_response)