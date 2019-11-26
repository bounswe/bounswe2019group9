import React, {useState} from 'react';
import PropTypes from 'prop-types';
import { useHistory } from 'react-router-dom';
import {Button, Card, Icon, PageHeader, Radio, Skeleton, Tag} from 'antd';
import {toast} from 'react-toastify';

import {ExercisesHelper, LanguagesHelper} from '../../../../../Helpers';

const ExerciseList = ({
  title,
  loading,
  language,
  exerciseType,
  exercises,
}) => {
  const [currentExerciseIndex, setCurrentExerciseIndex] = useState(0);
  const [answers, setAnswers] = useState([]);
  const history = useHistory();
  const currentExercise = exercises[currentExerciseIndex] || {};
  const currentAnswer = answers[currentExerciseIndex];
  const { questionBody, tags = [], imageUrl, soundUrl,
    optionA, optionB, optionC, optionD } = currentExercise;
  const options = [optionA, optionB, optionC, optionD];
  const nextExercise = (increment = 1) => {
    if (currentExerciseIndex + increment === exercises.length) {
      const correctAnswers = exercises
        .reduce((sum, { correctAnswer }, i) =>
          sum + (answers[i] === correctAnswer ? 1 : 0), 0);
      const incorrectAnswers = exercises
        .reduce((sum, { correctAnswer }, i) =>
          sum + ((answers[i] && answers[i] !== correctAnswer) ? 1 : 0), 0);
      toast.info(`You finished the ${title.toLowerCase()} with ${
        correctAnswers
      } correct answers, ${
        incorrectAnswers
      } incorrect answers and ${
        exercises.length - correctAnswers - incorrectAnswers
      } skipped!`);
      history.push(`/${language.name}`);
    } else {
      setCurrentExerciseIndex(currentExerciseIndex + increment);
    }
  };
  const answerExercise = (optionIndex) => {
    if (currentAnswer) {
      toast.error("You can't change your choice!");
    } else {
      const nextAnswers = [...answers];
      nextAnswers[currentExerciseIndex] = optionIndex + 1;
      setAnswers(nextAnswers);
    }
  };

  console.log('curans', currentAnswer, 'corans', currentExercise.correctAnswer);
  return (
    <div>
      <PageHeader
        title={title}
        subTitle={loading ? 'Loading ...' : `${
          currentExerciseIndex + 1
        } / ${exercises.length}`}
      />
      { tags.length ? (
        <div style={styles.tags}>
          <b>{ 'Tags:  ' }</b>
          {tags.map(({ tagText }) => (
            <Tag>
              { tagText }
            </Tag>
          ))}
        </div>
      ) : null}
      <Card
        loading={loading}
        title={(
          <Skeleton loading={loading} paragraph={false}>
            { questionBody }
          </Skeleton>
        )}
        actions={[
          <Button
            color="dashed"
            onClick={() => nextExercise(-1)}
            disabled={currentExerciseIndex === 0}
          >
            <Icon type="left" />
            Previous Question
          </Button>,
          <Button
            color="primary"
            onClick={() => nextExercise(1)}
          >
            <Icon type="right" />
            { currentExerciseIndex + 1 === exercises.length ? 'Finish Exercises' : 'Next Question' }
          </Button>
        ]}
      >
        {imageUrl && (
          <div>
            <img src={imageUrl} alt={`${title} ${currentExerciseIndex + 1}`}/>
          </div>
        )}
        {
          soundUrl && (
            <div style={styles.sound}>
              <audio controls>
                <source src={soundUrl} />
              </audio>
            </div>
          )
        }
        <Radio.Group value={currentAnswer}>
          {
            options.map((option, optionIndex) => (
              <Radio.Button
                key={`Option_${optionIndex}`}
                value={optionIndex + 1}
                onClick={() => answerExercise(optionIndex)}
                style={currentAnswer && (
                  optionIndex + 1 === currentExercise.correctAnswer ?
                    styles.correctAnswer : styles.incorrectAnswer
                )}
              >
                { option }
              </Radio.Button>
            ))
          }
        </Radio.Group>
      </Card>
    </div>
  )
};

ExercisesHelper.propTypes = {
  title: PropTypes.string,
  loading: PropTypes.bool,
  language: LanguagesHelper.languageType.isRequired,
  exerciseType: ExercisesHelper.exerciseTypeType,
  exercises: PropTypes.arrayOf(ExercisesHelper.exerciseContentType).isRequired
};

ExercisesHelper.defaultProps = {
  title: 'Exercises',
  loading: false,
  exerciseType: null
};

const styles = {
  correctAnswer: {
    backgroundColor: '#efe'
  },
  incorrectAnswer: {
    backgroundColor: '#fee'
  },
  tags: {
    marginBottom: 16
  },
  sound: {
    margin: '12px 0'
  }
};

export default ExerciseList;
