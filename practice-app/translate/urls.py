from django.urls import path

from . import views

app_name = 'translate'
urlpatterns = [
    path('', views.post_helper, name='index'),
    path('<slug:from_lang_code>/', views.translator, name='from'),
    path('-/<slug:to_lang_code>/', views.translator, name='to'),
    path('<slug:from_lang_code>/<slug:to_lang_code>/', views.translator, name='from-to')


    # path('<int:pk>/', views.DetailView.as_view(), name='detail'),
    # path('<int:pk>/results/', views.ResultsView.as_view(), name='results'),
    # path('<int:question_id>/vote/', views.vote, name='vote'),
]
