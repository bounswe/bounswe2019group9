import React, {useEffect, useState} from 'react';
import {getEssayById} from "../../../Api/Essay";
import AnnotationContainer from "../Annotation";

const EssayView = ({ essayId }) => {
    const [essay, setEssay] = useState();
    useEffect(() => {
        if (essayId) {
            getEssayById(essayId)
                .then((response) => {
                    const {data = {}} = response.data || {};
                    setEssay(data);
                }).catch(console.log)
        }
    }, [essayId]);

    if (!essay) {
        return null;
    }
    return (
        <AnnotationContainer essay={essay}/>
    )
};

export default EssayView;