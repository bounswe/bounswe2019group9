import React, {createRef} from 'react';
import {withContentRect} from "react-measure";
import {TextAnnotator} from "react-text-annotate";


const ImageAnnotation = withContentRect('bounds')(
    ({ measureRef, measure, contentRect, source, annotations }) => {
        const imgRef = createRef();
        const naturalWidth = (imgRef.current || {}).naturalWidth || 1;
        const naturalHeight = (imgRef.current || {}).naturalHeight || 1;
        return (
            <div measureRef={measureRef} onClick={}>
                <img src={source} ref={imgRef} title="Essay" alt="Essay" />
                { annotations.map((annotation) => {

                }) }
            </div>
        );
    }
);

const TextAnnotation = ({ source, annotations }) => {
    return (
        <TextAnnotator

        />
    )
};

const AnnotationContainer = ({ essay: { sourceType, source }, annotations }) => {

    if (sourceType == 2) {
        return (
            <ImageAnnotation source={source} annotations={annotations} />
        );
    } else if (sourceType == 1) {
        return (
            <TextAnnotation source={source} annotations={annotations} />
        );
    }
    return null;
}

/*

const AnnotationContainer = ({ annotations }) => {
    return (
        <div style={{display: 'flex', justifyContent: 'space-around'}}>
            { annotations.map((annot) => (
                <pre key={annot.id}>
                    {JSON.stringify(annot, null, 2)}
                </pre>
            ))}
        </div>
    )
};

export default AnnotationContainer;*/
