from django.urls import path
from . import views

app_name = 'obj'
urlpatterns = [
	path('<path:picture_url>/', views.object_detection)
	]
