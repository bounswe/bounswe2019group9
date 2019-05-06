from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt

import json

from .models import Language
from .serializers import CustomJSONEncoder


@csrf_exempt
def post_helper(request):
    if request.method == "POST":
        if request.content_type == 'application/json':
            request.POST = json.loads(request.body)
    return translator(
        request,
        from_lang_code=request.POST.get('from', None),
        to_lang_code=request.POST.get('to', None)
    )


def translator(request, from_lang_code=None, to_lang_code=None):
    status = 200
    context = {
        "errors": [],
        "from": None,
        "to": None,
        "source": None,
        "translation": None
    }
    if from_lang_code:
        try:
            context["from"] = Language.objects.get(code=from_lang_code)
        except Language.DoesNotExist:
            context["errors"].append(
                "No language for code, {}, exists. Defaulting from language to empty."
                .format(from_lang_code)
            )
    if to_lang_code:
        try:
            context["to"] = Language.objects.get(code=to_lang_code)
        except Language.DoesNotExist:
            context["errors"].append(
                "No language for code, {}, exists. Defaulting to language to empty."
                .format(to_lang_code)
            )
    if request.method == "POST":
        context["source"] = request.POST.get('source', None)
        if not context["from"]:
            context["errors"].append(
                "Please select a language to translate from."
            )
        if not context["to"]:
            context["errors"].append(
                "Please select a language to translate to."
            )
        if context["from"] and context["from"] == context["to"]:
            context["errors"].append(
                "From language & To language parts cannot be the same."
            )
        if not context["source"]:
            context["errors"].append(
                "Please enter a text to translate."
            )
        if len(context["errors"]) == 0:
            try:
                context["translation"] = context["to"].translate(
                    from_language=context["from"],
                    text=context["source"]
                )
            except:
                context["errors"].append(
                    "Failed to translate the text for some unknown reason. \
                    Maybe try again later or contact the developer."
                )
                status = 500
        else:
            status = 400
    if request.content_type == 'application/json':
        return JsonResponse(context, encoder=CustomJSONEncoder, status=status)
    else:
        context["languages"] = Language.objects.all()
        return render(request, 'translate/translator.html', context, status=status)
