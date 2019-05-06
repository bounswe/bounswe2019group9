from django.contrib import admin

# Register your models here.
from .models import Language


class LanguageAdmin(admin.ModelAdmin):
    list_display = ('code', 'name')
    search_fields = ['name']


admin.site.register(Language, LanguageAdmin)
