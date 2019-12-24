import React, {useEffect, useReducer, useState} from 'react';
import {TextAnnotator} from "react-text-annotate-ev";
import TextAnnotation from './TextAnnotation';
import TextAnnotationData from "../AnnotationData/TextAnnotationData";


const TextAnnotationContainer = ({ source, annotations, dispatch, user, targetUrl, setEditingAnnotation }) =>  {

    const handleRemoveAnnotation = (annotation) => {
        dispatch({ type: 'removeAnnotation', data: annotation });
    };

    const handleAddAnnotation = (nextAnnotations) => {
        if (nextAnnotations.length > annotations.length) {
            dispatch({ type: 'newAnnotation', data: nextAnnotations });
        }
    };

    const getNewAnnotation = ({ start, end }) => {
        const annotation = new TextAnnotationData({ start, end });
        annotation.setUser(user);
        annotation.setTarget(targetUrl);
        annotation.tag = (
            <TextAnnotation
                annotation={annotation}
                onRemoveAnnotation={handleRemoveAnnotation}
                setEditingAnnotation={setEditingAnnotation}
            />
        );
        return annotation;
    };

    return (
        <div style={styles.container}>
            <TextAnnotator
              style={{ lineHeight: 1.5 }}
              content={source}
              value={annotations}
              onChange={handleAddAnnotation}
              getSpan={getNewAnnotation}
            />
        </div>
    );

};

const styles = {
    container: {
        margin: 32,
        padding: 16,
        backgroundColor: '#fff',
        border: '1px solid #000'
    }
};

export default TextAnnotationContainer;