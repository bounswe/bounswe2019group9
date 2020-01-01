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
## Get Profile Info by User Id

**Request Content:**
id

**Response Content:**
The profile info with 200 status code if successful. The reason with 400(Bad Request) if unsuccessful.

##### Example Request-1
```
GET /users/profile?id=6
```
##### Example Response-1
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "userId": 6,
    "firstName": "testname",
    "lastName": "testsurname",
    "email": "test@test.com",
    "languages": [
      "English"
    ],
    "grades": [
      5
    ],
    "progressLevels": [
      0
    ]
  }
}
```
##### Example Request-2
```
GET /users/profile?id=66
```
##### Example Response-2
```json
{
  "status": 404,
  "explanation": "User not found with id: 66",
  "data": null
}
```
## Solved Exercise
**Request Content:**
exercise id, user id

**Response Content:**
The user with 200 status code if successful. The reason with 400(Bad Request) if unsuccessful.

##### Example Request-1
```
POST /users/solved
```
```json
{
  "userId": 6,
  "exerciseId": "6"
}
```
##### Example Response-1
```json
{
  "status": 200,
  "explanation": "Successful",
  "data": null
}
```
# Content Endpoints

## Get Exercise By Id

**Request Content:**
exercise id

**Response Content:**
Exercise with tags if successful, 404 not found if not.

##### Example Request
```
GET /contents?id=3
```
##### Example Response
```
{
  "status": 404,
  "explanation": "No exercise with this id.",
  "data": null
}
```

##### Example Request
```
GET /contents?id=13
```
##### Example Response
```
{
  "status": 200,
  "explanation": null,
  "data": {
    "id": 13,
    "languageId": 1,
    "grade": 2,
    "typeId": 2,
    "imageUrl": "",
    "soundUrl": "",
    "questionBody": "_______ he should have spent all the weekend preparing for his test, he in fact just lay in bed watching videos.",
    "optionA": "however",
    "optionB": "whereas",
    "optionC": "despite",
    "optionD": "nevertheless",
    "correctAnswer": 2,
    "tags": [
      {
        "id": 4,
        "exerciseId": 13,
        "tagText": "lazy"
      },
      {
        "id": 3,
        "exerciseId": 13,
        "tagText": "video"
      },
      {
        "id": 6,
        "exerciseId": 13,
        "tagText": "tv"
      },
      {
        "id": 7,
        "exerciseId": 13,
        "tagText": "television"
      }
    ]
  }
}
```

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

languageId(1 for English, 2 for Turkish, 3 for Italian)

typeId(1 for Grammar, 2 for vocabulary, 3 for reading, 4 for listening)

imageUrl(not necessary for proficiency, just delete)

soundUrl(not necessary for proficiency, just delete)

questionBody(As it sounds)

optionA, optionB, optionC, optionD(as it sounds)

correctAnswer(1 for A, 2 for B etc)

**Only image url and sound url can be null**

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
## Delete exercise

**Request Content:**

Exercise id

**Response Content:**
none

##### Example Request
```
GET /contents/delete?id=3
```

##### Example Response
```json
{
    "status": 200,
    "explanation": null,
    "data": null
}
```
# Grade Endpoints
## Get Grade by UserId and LanguageId

**Request Content:**
User id and language id

**Response Content:**
Grade

##### Example Request
```
GET /grades/get?userId=3&languageId=1
```

##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "id": 10,
    "userId": 12,
    "languageId": 1,
    "grade": 1
  }
}
```
## Add Grade

**Request Content:**
UserId, languageId, grade

**Response Content:**
Grade itself

##### Example Request
```
POST /grades/add
```
```json
{
  "grade": 4,
  "languageId": 1,
  "userId": 4
}
```

##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "userId": 4,
    "languageId": 1,
    "grade": 4
  }
}
```
# Search Endpoints
## Search User

**Request Content:**
First name, last name, grade and language

**Response Content:**
List of users

##### Example Request
```
POST /search/users
```
```json
{
  "firstName": "t",
  "grade": 3,
  "languageId": 1,
  "lastName": "te"
}
```

##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "userId": 3,
      "firstName": "ahmet",
      "lastName": "test",
      "email": "ahmettesttttt@testtest.coam",
      "languages": [
        "English"
      ],
      "grades": [
        3
      ],
      "progressLevels": [
        0
      ]
    },
    {
      "userId": 6,
      "firstName": "testname",
      "lastName": "testsurname",
      "email": "test@test.com",
      "languages": [
        "English"
      ],
      "grades": [
        5
      ],
      "progressLevels": [
        0
      ]
    }
  ]
}
```
## Search Exercises

