from django.db import models


# Create your models here.
class Language(models.Model):
    name = models.CharField(max_length=40)
    code = models.CharField(max_length=2)

    def __str__(self):
        return "{} ({})".format(self.name, self.code)

    def __eq__(self, other):
        return self and other and self.code == other.code

    def translate(self, from_language, text):
        return text  # TODO USE GOOGLE API HERE
