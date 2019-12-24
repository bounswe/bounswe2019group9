import React, {useState} from 'react';
import {EditableAnnotation, SubjectRect, ConnectorElbow, ConnectorEndDot, Note} from 'react-annotation';

const ImageAnnotation = ({ annotation }) => {
    const [editing, setEditing] = useState(!annotation.id);
    const [loading, setLoading] = useState(false);

    const [location, setLocation] = useState({ dx: 40, dy: 20 });

    const title = annotation.displayTitle;

    const body = annotation.displayBody;

    const x = annotation.displayX;

    const y = annotation.displayY;

    const width = annotation.displayWidth;

    const height = annotation.displayHeight;

    return (
        <EditableAnnotation
            x={width + x}
            y={height + y}
            dx={location.dx}
            dy={height/2 + location.dy}
            color={"#9610ff"}
            title={title}
            label={body}
            className="show-bg no-select"
            events={{
                onClick: (...args) => console.log('event click', ...args)
            }}
            width={-width}
            height={-height}
            cx={0}
        >
            <SubjectRect fill={"ddddff"} fillOpacity={0.4}/>
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