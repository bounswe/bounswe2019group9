import React, { useState } from 'react';
import {Icon, Tag, Collapse, Descriptions, Radio} from 'antd';

import InlineSearch from './Search';
import {searchExercises} from '../../../../../Api/Content';
import {ExercisesHelper, GradesHelper, LanguagesHelper} from '../../../../../Helpers';

const ExerciseSearch = () => {
  const [exercises, setExercises] = useState([]);

  const handleSearch = (values) => {
    searchExercises(values)
      .then( res => {
        const { data = [] } = res.data || [];
        setExercises(data);
      })
      .catch(console.log)
  }
  return (
    <div style={styles.card}>
      <div style={styles.innerCard}>
        <InlineSearch onSubmit={handleSearch}/>
        { exercises.length > 0 && (
          <Collapse accordion>
            {exercises.map(({ id, tags, typeId, languageId, questionBody, imageUrl, grade,
                              optionA, optionB, optionC, optionD, correctAnswer }) => (
              <Collapse.Panel key={id} header={
                <Descriptions column={4}>
                  <Descriptions.Item label={'Type'}>
                    { ExercisesHelper.typeIdToType(typeId).name }
                  </Descriptions.Item>
                  <Descriptions.Item label={'Language'}>
                    { LanguagesHelper.idToLanguage(languageId).name }
                  </Descriptions.Item>
                  <Descriptions.Item label={'Grade'}>
                    { GradesHelper.numGradeToStrGrade(grade) }
                  </Descriptions.Item>
                  <Descriptions.Item label={<span>
                    <Icon type="tags" />
                  </span>}>
                    { tags.map(({ id, tagText }) => (
                      <Tag key={id}>
                        { tagText }
                      </Tag>
                    ))}
                    { tags.length === 0 && 'No tags.' }
                  </Descriptions.Item>
                </Descriptions>
              }>
                { imageUrl && <img src={imageUrl} alt={imageUrl} /> }
                <div>
                  <h3>
                    { questionBody }
                  </h3>
                </div>
                <div>
                  <Radio.Group>
                    { [optionA, optionB, optionC, optionD].map((option, optionIndex) => (
                      <Radio.Button
                        key={`Option_${optionIndex}`}
                        value={optionIndex + 1}
                        style={ optionIndex + 1 === correctAnswer ? styles.correctAnswer : styles.incorrectAnswer }
                      >
                        { option }
                      </Radio.Button>
                    ))}
                  </Radio.Group>
                </div>
              </Collapse.Panel>
            ))}
          </Collapse>
        )}
      </div>
    </div>
  )
}

const styles = {
  card: {
    padding: 20,
    height: "100%",
  },
  innerCard: {
    overflow: "auto",
    maxHeight: "100%",
  },
  correctAnswer: {
    backgroundColor: '#efe'
  },
  incorrectAnswer: {
    backgroundColor: '#fee'
  },
}

export default ExerciseSearch;
