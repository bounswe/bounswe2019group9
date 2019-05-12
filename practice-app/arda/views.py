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
        letters = string.ascii_lowercase
        uncommon_language_words = { 'Turkish': ['bir'], 'German': ['aus','mit','auf','und','zur'] }

        if language =='en':
            language_long = 'English'
        elif language =='de':
            language_long = 'German'
        elif language == 'tr':
            language_long = 'Turkish'
        else:
            return JsonResponse({'movie_name': 'unsupported language','movie_year': 'unsupported language', 'movie_plot': 'unsupported language'})

        if language =='en':
            for i in range(100):
                response = requests.get('http://www.omdbapi.com/?t='+random.choice(letters)+'&y='+str(random.randint(0,39)+1980)+'&type=movie'+'&plot=full&apikey='+settings.OMDB_KEY)
                movie_data = response.json()
                if language_long in movie_data['Language']:
                    break

        elif language !='en':
            str_to_search = random.choice(uncommon_language_words[language_long])
            response = requests.get('http://www.omdbapi.com/?apikey='+settings.OMDB_KEY+'&s='+str_to_search+'&page=1')
            search_result = response.json()
            page_number = int(search_result['totalResults'])//10
            flag=0
            for k in range(1,page_number+1):
                if flag==1:
                    break
                random_page=random.randint(1,page_number)
                response = requests.get('http://www.omdbapi.com/?apikey='+settings.OMDB_KEY+'&s='+str_to_search+'&page='+str(random_page))
                search_result = response.json()
                for j in range(1,11):
                    imdb_id = search_result['Search'][random.randint(1,10)]['imdbID']
                    response = requests.get('http://www.omdbapi.com/?i='+imdb_id+'&apikey='+settings.OMDB_KEY)
                    movie_data = response.json()
                    if language_long in movie_data['Language']:
                        flag=1
                        break

        return JsonResponse({'movie_name': movie_data['Title'],'movie_year': movie_data['Year'], 'movie_plot': movie_data['Plot']})

    except:
        return JsonResponse({'movie_name': 'ERROR','movie_year': 'ERROR', 'movie_plot': 'ERROR'}, status=404)
