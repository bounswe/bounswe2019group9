from django.test import TestCase
from django.urls import reverse
#from six import string_types

class Tests(TestCase):
    def test_en(self):
        response = self.client.get(reverse("arda:multimedia_recommendation", args=("en",)))
        data = response.json()
        year = data['movie_year']
        name = data['movie_name']
        self.assertEqual(response.status_code, 200)
        self.assertIs(1980<=int(year) and int(year)<=2019, True)
        self.assertIs(isinstance(name, str), True)

    """def test_de(self):
        response = self.client.get(reverse("arda:multimedia_recommendation", args=("de",)))
        data = response.json()
        year = data['movie_year']
        name = data['movie_name']
        self.assertEqual(response.status_code, 200)
        self.assertIs(1980<=int(year) and int(year)<=2019, True)
        self.assertIs(isinstance(name, str), True)"""

    """def test_tr(self):
        response = self.client.get('http://127.0.0.1:8000/rec/tr')
        year = response['movie_year']
        name = response['movie_name']
        self.assertEqual(response.status_code, 200)
        self.assertIs(1980<=int(year) and int(year)<=2019, True)
        self.assertIs(isinstance(name, basestring), True)"""

    def test_undef(self):
        response = self.client.get(reverse("arda:multimedia_recommendation", args=("laga",)))
        self.assertEqual(response.status_code, 404)

"""from views.py import multimedia_recommendation

# Create your tests here.

class RecTests(TestCase):

    def assertRange(year):

        self.assertIs(1980<=int(year) && int(year)<=2019, True)

    def assertIsString(name):
        isinstance(name, basestring)
"""
