class Tests(TestCase):
    def test_en(self):
        response = self.client.get(reverse("burhan:language", args=("134.201.250.155",)))
        data = response.json()
        ip = data['ip']
        language = data['location']['languages'][0]['native']
        self.assertEqual(response.status_code, 200)
        self.assertIs(language="English", True)
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