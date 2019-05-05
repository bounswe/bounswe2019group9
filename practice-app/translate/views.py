from django.shortcuts import render

from .models import Language


def post_helper(request):
    return translator(
        request,
        from_lang_code=request.POST.get('from', None),
        to_lang_code=request.POST.get('to', None)
    )


def translator(request, from_lang_code=None, to_lang_code=None):
    context = {
        "errors": [],
        "languages": Language.objects.all(),
        "from_lang": None,
        "to_lang": None,
        "source_text": "",
    }
    if from_lang_code:
        try:
            context["from_lang"] = Language.objects.get(code=from_lang_code)
        except Language.DoesNotExist:
            context["errors"].append(
                "No language for code, {}, exists. Defaulting from language to empty."
                    .format(from_lang_code)
            )
    if to_lang_code:
        try:
            context["to_lang"] = Language.objects.get(code=to_lang_code)
        except Language.DoesNotExist:
            context["errors"].append(
                "No language for code, {}, exists. Defaulting to language to empty."
                    .format(to_lang_code)
            )
    if request.method == "POST":
        context["source_text"] = request.POST.get('source', "")
        if not context["from_lang"]:
            context["errors"].append(
                "Please select a language to translate from."
            )
        if not context["to_lang"]:
            context["errors"].append(
                "Please select a language to translate to."
            )
        if context["from_lang"] == context["to_lang"]:
            context["errors"].append(
                "From Language & To Language Parts cannot be the same."
            )
        if not context["source_text"]:
            context["errors"].append(
                "Please enter a text to translate."
            )
        if len(context["errors"]) == 0:
            try:
                context["translated_text"] = context["to_lang"].translate(
                    from_language=context["from_lang"],
                    text=context["source_text"]
                )
            except:
                context["errors"].append(
                    "Failed to translate the text for some unknown reason. \
                    Maybe try again later or contact the developer."
                )
    return render(request, 'translate/translator.html', context)
