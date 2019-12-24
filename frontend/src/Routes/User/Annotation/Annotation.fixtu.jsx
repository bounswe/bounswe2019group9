import Annotation from './index';
const sampleAnnotationList = require('./sample-annotation-list');

export default [
    {
        component: Annotation,
        name: 'annotation-sample',
        props: {
            annotations: sampleAnnotationList
        },
    },
];
