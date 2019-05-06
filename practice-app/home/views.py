from django.shortcuts import render

APP_LIST = [
    {
        "name": "Detect",
        "description": "A language detection app",
        "href": "/detect/I%20like%20eating%20apples%20while%20I%20am%20reading%20a%20book./"
    },
    {
        "name": "Translate",
        "description": "A language translation app",
        "href": "/translate/"
    },
    {
        "name": "Rec",
        "description": "A movie recommendation app",
        "href": "/rec/en"
    },
    {
        "name": "Polls",
        "description": "A sample question / answer app",
        "href": "/polls/",
    }
]


def index(request):
    return render(request, 'home/index.html', {
        "apps": APP_LIST
    })

# Create your views here.
