import React from 'react';

const AnnotationContainer = ({ annotations }) => {
    return (
        <div style={{display: 'flex', justifyContent: 'space-around'}}>
            { annotations.map((annot) => (
                <pre key={annot.id}>
                    {JSON.stringify(annot, null, 2)}
                </pre>
            ))}
        </div>
    )
};

export default AnnotationContainer;