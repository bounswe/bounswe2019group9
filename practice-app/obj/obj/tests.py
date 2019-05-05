from django.test import TestCase

# Create your tests here.
from django.urls import reverse
from rest_framework.views import status
import json
# tests for views
from django.test import TestCase, Client, RequestFactory
from .views import object_detection
from django.http import JsonResponse, HttpResponse

class SimpleTest(TestCase):
	def test_obj_recognition(self):
		picture_url = 'https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/640px-IBM_VGA_90X8941_on_PS55.jpg'
		request = RequestFactory().get('/obj')
		rr = object_detection(request, picture_url)
		self.assertEqual(rr.status_code,200)
