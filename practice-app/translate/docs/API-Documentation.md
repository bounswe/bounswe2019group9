# Practice-App / Translate App

This API provides translating a text from one language to another using Azure Translator Text Services

## Endpoints

### `GET ass7.bounswe2019group9.tk/translate/`
   * Returns an HTML page with a form to make translations.

### `GET ass7.bounswe2019group9.tk/translate/{from-language-code}/`
   * Returns the same HTML page with the given language preselected as the from language

### `GET ass7.bounswe2019group9.tk/translate/-/{to-language-code}`
   * Returns the same HTML page with the given language preselected as the to language
   
### `GET ass7.bounswe2019group9.tk/translate/{from-language-code}/{to-language-code}`
   * Returns the same HTML page with the given languages preselected as the from and to languages
   
### `POST ass7.bounswe2019group9.tk/translate/`

#### If `Content-Type` Header is set to `json/application` such request expected
```json
{
  "from": "from-language-code",
  "to": "to-language-code",
  "source": "text-to-be-translated" 
}
```
* When successful returns a response as such: (StatusCode: 200)
```json
{
  "errors": [],
  "from": "from-language-code",
  "to": "to-language-code",
  "source": "original-text",
  "translation": "translated-text"
}
```

* When a parameter is missing or wrong it returns a response as such: (StatusCode: 400)
```json
{
  "errors": [
    "Whatever error happened.",
    "More than one error can have been happened"
  ],
  "from": "from-language-code-or-null-if-invalid-from-input",
  "to": "to-language-code-or-null-if-invalid-to-input",
  "source": "original-text-or-null-if-invalid-source-text",
  "translation": null
}
```

* When the request is correct but an unknown error occured it returns a response as such: (StatusCode: 500)
```json
{
  "errors": [
    "Failed to translate the text for some unknown reason. Maybe try again later or contact the developer."
  ],
  "from": "from-language-code",
  "to": "to-language-code",
  "source": "original-text",
  "translation": null
}
```
* _in such case you should contact the developer._

#### If the `Content-Type` header is not set or something else, such request is expected (Form Encoded)
```
POST http://ass7.bounswe2019group9.tk/translate/
from=from-language-code
to=to-language-code
source=original-text
```   
Whether the translation is successful or not, it will return the same html form as `GET /translate/` ,however, 
displaying any errors occured as well as the translated text if it did succeed. Also, it preselects the given 
from & to languages in the form as well as filling the source text with the given one.


