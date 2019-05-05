from django.shortcuts import render

# Create your views here.
import json

from django.http import JsonResponse, HttpResponse

from .detection import getresponse

def output(request, in_text):
	response = getresponse(in_text)
	res = json.loads(response)
	return JsonResponse({
		"language_code": res['results'][0]['language_code'],
		"language_name": res['results'][0]['language_name'],
		"percentage" : res['results'][0]['percentage'],
		"reliable_result": res['results'][0]['reliable_result']
	})

