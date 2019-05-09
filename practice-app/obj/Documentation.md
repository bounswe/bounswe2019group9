## Object Recognition API
This API takes a picture and recognizes the objects in that picture with the correction percentage and returns an Json abject with that data. This API can be used in our project to help the users learn the vocabulary of the object in that picture.

### Usage
* Url for API: /obj/<Url of the picture>
* Returns a Json object such that: 
---
```json
{
  "images": [
    {
      "classifiers": [
        {
          "classifier_id": "default",
          "name": "default",
          "classes": [
            {
              "class": "<Object Name>",
              "score": <Score of the object>,
              "type_hierarchy": "<Type of the object>"
            }
          ]
        }
      ],
      "source_url": "<Url of the image>",
      "resolved_url": "<Url of the image>"
    }
  ],
  "images_processed": 1,
  "custom_classes": 0
} 
```
---

### Example
* Picture:


![Example picture](https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/640px-IBM_VGA_90X8941_on_PS55.jpg)

* Url Input:
/obj/https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/640px-IBM_VGA_90X8941_on_PS55.jpg

* Json Output:
---
```json
{
  "images": [
    {
      "classifiers": [
        {
          "classifier_id": "default",
          "name": "default",
          "classes": [
            {
              "class": "circuit board",
              "score": 0.578,
              "type_hierarchy": "/electrical device/computer circuit/circuit board"
            },
            {
              "class": "computer circuit",
              "score": 0.755
            },
            {
              "class": "electrical device",
              "score": 0.757
            },
            {
              "class": "disk controller",
              "score": 0.553,
              "type_hierarchy": "/controller/disk controller"
            },
            {
              "class": "controller",
              "score": 0.558
            },
            {
              "class": "central processing unit",
              "score": 0.535
            },
            {
              "class": "PC board",
              "score": 0.501,
              "type_hierarchy": "/electrical device/computer circuit/PC board"
            },
            {
              "class": "CPU board",
              "score": 0.5,
              "type_hierarchy": "/electrical device/computer circuit/CPU board"
            },
            {
              "class": "electronic equipment",
              "score": 0.6
            },
            {
              "class": "memory device",
              "score": 0.599
            },
            {
              "class": "microchip",
              "score": 0.592
            },
            {
              "class": "jade green color",
              "score": 0.838
            },
            {
              "class": "emerald color",
              "score": 0.787
            }
          ]
        }
      ],
      "source_url": "https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/640px-IBM_VGA_90X8941_on_PS55.jpg",
      "resolved_url": "https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/640px-IBM_VGA_90X8941_on_PS55.jpg"
    }
  ],
  "images_processed": 1,
  "custom_classes": 0
}
```
---


