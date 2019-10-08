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
      "password": "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
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
