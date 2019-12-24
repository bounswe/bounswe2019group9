import React, {useState} from 'react';
import {EditableAnnotation, SubjectRect, ConnectorElbow, ConnectorEndDot, Note} from 'react-annotation';
import { connect } from "../../../../Store";

const ImageAnnotation = ({ annotation, isOwner, onSaveAnnotation, setEditingAnnotation }) => {

    const [location, setLocation] = useState({});
    const [isDragging, setIsDragging] = useState(false);

    const title = annotation.displayTitle;

    const body = annotation.displayBody;

    const x = annotation.displayX;

    const y = annotation.displayY;

    const width = -annotation.displayWidth;

    const height = -annotation.displayHeight;

    const handleDragEnd = (nextProps) => {
        setTimeout(() => {
            setIsDragging(false);
        }, 10)
        const difference = [nextProps.x - (x-width), nextProps.y - (y-height), nextProps.width - width, nextProps.height - height]
            .map((x) => Math.abs(x))
            .reduce((a, b) => a + b);
        if (difference > 0.1) {
            annotation.displayX = nextProps.x + nextProps.width;
            annotation.displayY = nextProps.y + nextProps.height;
            annotation.displayWidth = -nextProps.width;
            annotation.displayHeight = -nextProps.height;
            const nextAnnotation = annotation.clone();
            nextAnnotation.setModified();
            onSaveAnnotation(annotation, nextAnnotation);
        }
        setLocation({ dx: nextProps.dx, dy: nextProps.dy });
    };

    const handleDragStart = () => {
        setIsDragging(true);
    };

    const handleToggle = (...args) => {
        if (!isOwner || isDragging) {
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
            className="show-bg no-select clickable"
            events={{
                onClick: handleToggle
            }}
            onDragStart={handleDragStart}
            onDragEnd={handleDragEnd}
            width={width}
            height={height}
            {...location}
        >
            <SubjectRect fill={"ddddff"} fillOpacity={0.4} editMode={isOwner} />
            <ConnectorElbow>
                <ConnectorEndDot />
            </ConnectorElbow>

            <Note
                align={"middle"}
                orientation={"topBottom"}
                bgPadding={20}
                wrap={240}
                padding={15}
                titleColor={"rgb(12, 54, 11)"}
                labelColor={'#1690ff'}
                lineType={"vertical"}
                title={title}
                label={body}
            />
        </EditableAnnotation>
    );
};

export default ImageAnnotation;