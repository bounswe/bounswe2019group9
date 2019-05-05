from django.shortcuts import render
from rest_framework.response import Response
import json
from ibm_watson import VisualRecognitionV3
from rest_framework.views import status
from django.http import JsonResponse, HttpResponse
from django.conf import settings


def object_detection(request, picture_url):
	visual_recognition = VisualRecognitionV3('2018-03-19',iam_apikey = settings.IBM_API_KEY)
	classes_result = visual_recognition.classify(url = picture_url).get_result()
	return JsonResponse(classes_result)