**Request Content:**
UserId, languageId, grade, typeId, tag

**Response Content:**
List of exercises which fits in given parameters; grade, type and language. Also if tag is given, returns only the exercises with tags semantically similar to the given one. Also if user id is given, returns the exercises which that user haven't solved yet.

##### Example Request
```
POST /search/exercises
```
```json
{
  "grade": 2,
  "languageId": 1,
  "tag": "television",
  "typeId": 2,
  "userId": 7
}
```

##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "id": 13,
      "languageId": 1,
      "grade": 2,
      "typeId": 2,
      "imageUrl": "",
      "soundUrl": "",
      "questionBody": "_______ he should have spent all the weekend preparing for his test, he in fact just lay in bed watching videos.",
      "optionA": "however",
      "optionB": "whereas",
      "optionC": "despite",
      "optionD": "nevertheless",
      "correctAnswer": 2,
      "tags": [
        {
          "id": 4,
          "exerciseId": 13,
          "tagText": "lazy"
        },
        {
          "id": 3,
          "exerciseId": 13,
          "tagText": "video"
        },
        {
          "id": 6,
          "exerciseId": 13,
          "tagText": "tv"
        },
        {
          "id": 7,
          "exerciseId": 13,
          "tagText": "television"
        }
      ]
    }
  ]
}
```
# Tag Endpoints
## Add Tag

**Request Content:**
Exercise id, tag text

**Response Content:**
Tag itself

##### Example Request
```
POST /tags
```
```json
{
  "exerciseId": 12,
  "tagText": "test"
}
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "id": 8,
    "exerciseId": 12,
    "tagText": "test"
  }
}
```
# Invitation Endpoints
## Add Invitation

**Request Content:**
receiver id, source id

**Response Content:**
Invitation itself

##### Example Request
```
POST /invitations
```
```json
{
  "receiverId": 1,
  "sourceId": 5
}
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "id": 15,
    "sourceId": 5,
    "receiverId": 1,
    "createdAt": "2019-12-01T15:06:22.626+0000"
  }
}
```
## Answer Invitation

**Request Content:**
receiver id, source id, approved(boolean)

**Response Content:**
Conversation itself

##### Example Request
```
POST /invitations/answer
```
```json
{
  "approved": true,
  "receiverId": 1,
  "sourceId": 5
}
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "id": 11,
    "userIdOne": 5,
    "userIdTwo": 1,
    "lastUpdatedAt": "2019-12-01T15:08:05.896+0000"
  }
}
```
##### Example Response(if conversation already exists)
```json
{
  "status": 400,
  "explanation": "Conversation already exists",
  "data": null
}
```

## Get Inviter Profile Infos

**Request Content:**
receiver user id

**Response Content:**
List of profile infos

##### Example Request
```
GET /invitations/byReceiverId?userId=1
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "userId": 6,
      "firstName": "testname",
      "lastName": "testsurname",
      "email": "test@test.com",
      "languages": [
        "English"
      ],
      "grades": [
        5
      ],
      "progressLevels": [
        0
      ]
    }
  ]
}
```
## Get Invitation State

**Request Content:**
userId1, userId2

**Response Content:**
Invitation state

##### Example Request
```
GET /invitations/state?userId1=1&userId2=6
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "userId1": 1,
    "userId2": 6,
    "pendingRequestFromOneToTwo": false,
    "pendingRequestFromTwoToOne": true,
    "startedConversation": false
  }
}
```
# Conversation Endpoints
## Get Conversation Profile Info

**Request Content:**
user id

**Response Content:**
List of profile infos of given user id

##### Example Request
```
GET /conversations?id=1
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "userId": 5,
      "firstName": "testname",
      "lastName": "testtest",
      "email": "test@fortest.com",
      "languages": [],
      "grades": [],
      "progressLevels": []
    },
    {
      "userId": 2,
      "firstName": "ahmet",
      "lastName": "test",
      "email": "ahmettest@testtest.coam",
      "languages": [],
      "grades": [],
      "progressLevels": []
    },
    {
      "userId": 3,
      "firstName": "ahmet",
      "lastName": "test",
      "email": "ahmettesttttt@testtest.coam",
      "languages": [
        "English"
      ],
      "grades": [
        3
      ],
      "progressLevels": [
        0
      ]
    },
    {
      "userId": 4,
      "firstName": "ahmet",
      "lastName": "test",
      "email": "ahmettesttttttt@testtest.coam",
      "languages": [
        "English"
      ],
      "grades": [
        1
      ],
      "progressLevels": [
        25
      ]
    }
  ]
}
```
# Message Endpoints
## Get Messages By User Id 

**Request Content:**
user id

**Response Content:**
List of Messages of the User

##### Example Request
```
GET /messages?userId=9
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "id": 10,
      "sourceId": 9,
      "receiverId": 2,
      "content": "selam",
      "createdAt": "2019-11-24T14:49:07.090+0000"
    },
    {
      "id": 11,
      "sourceId": 2,
      "receiverId": 9,
      "content": "selam",
      "createdAt": "2019-11-24T14:49:19.480+0000"
    },
    {
      "id": 12,
      "sourceId": 2,
      "receiverId": 9,
      "content": "nabber",
      "createdAt": "2019-11-24T14:49:28.963+0000"
    },
    {
      "id": 13,
      "sourceId": 2,
      "receiverId": 9,
      "content": "nabber",
      "createdAt": "2019-11-24T14:49:30.561+0000"
    },
    {
      "id": 14,
      "sourceId": 2,
      "receiverId": 9,
      "content": "nabber",
      "createdAt": "2019-11-24T14:49:31.344+0000"
    },
    {
      "id": 15,
      "sourceId": 2,
      "receiverId": 9,
      "content": "nabber",
      "createdAt": "2019-11-24T15:55:51.799+0000"
    },
    {
      "id": 16,
      "sourceId": 9,
      "receiverId": 2,
      "content": "asffghff",
      "createdAt": "2019-11-24T16:01:23.690+0000"
    },
    {
      "id": 17,
      "sourceId": 9,
      "receiverId": 2,
      "content": "asdfghjkli",
      "createdAt": "2019-11-24T16:01:34.912+0000"
    },
    {
      "id": 18,
      "sourceId": 9,
      "receiverId": 2,
      "content": "ibrahm",
      "createdAt": "2019-11-24T16:01:46.860+0000"
    },
    {
      "id": 19,
      "sourceId": 9,
      "receiverId": 2,
      "content": "iyi",
      "createdAt": "2019-11-24T16:18:16.600+0000"
    },
    {
      "id": 20,
      "sourceId": 9,
      "receiverId": 2,
      "content": "çok güzel mesajlaşma",
      "createdAt": "2019-11-24T16:56:26.617+0000"
    },
    {
      "id": 21,
      "sourceId": 9,
      "receiverId": 2,
      "content": "yfgjomö",
      "createdAt": "2019-11-24T17:02:18.924+0000"
    },
    {
      "id": 22,
      "sourceId": 9,
      "receiverId": 2,
      "content": "irem",
      "createdAt": "2019-11-24T17:09:54.875+0000"
    },
    {
      "id": 23,
      "sourceId": 37,
      "receiverId": 9,
      "content": "Selam İremm ",
      "createdAt": "2019-11-25T04:51:11.692+0000"
    },
    {
      "id": 24,
      "sourceId": 37,
      "receiverId": 9,
      "content": "Deneme yapıyorum :D ",
      "createdAt": "2019-11-25T04:51:39.463+0000"
    },
    {
      "id": 25,
      "sourceId": 9,
      "receiverId": 37,
      "content": "selam ",
      "createdAt": "2019-11-25T04:53:31.176+0000"
    },
    {
      "id": 26,
      "sourceId": 37,
      "receiverId": 9,
      "content": "Günaydın irem",
      "createdAt": "2019-11-25T10:39:09.810+0000"
    },
    {
      "id": 27,
      "sourceId": 37,
      "receiverId": 9,
      "content": "Ben de ibrahim :D",
      "createdAt": "2019-11-25T10:39:16.587+0000"
    },
    {
      "id": 28,
      "sourceId": 9,
      "receiverId": 37,
      "content": "Ben de game :D\n",
      "createdAt": "2019-11-25T10:39:34.911+0000"
    },
    {
      "id": 29,
      "sourceId": 37,
      "receiverId": 9,
      "content": "Ama saat sıkıntılı :D",
      "createdAt": "2019-11-25T10:40:12.159+0000"
    },
    {
      "id": 30,
      "sourceId": 37,
      "receiverId": 9,
      "content": "13 40 yazıyor",
      "createdAt": "2019-11-25T10:40:24.313+0000"
    },
    {
      "id": 31,
      "sourceId": 9,
      "receiverId": 8,
      "content": "selam gg",
      "createdAt": "2019-11-25T10:40:30.933+0000"
    },
    {
      "id": 32,
      "sourceId": 37,
      "receiverId": 9,
      "content": "Halbuki 16 40",
      "createdAt": "2019-11-25T10:40:36.387+0000"
    },
    {
      "id": 33,
      "sourceId": 9,
      "receiverId": 8,
      "content": "deneme",
      "createdAt": "2019-11-25T17:17:43.781+0000"
    }
  ]
}
```
## Get Conversation Content 

**Request Content:**
user id1, user id2

**Response Content:**
List of Messages of the conversation

##### Example Request
```
GET /messages/chat?userId1=9&userId2=8
```
##### Example Response
```
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "id": 31,
      "sourceId": 9,
      "receiverId": 8,
      "content": "selam gg",
      "createdAt": "2019-11-25T10:40:30.933+0000"
    },
    {
      "id": 33,
      "sourceId": 9,
      "receiverId": 8,
      "content": "deneme",
      "createdAt": "2019-11-25T17:17:43.781+0000"
    }
  ]
}
```

## Create Message

**Request Content:**
sourceId, receiverId, content

**Response Content:**
Conversation content between two users

##### Example Request
```
POST /messages
```
```
{
  "content": "this is a message",
  "receiverId": 9,
  "sourceId": 8
}
```
##### Example Response
```
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "id": 31,
      "sourceId": 9,
      "receiverId": 8,
      "content": "selam gg",
      "createdAt": "2019-11-25T10:40:30.933+0000"
    },
    {
      "id": 33,
      "sourceId": 9,
      "receiverId": 8,
      "content": "deneme",
      "createdAt": "2019-11-25T17:17:43.781+0000"
    },
    {
      "id": 42,
      "sourceId": 8,
      "receiverId": 9,
      "content": "this is a message",
      "createdAt": "2019-12-01T16:20:34.554+0000"
    }
  ]
}
```

# Annotation Endpoints
## Create Annotation 

**Request Content:**
Annotation string

**Response Content:**
Ok

##### Example Request
```
POST /annotations
```
```json
{
  "annotation": "This is an annotation message."
}
```
##### Example Response
```json
{
  "data": null,
  "explanation": null,
  "status": 200
}
```
## Update Annotation 

**Request Content:**
Annotation string, annotation id

**Response Content:**
Ok

##### Example Request
```
POST /annotations
```
```json
{
  "annotation": "This is an annotation message.",
  "id": 3
}
```
##### Example Response
```json
{
  "data": null,
  "explanation": null,
  "status": 200
}
```
## Get Annotation 

**Request Content:**
Annotation id

**Response Content:**
Annotation itself, by id

##### Example Request
```
GET /annotations/getById?id=3
```
##### Example Response
```json
{
  "id": "http://api.bounswe2019group9.tk/annotations?id=3",
  "field": "hasan",
  "field2": "mahmut"
}
```

## Delete Annotation 

**Request Content:**
Annotation id

**Response Content:**
Ok

##### Example Request
```
GET /annotations/deleteById?annotationId=3
```
##### Example Response
```json
{
  "data": null,
  "explanation": null,
  "status": 200
}
```

## Search Annotation 

**Request Content:**
Target id

**Response Content:**
Annotation string

##### Example Request
```
GET /annotations/searchInAnnotations?targetId=3
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=4\",\"field\":\"http://www.w3.org/TR/media-frags/\", \"field2\":\"mahmut\"}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=5\",\"field\":\"http://www.w3.org/TR/media-frags/\", \"field2\":\"mahmut\"}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=6\", \"url\": \"http://www.w3.org/TR/media-frags/\" }",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=7\", \"url\": \"http://www.w3.org/ns/anno.jsonld/\" }",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=8\", 'url': 'http://www.w3.org/ns/anno.jsonld/', 'neden': 'singlequote' }",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=19\", \"dasdsa\": \" 5635 \" }",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=20\", \"dasdsa\": \" 5635 \" }",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=37\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/22\",\"name\":\"Mahmut Kızıloğlu\",\"email\":\"mahmut.kiziloglu@hotmail.com\"},\"created\":\"2019-12-24T14:43:10.115Z\",\"modified\":\"2019-12-24T14:43:19.094Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"kjhkjhjkhlkjhliutuyftsrtyuiuytr\",\"format\":\"text/plain\"},\"motivation\":\"assessing\",\"target\":{\"id\":\"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=24\",\"type\":\"Text\",\"format\":\"text/plain\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://tools.ietf.org/rfc/rfc5147\",\"value\":\"char=323,482\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=38\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/22\",\"name\":\"Mahmut Kızıloğlu\",\"email\":\"mahmut.kiziloglu@hotmail.com\"},\"created\":\"2019-12-24T14:43:29.904Z\",\"modified\":\"2019-12-24T14:43:33.008Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"jgt\",\"format\":\"text/plain\"},\"motivation\":\"commenting\",\"target\":{\"id\":\"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=24\",\"type\":\"Text\",\"format\":\"text/plain\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://tools.ietf.org/rfc/rfc5147\",\"value\":\"char=180,192\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=25\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/30\",\"name\":\"John Johnwell\",\"email\":\"john@johnwell.com\"},\"created\":\"2019-12-24T10:55:49.132Z\",\"modified\":\"2019-12-24T11:18:45.251Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\",\"format\":\"text/plain\"},\"motivation\":\"describing\",\"target\":{\"id\":\"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=17\",\"type\":\"Text\",\"format\":\"text/plain\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://tools.ietf.org/rfc/rfc5147\",\"value\":\"char=3,5\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=26\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/30\",\"name\":\"John Johnwell\",\"email\":\"john@johnwell.com\"},\"created\":\"2019-12-24T11:30:44.664Z\",\"modified\":\"2019-12-24T11:36:44.649Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"Well done here\",\"format\":\"text/plain\"},\"motivation\":\"assessing\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_30.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=56,209,305,46\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=27\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/30\",\"name\":\"John Johnwell\",\"email\":\"john@johnwell.com\"},\"created\":\"2019-12-24T11:30:56.984Z\",\"modified\":\"2019-12-24T11:36:49.841Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"Oh my god, great!!\",\"format\":\"text/plain\"},\"motivation\":\"describing\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_30.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=54,321,88,181\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=28\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/30\",\"name\":\"John Johnwell\",\"email\":\"john@johnwell.com\"},\"created\":\"2019-12-24T12:23:03.494Z\",\"modified\":\"2019-12-24T12:23:09.048Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"Wow dude\",\"format\":\"text/plain\"},\"motivation\":\"commenting\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=138,193,60,30\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=29\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/30\",\"name\":\"John Johnwell\",\"email\":\"john@johnwell.com\"},\"created\":\"2019-12-24T12:23:10.269Z\",\"modified\":\"2019-12-24T12:23:22.445Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"This is the best thing I've ever seen\",\"format\":\"text/plain\"},\"motivation\":\"bookmarking\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=360,516,171,30\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=33\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/9\",\"name\":\"irem Uguz\",\"email\":\"irem@test.com\"},\"created\":\"2019-12-24T13:30:48.093Z\",\"modified\":\"2019-12-24T13:31:04.987Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"burası net\",\"format\":\"text/plain\"},\"motivation\":\"classifying\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_9.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=277,307,60,30\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=30\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/30\",\"name\":\"John Johnwell\",\"email\":\"john@johnwell.com\"},\"created\":\"2019-12-24T12:23:23.805Z\",\"modified\":\"2019-12-24T12:23:33.583Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"It even supports scroll, woah\",\"format\":\"text/plain\"},\"motivation\":\"highlighting\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=138,642,60,30\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=32\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/9\",\"name\":\"irem Uguz\",\"email\":\"irem@test.com\"},\"created\":\"2019-12-24T13:30:17.798Z\",\"modified\":\"2019-12-24T13:30:44.551Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"Merhaba bunu ben yazdım\",\"format\":\"text/plain\"},\"motivation\":\"bookmarking\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_9.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=301,45,201,215\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=31\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/12\",\"name\":\"Johnie Walker\",\"email\":\"j.walker@hotmail.com\"},\"created\":\"2019-12-24T12:24:11.645Z\",\"modified\":\"2019-12-24T12:24:49.757Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"I know right!!\",\"format\":\"text/plain\"},\"motivation\":\"moderating\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=18,394,75,56\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=34\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/41\",\"name\":\"Doğan Çavdarcı\",\"email\":\"dogan.cavdarci@hotmail.com\"},\"created\":\"2019-12-24T13:32:15.392Z\",\"modified\":\"2019-12-24T13:32:26.445Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"burası olmamıs düzelecek\",\"format\":\"text/plain\"},\"motivation\":\"editing\",\"target\":{\"id\":\"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_9.jpeg\",\"type\":\"Image\",\"format\":\"image/jpeg\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://www.w3.org/TR/media-frags/\",\"value\":\"xywh=397,290,60,30\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=35\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/30\",\"name\":\"John Johnwell\",\"email\":\"john@johnwell.com\"},\"created\":\"2019-12-24T13:37:19.537Z\",\"modified\":\"2019-12-24T13:37:23.015Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"asdasdas\",\"format\":\"text/plain\"},\"motivation\":\"describing\",\"target\":{\"id\":\"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=17\",\"type\":\"Text\",\"format\":\"text/plain\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://tools.ietf.org/rfc/rfc5147\",\"value\":\"char=0,3\"}}}",
    "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=36\",\"@context\":\"http://www.w3.org/ns/anno.jsonld\",\"type\":\"Annotation\",\"creator\":{\"id\":\"https://bounswe2019group9.tk/users/22\",\"name\":\"Mahmut Kızıloğlu\",\"email\":\"mahmut.kiziloglu@hotmail.com\"},\"created\":\"2019-12-24T14:42:56.554Z\",\"modified\":\"2019-12-24T14:43:01.283Z\",\"body\":{\"type\":\"TextualBody\",\"value\":\"jhg\",\"format\":\"text/plain\"},\"motivation\":\"highlighting\",\"target\":{\"id\":\"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=24\",\"type\":\"Text\",\"format\":\"text/plain\",\"selector\":{\"type\":\"FragmentSelector\",\"conformsTo\":\"http://tools.ietf.org/rfc/rfc5147\",\"value\":\"char=152,456\"}}}"
  ]
}
```

# Comment Endpoints
## Create Comment 

**Request Content:**
Content text, source id, receiver id

**Response Content:**
Comment itself

##### Example Request
```
POST /comments
```
```json
{
  "content": "Good one",
  "receiverId": 2,
  "sourceId": 3
}
```
##### Example Response
```json
{
  "data": {
    "content": "Good one",
    "receiverId": 2,
    "sourceId": 3
  },
  "explanation": null,
  "status": 200
}
```

## Get Comments By Receiver Id 

**Request Content:**
Receiver user id

**Response Content:**
Comments of that user

##### Example Request
```
GET /comments/getCommentsByReceiverId?userId=3
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "sourceFirstName": "ahmet",
      "sourceLastName": "test",
      "comment": {
        "id": 1,
        "sourceId": 1,
        "receiverId": 3,
        "content": "nice",
        "createdAt": "2019-12-14T00:00:00.000+0000"
      }
    }
  ]
}
```

# Assignment Endpoints
## Create Assignment 

**Request Content:**
Language id, question text

**Response Content:**
Assignment itself

##### Example Request
```
POST /assignments
```
```json
{
  "languageId": 1,
  "text": "What do you think about fortune telling?"
}
```
##### Example Response
```json
{
  "data": {
    "languageId": 1,
    "text": "What do you think about fortune telling?"
  },
  "explanation": null,
  "status": 200
}
```

## Get Assignment By Id 

**Request Content:**
Id

**Response Content:**
Assignment with that id

##### Example Request
```
GET /assignments?id=3
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": {
    "id": 3,
    "question": "Describe a place you will never forget.",
    "languageId": 1
  }
}
```

## Get Assignment By Language Id 

**Request Content:**
Language Id

**Response Content:**
Assignments in that language

##### Example Request
```
GET /assignments/language?id=1
```
##### Example Response
```json
{
  "status": 200,
  "explanation": null,
  "data": [
    {
      "id": 3,
      "question": "Describe a place you will never forget.",
      "languageId": 1
    },
    {
      "id": 4,
      "question": "Describe a sporting event you attended recently.",
      "languageId": 1
    },
    {
      "id": 5,
      "question": "Describe someone you respect deeply.",
      "languageId": 1
    },
    {
      "id": 6,
      "question": "Describe your childhood home.",
      "languageId": 1
    },
    {
      "id": 7,
      "question": "Describe the nightlife in a city you are familiar with.",
      "languageId": 1
    },
    {
      "id": 8,
      "question": "What would you do if you got lost in an unfamiliar city?",
      "languageId": 1
    },
    {
      "id": 9,
      "question": "What would you do if you left something in a locked building?",
      "languageId": 1
    },
    {
      "id": 1,
      "question": "What is your opinion about fortune telling?",
      "languageId": 1
    },
    {
      "id": 2,
      "question": "What is your opinion about cellular phones?",
      "languageId": 1
    }
  ]
}
```

