from django.test import TestCase
from django.urls import reverse
#from six import string_types

class Tests(TestCase):
    def test_en(self):
        response = self.client.get(reverse("arda:multimedia_recommendation", args=("en",)))
        data = response.json()
        #year = data['movie_year']
        year = data.get('movie_year')
        name = data['movie_name']
        plot = data['movie_plot']
        self.assertEqual(response.status_code, 200)

        self.assertIs(isinstance(plot, str), True)
        if year:
            self.assertIs(isinstance(year, str), True)
            self.assertIs(1700<=int(year[0:4]) and int(year[0:4])<=2223, True)
        self.assertIs(isinstance(name, str), True)

    def test_de(self):
        response = self.client.get(reverse("arda:multimedia_recommendation", args=("en",)))
        data = response.json()
        #year = data['movie_year']
        year = data.get('movie_year')
        name = data['movie_name']
        plot = data['movie_plot']
        self.assertEqual(response.status_code, 200)

        self.assertIs(isinstance(plot, str), True)
        if year:
            self.assertIs(isinstance(year, str), True)
            self.assertIs(1700<=int(year[0:4]) and int(year[0:4])<=2223, True)
        self.assertIs(isinstance(name, str), True)

    def test_tr(self):
        response = self.client.get(reverse("arda:multimedia_recommendation", args=("tr",)))
        data = response.json()
        #year = data['movie_year']
        year = data.get('movie_year')
        name = data['movie_name']
        plot = data['movie_plot']
        self.assertEqual(response.status_code, 200)

        self.assertIs(isinstance(plot, str), True)
        if year:
            self.assertIs(isinstance(year, str), True)
            self.assertIs(1700<=int(year[0:4]) and int(year[0:4])<=2223, True)
        self.assertIs(isinstance(name, str), True)

    def test_undef(self):
        response = self.client.get(reverse("arda:multimedia_recommendation", args=("laga",)))
        data = response.json()
        year = data['movie_year']
        name = data['movie_name']
        plot = data['movie_plot']
        self.assertEqual(response.status_code, 200)
        self.assertEqual(name, 'unsupported language')
