import TextAnnotationData from "./TextAnnotationData";
import ImageAnnotationData from "./ImageAnnotationData";

class AnnotationDataFactory {
    static fromDataModel = (annotation) => {
        let annotationData;
        if (annotation.target.type === 'Image') {
            annotationData = new ImageAnnotationData();
            const [x,y,width,height] = annotation.target.selector.value.substr(5).split(',');
            annotationData.originalX = parseInt(x, 10);
            annotationData.originalY = parseInt(y, 10);
            annotationData.originalWidth = parseInt(width, 10);
            annotationData.originalHeight = parseInt(height, 10);
        } else if (annotation.target.type === 'Text') {
            annotationData = new TextAnnotationData();
            const [start, end] = annotation.target.selector.value.substr(5).split(',');
            annotationData.start = parseInt(start, 10);
            annotationData.end = parseInt(end, 10);
        } else {
            throw new Error('Unknown annotation target type, must be "Image" or "Text"');
        }
        annotationData.id = (/(\d+)$/.exec(annotation.id) || [])[1];
        annotationData.userId = parseInt((/(\d+)$/.exec(annotation.creator.id) || [])[1], 10);
        annotationData.userName = annotation.creator.name;
        annotationData.userEmail = annotation.creator.email;
        annotationData.createdAt = new Date(annotation.created);
        annotationData.modifiedAt = new Date(annotation.modified);
        annotationData.body = annotation.body.value;
        annotationData.motivation = annotation.motivation;
        annotationData.targetUrl = annotation.target.id;
        return annotationData;
    };
}

export default AnnotationDataFactory;
export const fromDataModel = AnnotationDataFactory.fromDataModel;