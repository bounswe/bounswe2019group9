import React, {useState} from 'react';
import {EditableAnnotation, SubjectRect, ConnectorElbow, ConnectorEndDot, Note} from 'react-annotation';
import { connect } from "../../../../Store";

const ImageAnnotation = ({ annotation, isOwner, onSaveAnnotation, setEditingAnnotation }) => {

    const title = annotation.displayTitle;

    const body = annotation.displayBody;

    const x = annotation.displayX;

    const y = annotation.displayY;

    const width = -annotation.displayWidth;

    const height = -annotation.displayHeight;

    const handleDragEnd = (nextProps) => {
        const difference = [nextProps.x - (x-width), nextProps.y - (y-height), nextProps.width - width, nextProps.height - height]
            .map((x) => Math.abs(x))
            .reduce((a, b) => a + b);
        console.log('difference', difference);
        if (difference > 0.1) {
            console.log('anno', annotation);
            const nextAnnotation = annotation.clone();
            nextAnnotation.displayX = nextProps.x;
            nextAnnotation.displayY = nextProps.y;
            nextAnnotation.displayWidth = -nextProps.width;
            nextAnnotation.displayHeight = -nextProps.height;
            nextAnnotation.setModified();
            onSaveAnnotation(annotation, nextAnnotation)
        }
    };

    const handleToggle = (...args) => {
        console.log('event click', ...args);
        if (!isOwner) {
            return;
        }
        setEditingAnnotation(annotation);
    };

    return (
        <EditableAnnotation
            x={x - width}
            y={y - height}
            dx={40}
            dy={20 -height/2}
            color={"#9610ff"}
            title={title}
            label={body}
            className="show-bg no-select"
            events={{
                onClick: handleToggle
            }}
            onDragEnd={handleDragEnd}
            width={width}
            height={height}
        >
            <SubjectRect fill={"ddddff"} fillOpacity={0.4} editMode={isOwner} />
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
    );
};

export default ImageAnnotation;