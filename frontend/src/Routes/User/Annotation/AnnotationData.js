class AnnotationData {
    constructor() {
        this.id = undefined;
        this.userId = undefined;
        this.userName = "";
        this.userEmail = "";
        this.createdAt = new Date();
        this.modifiedAt = this.createdAt;
        this.body = "";
        this.motivation = "commenting";
        this.essayId = "";
        this.targetUrl = "";
        this.targetType = "";
        this.targetPosition = {};
    }
    static fromDataModel = (annotation) => {
        const annotationData = new AnnotationData();
        annotationData.id = (/(\d+)$/.exec(annotation.id) || [])[1];
        annotationData.userId = (/(\d+)$/.exec(annotation.creator.id) || [])[1];
        annotationData.userName = annotation.creator.name;
        annotationData.userEmail = annotation.creator.email;
        annotationData.createdAt = new Date(annotation.created);
        annotationData.modifiedAt = new Date(annotation.modified);
        annotationData.body = annotation.body.value;
        annotationData.motivation = annotation.motivation;
        annotationData.targetType = annotation.target.type.toLowerCase();
        annotationData.targetUrl = annotation.target.id;
        if (annotationData.targetType === 'image') {
            const [x,y,width,height] = annotation.target.selector.value.substr(5).split(',');
            annotationData.targetPosition = {
                x: parseFloat(x),
                y: parseFloat(y),
                width: parseFloat(width),
                height: parseFloat(height),
            }
        } else if (annotationData.targetType === 'text') {
            const [start, end] = annotation.target.selector.value.subtr(5).split(',');
            annotationData.targetPosition = {
                start: parseFloat(start),
                end: parseFloat(end)
            }
        } else {
            throw new Error('unknown target type');
        }

    };

    toDataModel = () => {
        const result = {};
        result['@context'] = "http://www.w3.org/ns/anno.jsonld";
        if(this.id) {
            result.id = `https://api.bounswe2019group9.tk/annotations?id=${this.id}`;
        }
        result.type = "Annotation";
        result.creator = {
            id: `https://bounswe2019group9.tk/users/${this.userId}`,
            name: this.userName,
            email: this.userEmail
        };
        result.created = this.createdAt.toISOString();
        result.modified = this.modifiedAt.toISOString();
        result.body = {
            type: "TextualBody",
            value: this.body,
            format: "text/plain"
        };
        result.motivation = this.motivation;
        result.target = {};
        if (this.targetType === "image") {
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
                    this.targetPosition.x},${
                    this.targetPosition.y},${
                    this.targetPosition.width},${
                    this.targetPosition.height}`
            };
        } else if (this.targetType === "text") {
            if (this.targetUrl) {
                result.target.id = this.targetUrl;
            } else {
                result.target.id = `https://api.bounswe2019group9.tk/essays?id=${this.essayId}`;
                // temporary id until a full text endpoint is ready.
            }
            result.target.type = "Text";
            result.target.format = "text/plain";
            result.target.selector = {
                type: "FragmentSelector",
                conformsTo: "http://tools.ietf.org/rfc/rfc5147",
                value: `char=${
                    this.targetPosition.start},${
                    this.targetPosition.finish
                }`
            };
        } else {
            throw new Error('Unknown targetType, cant export');
        }
    }
}