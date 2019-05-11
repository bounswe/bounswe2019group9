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
            return JsonResponse({'status':'false','message':'UNDEFINED LANGUAGE'}, status=404)


        doc = requests.get('http://api.voicerss.org/?key='+settings.TTS_API_KEY+'&hl='+language+'&src='+text)
        with open('speech.wav', 'wb') as f:
            f.write(doc.content)
    
        response = HttpResponse(doc.content,content_type='audio/wav')
        return response
        
    except:
        return JsonResponse({'status':'false','message':'UNDEFINED ERROR'}, status=404)

