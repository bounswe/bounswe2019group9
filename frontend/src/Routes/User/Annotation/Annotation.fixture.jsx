import Annotation from './index';
const sampleAnnotationList = require('./sample-annotation-list');

export default [
    {
        component: Annotation,
        name: 'image-annotation-sample',
        props: {
            annotations: [sampleAnnotationList[0]],
            essay: {
                id: 3,
                sourceType: 2,
                source: 'http://1.bp.blogspot.com/-tHh203WU8kI/UxZtbYH2nbI/AAAAAAAANNk/5NjxRD7zOac/s1600/jrst.jpg',
            },
            store: {
                userId: 12
            }
        },
        xhr: [
            {
                url: '/api/users/profile?id=12',
                response: (req, res) =>
                    res.status(200).body({
                        "status": 200,
                        "explanation": null,
                        "data": {
                            "userId": 12,
                            "firstName": "Johnie",
                            "lastName": "Walker",
                            "email": "j.walker@hotmail.com",
                            "rating": 0,
                            "languages": [
                                "English"
                            ],
                            "grades": [
                                1
                            ],
                            "progressLevels": [
                                0
                            ]
                        }
                    })
            }
        ]
    },
    {
        component: Annotation,
        name: 'text-annotation-sample',
        props: {
            annotations: [sampleAnnotationList[1]],
            essay: {
                id: 4,
                sourceType: 1,
                source: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum',
            },
            store: {
                userId: 12
            }
        },
        xhr: [
            {
                url: '/api/users/profile?id=12',
                response: (req, res) =>
                    res.status(200).body({
                        "status": 200,
                        "explanation": null,
                        "data": {
                            "userId": 12,
                            "firstName": "Johnie",
                            "lastName": "Walker",
                            "email": "j.walker@hotmail.com",
                            "rating": 0,
                            "languages": [
                                "English"
                            ],
                            "grades": [
                                1
                            ],
                            "progressLevels": [
                                0
                            ]
                        }
                    })
            }
        ]
    },
];
