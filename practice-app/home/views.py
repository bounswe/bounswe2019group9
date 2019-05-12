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
        "name": "Obj",
        "description": "An object detection app",
        "href": "/obj/https%3A%2F%2Fwatson-developer-cloud.github.io%2Fdoc-tutorial-downloads%2Fvisual-recognition%2F640px-IBM_VGA_90X8941_on_PS55.jpg/"
    },
    {
        "name": "WordOfTheDay",
        "description": "An app for getting word of the day",
        "href": "/wordoftheday/en"
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
    },
	{
		"name": "burhan",
		"description": " Gives predicted language of given IP",
		"href":"/burhan/",
	},
]


def index(request):
    return render(request, 'home/index.html', {
        "apps": APP_LIST
    })

# Create your views here.
