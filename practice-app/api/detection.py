import requests
import json
from django.conf import settings
from urllib.parse import urlencode


def getresponse(text, api_endpoint=settings.DETECT_API_URL):
	if text == "":
		return ""

	#file = open("secret.json")
	#access_key = json.load(file)
	url = api_endpoint+ "?access_key=" + settings.DETECT_API_KEY +"&query="  + text

	response = requests.get(url)
	return response.text