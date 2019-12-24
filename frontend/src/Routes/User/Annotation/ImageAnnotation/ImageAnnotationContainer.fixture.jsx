import ImageAnnotationContainer from "./ImageAnnotationContainer";

const sampleAnnotationList = require('../sample-annotation-list');

export default [
    {
        component: ImageAnnotationContainer,
        name: 'image-annotation-sample',
        props: {
            source: 'http://1.bp.blogspot.com/-tHh203WU8kI/UxZtbYH2nbI/AAAAAAAANNk/5NjxRD7zOac/s1600/jrst.jpg',
            annotations: [sampleAnnotationList[0]],
            user: {
                firstName: 'John',
                lastName: 'Johnwell',
                email:'john@johnwell.com',
                userId: '15'
            },
            targetUrl: 'https://google.com/'
        },
    },
];
