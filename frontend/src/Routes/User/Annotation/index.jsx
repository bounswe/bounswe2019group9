import React, {createRef, useEffect, useReducer, useState} from 'react';

import ImageAnnotationContainer from "./ImageAnnotation/ImageAnnotationContainer";
import TextAnnotationContainer from "./TextAnnotation/TextAnnotationContainer";

import { connect } from "../../../Store";
import {getProfileInfoByUserId} from "../../../Api/User";
import AnnotationDataFactory from "./AnnotationData/AnnotationDataFactory";
import AnnotationModal from "./AnnotationModal";
import TextAnnotation from "./TextAnnotation/TextAnnotation";

import './index.css';
import {createAnnotation, deleteAnnotationById, searchAnnotations, updateAnnotation} from "../../../Api/Annotation";

const AnnotationContainer = ({ essay, store: { userId } }) => {
    const [profile, setProfile] = useState();
    useEffect(() => {
        console.log('userId', userId);
        if (userId) {
            getProfileInfoByUserId(userId)
                .then((response) => {
                    const { data = {} } = response.data || {};
                    setProfile(data);
                })
                .catch(console.log);
        }
    }, [userId]);

    const { id: essayId, assignmentId, authorId, sourceType, source } =  essay;

    const targetUrl = sourceType === 2 ? source : `https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=${essayId}`;

    const [editingAnnotation, setEditingAnnotation] = useState(null);

    const [annotations, dispatch] = useReducer((prevState, action) => {
        switch (action.type) {
            case 'initialize':
                return action.data;
            case 'newAnnotation':
                setEditingAnnotation(action.data[action.data.length - 1]);
                return action.data;
            case 'removeAnnotation':
                if (editingAnnotation === action.data) {
                    setEditingAnnotation(null);
                }
                return prevState.filter((annotation) => annotation !== action.data);
            case 'updateAnnotation':
                if (editingAnnotation === action.data.annotation) {
                    setEditingAnnotation(action.data.nextAnnotation);
                }
                return prevState.map((annotation) =>
                    annotation === action.data.annotation ? action.data.nextAnnotation : annotation
                );
            default:
                return prevState;
        }
    }, []);

    useEffect(() => {
        if (targetUrl) {
            searchAnnotations(targetUrl)
                .then((response) => {
                    const { data = [] } = response.data || {};
                    let initialAnnotations = (data || [])
                        .map((x) => JSON.parse(x))
                        .map(AnnotationDataFactory.fromDataModel);
                    if (sourceType === 1) {
                        initialAnnotations = initialAnnotations.map((annotation => {
                            annotation.tag = (
                                <TextAnnotation
                                    annotation={annotation}
                                    onRemoveAnnotation={handleRemoveAnnotation}
                                    setEditingAnnotation={setEditingAnnotation}
                                />
                            );
                            return annotation;
                        }));
                    }
                    dispatch({ type: 'initialize', data: initialAnnotations });
                }).catch(console.log);
        }
    }, [targetUrl]);

    const handleSaveAnnotation = (annotation, nextAnnotation) => {
      const isNew = !annotation.id;
      let dataModel = nextAnnotation.toDataModel();
      delete dataModel.id;
      dataModel = JSON.stringify(dataModel);
      let request;

      if (isNew) {
          request = createAnnotation({annotation: dataModel});
      } else {
          request = updateAnnotation({ id: annotation.id, annotation: dataModel });
      }
      request
          .then((response) => {
              let { data } = response.data;
              data = JSON.parse(data);
              nextAnnotation.setIdFromLink(data.id);
              if (sourceType === 1) {
                  nextAnnotation.tag = (
                      <TextAnnotation
                          annotation={nextAnnotation}
                          onRemoveAnnotation={handleRemoveAnnotation}
                          setEditingAnnotation={setEditingAnnotation}
                      />
                  );
              }
              dispatch({ type: 'updateAnnotation', data: { annotation, nextAnnotation }});
          }).catch(console.log);
    };

    const handleRemoveAnnotation = (annotation) => {
      const isNew = !annotation.id;
      if (isNew) {
         return dispatch({ type: 'removeAnnotation', data: annotation });
      } else {
          deleteAnnotationById(annotation.id)
              .then((response) => {
                  dispatch({ type: 'removeAnnotation', data: annotation });
              }).catch(console.log);
      }
    };

    const ChosenAnnotationContainer = sourceType == 2
        ? ImageAnnotationContainer : sourceType == 1
        ? TextAnnotationContainer : null;

    return (
        <div>
            { ChosenAnnotationContainer && (
                <ChosenAnnotationContainer
                    source={source}
                    annotations={annotations}
                    dispatch={dispatch}
                    user={profile || {}}
                    targetUrl={targetUrl}
                    onSaveAnnotation={handleSaveAnnotation}
                    setEditingAnnotation={setEditingAnnotation}
                />
            ) }
            <AnnotationModal
                annotation={editingAnnotation}
                setEditingAnnotation={setEditingAnnotation}
                onSaveAnnotation={handleSaveAnnotation}
                onRemoveAnnotation={handleRemoveAnnotation}
            />
        </div>
    )
};

export default connect(AnnotationContainer)
