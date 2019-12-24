import React, {createRef, useEffect, useState} from 'react';
import {EditableAnnotation, SubjectCircle, ConnectorElbow, ConnectorEndDot, Note} from 'react-annotation';

import { connect } from "../../../../Store";

const TextAnnotation = ({ store: { userId }, annotation, setEditingAnnotation }) => {

    const [location, setLocation] = useState({});
    const [isDragging, setIsDragging] = useState(false);

    const isOwner = userId === annotation.userId;

    const title = annotation.displayTitle;

    const body = annotation.displayBody;

    const handleDragEnd = (nextProps) => {
        setTimeout(() => {
            setIsDragging(false);
        }, 10);
        setLocation({ dx: nextProps.dx, dy: nextProps.dy });
    };

    const handleToggle = (a,b,e) => {
        if (!isOwner || isDragging) {
            return;
        }
        setEditingAnnotation(annotation);
    };

    const handleDragStart = () => {
        setIsDragging(true);
    };

    const catchMouseUp = (a, b, e) => {
        e.stopPropagation();
    };

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
                    onDragStart={handleDragStart}
                    onDragEnd={handleDragEnd}
                    {...location}
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