import React, {useState} from 'react';
import {EditableAnnotation, SubjectCircle, ConnectorElbow, ConnectorEndDot, Note} from 'react-annotation';

import { connect } from "../../../../Store";

const TextAnnotation = ({ store: { userId }, annotation, setEditingAnnotation }) => {

    const isOwner = userId === annotation.userId;

    const [location, setLocation] = useState({});
    const [isDragging, setIsDragging] = useState(false);

    const title = annotation.displayTitle;

    const body = annotation.displayBody;

    const handleToggle = (a,b,e) => {
        if (!isOwner) {
            console.log('not owner');
            return;
        }
        setEditingAnnotation(annotation);
        return false;
    };

    const catchMouseUp = (a, b, e) => {
        e.persist();
        e.preventDefault();
        e.stopPropagation();
        e.nativeEvent.stopImmediatePropagation();
        return false;
    };

    console.log('title', title, 'label', body);
    return (
        <span style={styles.container}>
            <svg style={styles.svg} width={1} height={1}>
                <EditableAnnotation
                    x={0}
                    y={10}
                    dx={40}
                    dy={20}
                    color={"#9610ff"}
                    title={title}
                    label={body}
                    className="show-bg no-select clickable"
                    events={{
                        onClick: handleToggle,
                        onMouseUp: catchMouseUp
                    }}
                >
                    <ConnectorElbow>
                        <ConnectorEndDot />
                    </ConnectorElbow>

                    <Note
                      align={"middle"}
                      orientation={"topBottom"}
                      bgPadding={20}
                      padding={15}
                      titleColor={"#59039c"}
                      lineType={"vertical"}
                    />
                </EditableAnnotation>
            </svg>
        </span>
    );
};

const styles = {
    container: {
        position: 'relative'
    },
    svg: {
        position: 'absolute',
        overflow: 'visible',
        zIndex: 100
    }
};

export default connect(TextAnnotation);