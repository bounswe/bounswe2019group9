import moment from 'moment';

class AnnotationData {
    static motivationTypes = [
        { value: 'assessing', label: 'Assessing' },
        { value: 'bookmarking', label: 'Bookmarking' },
        { value: 'classifying', label: 'Classifying' },
        { value: 'commenting', label: 'Commenting' },
        { value: 'describing', label: 'Describing' },
        { value: 'editing', label: 'Editing' },
        { value: 'highlighting', label: 'Highlighting' },
        { value: 'identifying', label: 'Identifying' },
        { value: 'linking', label: 'Linking' },
        { value: 'moderating', label: 'Moderating' },
        { value: 'questioning', label: 'Questioning' },
        { value: 'replying', label: 'Replying' },
        { value: 'tagging', label: 'Tagging' }
    ];
    static motivationTypeToMotivationObject = (() => {
        const motivationTypesByValue = AnnotationData.motivationTypes
            .reduce((acc, x) =>
                ({ ...acc, [x.value]: x }), {});
        return (value) => (motivationTypesByValue[value] || {});
    })();
    id = undefined;
    userId = undefined;
    userName = "";
    userEmail = "";
    body = "";
    motivation = "commenting";
    targetUrl = "";
    targetType = "";
    constructor() {
        this.createdAt = new Date();
        this.modifiedAt = this.createdAt;
        if (this.constructor === AnnotationData) {
            throw new Error('Do not use AnnotationData class directly it is abstract');
        }
    }
    get motivationObject() {
        return AnnotationData.motivationTypeToMotivationObject(this.motivation);
    }
    get displayTitle() {
        return `${this.motivationObject.label || 'New'} ~ ${
            moment(this.createdAt).fromNow()}`;
    }
    get displayBody() {
        let body = `${this.body} \n by ${this.userName} (${this.userEmail})`;
        if (this.modifiedAt != this.createdAt) {
            body += `\n last edited on ${moment(this.modifiedAt).fromNow()}`;
        }
        return body;
    }
    setModified = () => {
        this.modifiedAt = new Date();
    };
    // fields to fill when a new annotation is initialized
    setTarget = (targetUrl) => {
        this.targetUrl = targetUrl;
    };
    setUser = ({ email, firstName, lastName, userId }) => {
        this.userId = userId;
        this.userName = `${firstName} ${lastName}`;
        this.userEmail = email;
    };
    // fields to fill when a new annotation is prepared
    setMessage = (message) => {
        this.body = message;
        this.modifiedAt = new Date();
    };
    setMotivation = (motivation) => {
        this.motivation = motivation;
        this.modifiedAt = new Date();
    };
    // fields to fill when a new annotation is created on backend
    setAnnotationId = (id) => {
        this.id = id;
    };

    toDataModel() {
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
        return result;
    };
    clone() {
        const clonedData = new this.constructor();
        clonedData.id = this.id;
        clonedData.userId = this.userId;
        clonedData.userName = this.userName;
        clonedData.userEmail = this.userEmail;
        clonedData.body = this.body;
        clonedData.motivation = this.motivation;
        clonedData.targetUrl = this.targetUrl;
        clonedData.targetType = this.targetType;
        clonedData.createdAt = this.createdAt;
        clonedData.modifiedAt = this.modifiedAt;
        return clonedData;
    };
}

export default AnnotationData;