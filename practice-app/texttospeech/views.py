from django.shortcuts import render
from django.conf import settings
from django.http import HttpResponse, JsonResponse

from django.conf import settings



import json
import requests

def texttospeech(request, lang, text):
    try:
        if lang =='en':
            language = 'en-us'
        elif lang =='fr':
            language = 'fr-fr'
        elif lang == 'es':
            language = 'es-es'
        else:
            #content = {'UNDEFINED LANGUAGE': 'UNDEFINED LANGUAGE'}
            return JsonResponse({'status':'false','message':'UNDEFINED LANGUAGE'}, status=404)
        
        return JsonResponse({'audioUrl':'http://api.voicerss.org/?key='+settings.TTS_API_KEY+'&hl='+language+'&src='+text})
        
    except:
        return JsonResponse({'status':'false','message':'UNDEFINED ERROR'}, status=404)
