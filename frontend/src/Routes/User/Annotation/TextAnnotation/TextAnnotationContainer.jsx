import React, {useEffect, useReducer, useState} from 'react';
import {TextAnnotator} from "react-text-annotate";
import AnnotationDataFactory from "../AnnotationData/AnnotationDataFactory";
import TextAnnotation from './TextAnnotation';
import TextAnnotationData from "../AnnotationData/TextAnnotationData";

import './index.css';

const TextAnnotationContainer = ({ source, annotations: modelDatas, user, targetUrl }) => {
    const [annotations, dispatch] = useReducer((prevState, action) => {
       switch (action.type) {
           case 'initialize':
               return action.data;
           case 'newAnnotation':
               return action.data.length > prevState.length ? action.data : prevState;
           case 'removeAnnotation':
               return prevState.filter((annotation) => annotation !== action.data);
           default:
               return prevState;
       }
    }, []);

    const handleRemoveAnnotation = (annotation) => {
        dispatch({ type: 'removeAnnotation', data: annotation });
    };

    const handleAddAnnotation = (nextAnnotations) => {
        dispatch({ type: 'newAnnotation', data: nextAnnotations });
    };

    // only run once
    useEffect(() => {
        const initialAnnotations = (modelDatas || []).map(AnnotationDataFactory.fromDataModel).map((annotation => {
            annotation.tag = (
                <TextAnnotation annotation={annotation} onRemoveAnnotation={handleRemoveAnnotation} />
            );
            return annotation;
        }));
        dispatch({ type: 'initialize', data: initialAnnotations });
    }, []);

    const getNewAnnotation = ({ start, end }) => {
        const annotation = new TextAnnotationData({ start, end });
        annotation.setUser(user);
        annotation.setTarget(targetUrl);
        annotation.tag = (
            <TextAnnotation annotation={annotation} onRemoveAnnotation={handleRemoveAnnotation} />
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