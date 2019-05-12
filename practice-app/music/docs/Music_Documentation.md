## Music Suggestion
A user can GET, PUT and DELETE songs and the endpoint returns a `JSON` object.

## Usage
Url extension: `/music/v1/songs`

# `GET`

```json
{
"title": "Dark Side of The Moon", 
"artist": "Pink Floyd" 
}
```

# `PUT`

If successful:

```json
{
"title": "Symphony No. 40", 
"artist": "Amadeus Mozart" 
}
```

If not successful:

```json
{
"message": "Song with id: {} does not exist", 
}
```

# `DELETE`

If successful:

```json
{
"title": "They Don't Care About Us", 
"artist": "Michael Jackson" 
}
```

If not successful:

```json
{
"message": "Song with id: {} does not exist", 
}
```


