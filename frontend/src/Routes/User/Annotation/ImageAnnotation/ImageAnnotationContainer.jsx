import {withContentRect} from "react-measure";
import React, {createRef, useEffect, useReducer, useState} from "react";
import ImageAnnotationData from "../AnnotationData/ImageAnnotationData";
import AnnotationDataFactory from "../AnnotationData/AnnotationDataFactory";
import ImageAnnotation from './ImageAnnotation';

import './index.css';

const ImageAnnotationContainer =
    ({ measureRef, measure, contentRect, source, annotations: modelDatas, user, targetUrl }) => {
        const { width: realWidth, height: realHeight, left, top } = contentRect.bounds;
        ImageAnnotationData.realWidth = realWidth;
        ImageAnnotationData.realHeight = realHeight;

        const [imageReady, setImageReady] = useState(false);

        const updateNaturalDimensions = (imageRef) => {
            if (imageRef) {
                ImageAnnotationData.naturalWidth = imageRef.naturalWidth || realWidth;
                ImageAnnotationData.naturalHeight = imageRef.naturalHeight || realHeight;
                setImageReady(true);
            }
        };

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

        const handleAddAnnotation = (e) => {
            const { clientX, clientY } = e;
            const annotation = new ImageAnnotationData({ displayX: clientX - left, displayY: clientY - top });
            annotation.setUser(user);
            annotation.setTarget(targetUrl);
            dispatch({ type: 'newAnnotation', data: [...annotations, annotation] });
        };

        // only run once
        useEffect(() => {
            const initialAnnotations = (modelDatas || []).map(AnnotationDataFactory.fromDataModel);
            dispatch({ type: 'initialize', data: initialAnnotations });
        }, [modelDatas]);

        return (
            <div ref={measureRef} style={styles.container}>
                <img
                    src={source}
                    ref={updateNaturalDimensions}
                    title="Essay"
                    alt="Essay"
                    onClick={handleAddAnnotation}
                />
                { imageReady ? (
                    <svg style={styles.annotationContainer} width={1} height={1}>
                        { annotations.map((annotation, index) => (
                            <ImageAnnotation
                                key={annotation.id || `anno_${index}`}
                                annotation={annotation}
                                onRemoveAnnotation={handleRemoveAnnotation}
                            />
                        )) }
                    </svg>
                ) : null}
            </div>
        );
    };

const styles = {
    container: {
        position: 'relative'
    },
    image: {
        maxWidth: '100%'
    },
    annotationContainer: {
        position: 'absolute',
        overflow: 'visible',
        top: 0,
        left: 0,
        zIndex: 100,
    }
};

export default withContentRect('bounds')(ImageAnnotationContainer);