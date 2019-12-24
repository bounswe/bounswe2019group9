import React, {useState} from 'react';
import {EditableAnnotation, SubjectCircle, ConnectorElbow, ConnectorEndDot, Note} from 'react-annotation';

const TextAnnotation = ({ annotation }) => {
    const [editing, setEditing] = useState(!annotation.id);
    const [loading, setLoading] = useState(false);

    const [location, setLocation] = useState({ dx: 40, dy: 20 });

    const title = annotation.displayTitle;

    const body = annotation.displayBody;

    console.log('title', title, 'label', body);
    return (
        <span style={styles.container}>
            <svg style={styles.svg} width={1} height={1}>
                <EditableAnnotation
                    x={0}
                    y={10}
                    dx={location.dx}
                    dy={location.dy}
                    color={"#9610ff"}
                    title={title}
                    label={body}
                    className="show-bg no-select"
                    events={{
                        onClick: (...args) => console.log('event click', ...args)
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

export default TextAnnotation;