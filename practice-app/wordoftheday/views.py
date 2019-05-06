from django.http import HttpResponse, JsonResponse
from datetime import date
from .dictionaries import dic_en, dic_tr


def get_random_by_date(year, month, day, mod):
    return (year ** 2 + month ** 3 + day ** 4) % mod


def get_words_and_definitions(lang):
    if lang == 'en':
        return [dic_en.words_en, dic_en.definitions_en]
    elif lang == 'tr':
        return [dic_tr.words_tr, dic_tr.definitions_tr]
    else:
        return [[], []]


def index(request, lang):
    words = get_words_and_definitions(lang)[0]
    definitions = get_words_and_definitions(lang)[1]
    if len(words) == 0:
        return HttpResponse('NO SUCH LANGUAGE AVAILABLE')
    today = str(date.today())
    year = int(today.split('-')[0])
    month = int(today.split('-')[1])
    day = int(today.split('-')[2])
    word_number = get_random_by_date(year, month, day, len(words))
    return JsonResponse({
        'word': words[word_number],
        'definition': definitions[word_number],
    })
    #return HttpResponse(words[word_number]+':\n\n\t'+definitions[word_number])
