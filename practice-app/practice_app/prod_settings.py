# Settings for production environment

from .base_settings import *

SECRET_KEY = get_secret('SECRET_KEY')

DEBUG = False

ALLOWED_HOSTS = ["ass7.bounswe2019group9.tk", "127.0.0.1", "localhost"]

# Database
# https://docs.djangoproject.com/en/2.2/ref/settings/#databases

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': get_secret('DB_NAME'),
        'USER': get_secret('DB_USER'),
        'PASSWORD': get_secret('DB_PASSWORD'),
        'HOST': '',
        'PORT': ''
    }
}

STATIC_ROOT = get_secret("STATIC_ROOT")

DETECT_API_KEY = get_secret("DETECT_API_KEY")