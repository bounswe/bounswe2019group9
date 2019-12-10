module.exports = [
  {
    "@context": "http://www.w3.org/ns/anno.jsonld",
    "id": "http://example.org/anno1",
    "type": "Annotation",
    "creator": {
      "id": "http://example.org/user1",
      "name": "John Russel",
      "email": "john@russel.com"
    },
    "created": "2015-01-28T12:00:00Z",
    "modified": "2015-01-29T09:00:00Z",
    "body": {
      "type" : "TextualBody",
      "value" : "Great Essay",
      "format" : "text/plain"
    },
    "motivation": "assessing",
    "target": {
      "id": "http://example.com/image1",
      "type": "Image",
      "format": "image/jpeg",
      "selector": {
        "type": "FragmentSelector",
        "conformsTo": "http://www.w3.org/TR/media-frags/",
        "value": "xywh=100,100,300,300"
      }
    }
  },
  {
    "@context": "http://www.w3.org/ns/anno.jsonld",
    "id": "http://api.bounswe2019group9.tk/annotation/find?id=2",
    "type": "Annotation",
    "creator": {
      "id": "http://example.org/user1",
      "name": "John Russel",
      "email": "john@russel.com"
    },
    "created": "2015-01-28T12:00:00Z",
    "modified": "2015-01-29T09:00:00Z",
    "body": {
      "type" : "TextualBody",
      "value" : "Bad Mistake",
      "format" : "text/plain"
    },
    "motivation": "commenting",
    "target": {
      "id": "http://example.com/writing1#char=0,10",
      "type": "Text",
      "format" : "text/plain",
      "selector": {
        "type": "FragmentSelector",
        "conformsTo": "http://tools.ietf.org/rfc/rfc5147",
        "value": "char=0,10"
      }
    }
  }
]