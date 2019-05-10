from django.urls import path

from . import views

app_name = 'texttospeech'

urlpatterns = [
    path('<lang>/<text>', views.texttospeech, name='texttospeech'),
]
