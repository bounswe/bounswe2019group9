from django.core.serializers.json import DjangoJSONEncoder


class CustomJSONEncoder(DjangoJSONEncoder):
    def default(self, o):
        toJSON = getattr(o, 'toJSON', None)
        if callable(toJSON):
            return o.toJSON()
        else:
            return super().default(o)
