from django.urls import path, include

from . import views

urlpatterns = [
    path('<in_text>/', views.output, name="output"),
]