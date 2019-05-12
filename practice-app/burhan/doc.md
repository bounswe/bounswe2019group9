# Practice-App / burhan App

This API predicts a user's language based on her IP address.

## Endpoints

### `GET ass7.bounswe2019group9.tk/burhan/check`
   * Returns requester's ip and predicted language

### `GET ass7.bounswe2019group9.tk/burhan/<ip>`
   * Returns predicted language of <ip>

* When successful returns a response as such: (StatusCode: 200)
```json
{"ip": "193.140.194.16",
 "Language": "Turkish"}
```

* When given ip is not legit or not found in the system it returns a response as such: (StatusCode: 404)
```json
{"ip": "null", "Language": "null"}
```

* _in such case you should contact the developer._