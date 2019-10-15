# Language Learning Platform REST API Documentation  

# Test Endpoint
## Greeting
This is created for testing purpose, say hi
```
GET /greeting?name=group9
```
```json
{
  "id": 3,
  "content": "Hello, group9!"
}
```

# User Endpoints
## Get User By Id

**Request Content:**
id

**Response Content:**
The user with corresponding id if it exists, null otherwise.

##### Example Request
```
GET /users/get?id=2
```

##### Example Response
```json
{
  "id": 2,
  "email": "ahmettest@testtest.coam",
  "password": "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
  "firstName": "ahmet",
  "lastName": "test"
}
```

## Register User

**Request Content:**
e-mail, password, firstName, lastName

**Response Content:**
The created user with 200 status code if successful. The reason with 400(Bad Request) if unsuccessful.

##### Example Request-1
```
POST /users/register
```
```json
{
  "email": "test@test.com",
  "password": "123456",
  "firstName": "testname",
  "lastName": "testsurname"
}
```
##### Example Response-1
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "email": "test@test.com",
    "password": "123456",
    "firstName": "testname",
    "lastName": "testsurname"
  }
}
```
##### Example Request-2
```
POST /users/register
```
```json
{
  "email": "testtest.com",
  "password": "123456",
  "firstName": "testname",
  "lastName": "testsurname"
}
```
##### Example Response-2
```json
{
  "status": 400,
  "explanation": "Invalid email",
  "data": null
}
```
##### Example Request-3
```
POST /users/register
```
```json
{
  "email": "somebodyelsesemailaddress@test.com",
  "password": "123456",
  "firstName": "testname",
  "lastName": "testsurname"
}
```
##### Example Response-3
```json
{
  "status": 400,
  "explanation": "This e-mail has already been registered",
  "data": null
}
```
## Login User

**Request Content:**
e-mail, password

**Response Content:**
The user with 200 status code if successful. The reason with 400(Bad Request) if unsuccessful.

##### Example Request-1
```
POST /users/login
```
```json
{
  "email": "test@fortest.com",
  "password": "123456"
}
```
##### Example Response-1
```json
{
  "status": 200,
  "explanation": null,
  "data": {
      "id": 5,
      "email": "test@fortest.com",
      "password": "123456",
      "firstName": "testname",
      "lastName": "testsurname"
  }
}
```
##### Example Request-2
```
POST /users/login
```
```json
{
  "email": "wrongemailaddress@wrong.email",
  "password": "wrongpassword"
}
```
##### Example Response-2
```json
{
  "status": 400,
  "explanation": "Wrong credentials",
  "data": null
}
```
# Content Endpoints
## Get All Available Languages List

**Request Content:**
none

**Response Content:**
The list of available languages

##### Example Request
```
GET /contents/languages
```

##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    "English",
    "Turkish",
    "Italian"
  ]
}
```
## Get All Exercises List

**Request Content:**
none

**Response Content:**
The list of all exercises of all languages

##### Example Request
```
GET /contents/all
```

##### Example Response
```json
{
    "status": 200,
    "explanation": null,
    "data": [
        {
            "languageId": 1,
            "typeId": 1,
            "imageUrl": null,
            "soundUrl": null,
            "question": "what is your name?",
            "optionA": "my name is..",
            "optionB": "your name is..",
            "optionC": "his name is...",
            "optionD": "her name is...",
            "correctAnswer": 1
        },
        {
          ...
        },
        
        ...
    ]
}
```

## Get Proficiency Exam

**Request Content:**
Language name as request parameter. Language names should be in English and capitalized.

**Response Content:**
10 questions from given language

##### Example Request
```
GET /contents/prof?language=English
```

##### Example Response
```json
{
    "status": 200,
    "explanation": null,
    "data": [
        {
            "languageId": 1,
            "typeId": 1,
            "imageUrl": null,
            "soundUrl": null,
            "question": "what is your name?",
            "optionA": "my name is..",
            "optionB": "your name is..",
            "optionC": "his name is...",
            "optionD": "her name is...",
            "correctAnswer": 1
        },
        {
          ...
        },
        
        ...
    ]
}
```
##### Example Request
```
GET /contents/prof?language=english
```

##### Example Response
```json
{
    "status": 400,
    "explanation": "Language not found.",
    "data": null
}
```
## Create exercise

**Request Content:**
languageId: 1 for English, 2 for Turkish, 3 for Italian..
typeId: 1 for Grammar, 2 for vocabulary, 3 for reading, 4 for listening
imageUrl: not necessary for proficiency, just leave blank
soundUrl: not necessary for proficiency, just leave blank
questionBody: As it sounds
optionA, optionB, optionC, optionD: as it sounds
correctAnswer: 1 for A, 2 for B...

ONLY IMAGEURL AND SOUNDURL CAN BE NULL

**Response Content:**
Exercise itself

##### Example Request
```
POST /contents/add
```
```json
{
  "correctAnswer": 4,
  "languageId": 1,
  "optionA": "Bean",
  "optionB": "Potato",
  "optionC": "Bread",
  "optionD": "Apple",
  "questionBody": "Which one of these is a fruit?",
  "typeId": 2
}
```

##### Example Response
```json
{
    "status": 200,
    "explanation": null,
    "data": {
        "correctAnswer": 4,
        "imageUrl": null,
        "languageId": 1,
        "optionA": "Bean",
        "optionB": "Potato",
        "optionC": "Bread",
        "optionD": "Apple",
        "questionBody": "Which one of these is a fruit?",
        "soundUrl": null,
        "typeId": 2
      }
}
```
