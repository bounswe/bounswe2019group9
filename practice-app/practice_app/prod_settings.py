# Settings for production environment

from .base_settings import *
import os
import json

from django.core.exceptions import ImproperlyConfigured

with open(os.path.join(BASE_DIR, 'secret.json')) as f:
    secrets = json.loads(f.read())


def get_secret(name):
    try:
        return secrets[name]
    except KeyError:
        raise ImproperlyConfigured(
            "ImproperlyConfigured: Set {0} environment variable".format(name))


SECRET_KEY = get_secret('SECRET_KEY')

DEBUG = False

ALLOWED_HOSTS = ["ass7.bounswe2019group9.tk", "127.0.0.1"]
