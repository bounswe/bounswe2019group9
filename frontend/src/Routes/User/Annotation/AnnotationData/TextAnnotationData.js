import AnnotationData from "./AnnotationData";

class TextAnnotationData extends AnnotationData {
    constructor({ start = 0, end = 0 } = {}) {
        super();
        this.targetType = 'text';
        this.start = start;
        this.end = end;
    }
    toDataModel() {
        const result = super.toDataModel();

        result.target.id = this.targetUrl;
        result.target.type = "Text";
        result.target.format = "text/plain";
        result.target.selector = {
            type: "FragmentSelector",
            conformsTo: "http://tools.ietf.org/rfc/rfc5147",
            value: `char=${
                this.start},${
                this.finish
            }`
        };
        return result;
    };
    clone() {
        const cloned = super.clone();
        cloned.start = this.start;
        cloned.end = this.end;
        return cloned;
    }
}

export default TextAnnotationData;