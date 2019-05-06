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

def multimedia_recommendation(request, language):

    try:
        if language =='en':
            language_long = 'English'
        elif language =='de':
            language_long = 'German'
        elif language == 'tr':
            language_long = 'Turkish'
        else:
            #content = {'UNDEFINED LANGUAGE': 'UNDEFINED LANGUAGE'}
            return JsonResponse({'status':'false','message':'UNDEFINED LANGUAGE'}, status=404)

        letters = string.ascii_lowercase

        while True:
            response = requests.get('http://www.omdbapi.com/?t='+random.choice(letters)+'&y='+str(random.randint(0,39)+1980)+'&plot=full&apikey='+settings.OMDB_KEY)
            movie_data = response.json()
            if language_long in movie_data['Language']:
                break
        return JsonResponse({'movie_name': movie_data['Title'],'movie_year': movie_data['Year']})

    except:
        return JsonResponse({'status':'false','message':'UNDEFINED ERROR'}, status=404)
