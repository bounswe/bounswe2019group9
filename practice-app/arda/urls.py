from django.urls import path
from . import views

app_name = 'arda'

urlpatterns = [
    path('<language>', views.multimedia_recommendation, name="multimedia_recommendation"),
]
