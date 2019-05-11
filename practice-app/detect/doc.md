


Usage : 

~~~
/detect/I like apples and oranges.
~~~

Output :

~~~
{"language_code": "en", "language_name": "English", "percentage": 100, "reliable_result": true}
~~~

language_code : 

Returns the 2-digit language code for the respective language.

language_name:

Returns the language name for the respective language.

percentage:

Returns a percentage value (0-100%) in order to put in perspective the API's confidence about the respective language match and present the confidence margin between multiple matches.

Reliable_result :

Returns true or false depending on whether or not the API is completely confident about the main match.
