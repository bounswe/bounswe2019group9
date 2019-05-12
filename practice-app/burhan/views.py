from django.conf import settings
from django.shortcuts import render
from django.conf import settings
#from rest_framework import status
#from rest_framework.response import Response
import json
import requests
import random
import string
#import sys
from django.http import JsonResponse, HttpResponse

def language(request, ip):
	
	access_key=settings.burhan.API_KEY
	response = requests.get('http://api.ipstack.com/'+ip+'?access_key='+access_key+'&output=json')
	data = response.json()
	return JsonResponse({'ip': data['ip'],'Language': data['location']['languages'][0]['name']})

	