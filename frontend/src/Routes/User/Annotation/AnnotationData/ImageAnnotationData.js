import AnnotationData from './AnnotationData';

export default class ImageAnnotationData extends AnnotationData {
    // target width/height (natural is image dimension, real is displayed image dimension)
    static naturalWidth = 1;
    static naturalHeight = 1;
    static realWidth = 1;
    static realHeight = 1;
    // annotation xywh
    originalX = 0;
    originalY = 0;
    originalWidth = 1;
    originalHeight = 1;

    constructor({ displayX = 60, displayY = 30 } = {}) {
        super();
        this.targetType = 'image';
        this.displayX = displayX;
        this.displayY = displayY;
        this.displayWidth = 60;
        this.displayHeight = 30;
    }
    get displayX() {
        return this.originalX * ImageAnnotationData.realWidth / ImageAnnotationData.naturalWidth;
    }
    set displayX(displayX) {
        this.originalX = Math.round(displayX * ImageAnnotationData.naturalWidth / ImageAnnotationData.realWidth);
    }
    get displayY() {
        return this.originalY * ImageAnnotationData.realHeight / ImageAnnotationData.naturalHeight;
    }
    set displayY(displayY) {
        this.originalY = Math.round(displayY * ImageAnnotationData.naturalHeight / ImageAnnotationData.realHeight);
    }
    get displayWidth() {
        return this.originalWidth * ImageAnnotationData.realWidth / ImageAnnotationData.naturalWidth;
    }
    set displayWidth(displayWidth) {
        this.originalWidth = Math.round(displayWidth * ImageAnnotationData.naturalWidth / ImageAnnotationData.realWidth);
    }
    get displayHeight() {
        return this.originalHeight * ImageAnnotationData.realHeight / ImageAnnotationData.naturalHeight;
    }
    set displayHeight(displayHeight) {
        this.originalHeight = Math.round(displayHeight * ImageAnnotationData.naturalHeight / ImageAnnotationData.realHeight);
    }
    toDataModel() {
        const result = super.toDataModel();
        result.target.id = this.targetUrl;
        result.target.type = "Image";
        let ext = this.targetUrl.substr(
            this.targetUrl.lastIndexOf('.') + 1
        ).toLowerCase();
        if (ext.startsWith('jpeg') || ext.startsWith('jpg')) {
            ext = 'jpeg';
        } else if (ext.startsWith('png')) {
            ext = 'png';
        } else {
            ext = '*';
        }
        result.target.format = `image/${ext}`;
        result.target.selector = {
            type: "FragmentSelector",
            conformsTo: "http://www.w3.org/TR/media-frags/",
            value: `xywh=${
                this.originalX},${
                this.originalY},${
                this.originalWidth},${
                this.originalHeight}`
        };
        return result;
    };
    clone() {
        const cloned = super.clone();
        cloned.originalX = this.originalX;
        cloned.originalY = this.originalY;
        cloned.originalWidth = this.originalWidth;
        cloned.originalHeight = this.originalHeight;
        return cloned;
    };
}