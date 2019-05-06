from django.db import models

from django.conf import settings
import requests, uuid, json


# Create your models here.
class Language(models.Model):
    name = models.CharField(max_length=40)
    code = models.CharField(max_length=2)

    def __str__(self):
        return "{} ({})".format(self.name, self.code)

    def __eq__(self, other):
        return self and other and self.code == other.code

    def toJSON(self):
        return self.code

    def translate(self, from_language, text):
        url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&textType=plain&to={}&from={}" \
            .format(self.code, from_language.code)
        headers = {
            'Ocp-Apim-Subscription-Key': settings.AZURE_TRANSLATOR_API_KEY,
            'Ocp-Apim-Subscription-Region': settings.AZURE_TRANSLATOR_REGION,
            'Content-type': 'application/json',
            'X-ClientTraceId': str(uuid.uuid4())
        }
        request = requests.post(url, headers=headers, json=[{"text": text}])
        response = request.json()
        return response[0]["translations"][0]["text"]
        # https://docs.microsoft.com/en-us/azure/cognitive-services/translator/quickstart-python-translate
