import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import {Card, PageHeader} from 'antd';
import {connect} from '../../../../Store';
import {searchExercises} from '../../../../Api/Content';
import {ExercisesHelper, LanguagesHelper} from '../../../../Helpers';
import {CenterView} from '../../../../Layouts';
import ExerciseList from './ExerciseList';

const Exercises = ({store}) => {
  const {userId} = store;
  const {language:languageName, exerciseType: exerciseTypeRoute} = useParams();
  const exerciseType = ExercisesHelper.routeToType(exerciseTypeRoute);
  const language = LanguagesHelper.nameToLanguage(languageName);
  const [exercises, setExercises] = useState([]);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    setLoading(true);
    searchExercises({
      languageId: language.languageId,
      typeId: exerciseType.typeId
    }).then((response) => {
      const {data = []} = response.data || {};
      data.sort((a, b) => a.grade - b.grade);
      // sort from easiest to hardest
      setExercises(data);
      setLoading(false);
    });

  }, [languageName, exerciseTypeRoute]);
  return (
    <ExerciseList
      title={`${exerciseType.name} Exercises`}
      loading={loading}
      language={language}
      exerciseType={exerciseType}
      exercises={exercises}
    />
  );
};

export default connect(Exercises);
