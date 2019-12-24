import {withContentRect} from "react-measure";
import React, {createRef, useEffect, useReducer, useState} from "react";
import ImageAnnotationData from "../AnnotationData/ImageAnnotationData";
import AnnotationDataFactory from "../AnnotationData/AnnotationDataFactory";
import ImageAnnotation from './ImageAnnotation';

const ImageAnnotationContainer =
    ({ measureRef, contentRect, source, annotations, dispatch, user, onSaveAnnotation, setEditingAnnotation }) => {
        const { width: realWidth, height: realHeight, left, top } = contentRect.bounds;
        ImageAnnotationData.realWidth = realWidth;
        ImageAnnotationData.realHeight = realHeight;

        const [imageReady, setImageReady] = useState(false);

        const updateNaturalDimensions = (imageRef) => {
            if (imageRef) {
                const handleImageLoad = () => {
                    ImageAnnotationData.naturalWidth = imageRef.naturalWidth || realWidth;
                    ImageAnnotationData.naturalHeight = imageRef.naturalHeight || realHeight;
                    imageRef.removeEventListener('load', handleImageLoad);
                    setImageReady(true);
                    console.log('hulog');
                };
                imageRef.addEventListener('load', handleImageLoad);
            }
        };

        const handleAddAnnotation = (e) => {
            const { clientX, clientY } = e;
            const annotation = new ImageAnnotationData({ displayX: clientX - left, displayY: clientY - top });
            annotation.setUser(user);
            annotation.setTarget(source);
            dispatch({ type: 'newAnnotation', data: [...annotations, annotation] });
        };

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
                                isOwner={annotation.userId === user.userId}
                                onSaveAnnotation={onSaveAnnotation}
                                setEditingAnnotation={setEditingAnnotation}
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