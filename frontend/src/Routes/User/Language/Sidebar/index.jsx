import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {useParams} from 'react-router-dom';
import {getGrade} from '../../../../Api/Grade';
import store from '../../../../Store';

const Sidebar = () => {
  const {language} = useParams();

  const languageId = language === 'English' ? 1 : language === 'Turkish' ? 2 : language === 'Italian' ? 3 : 0;

  const [grade, setGrade] = useState();

  useEffect(() => {
    if (languageId) {
      getGrade({userId: store.userId, languageId}).then((response) => {
        const {data = {}} = response.data || {};
        setGrade(data.grade || 0);
      })
    }
  }, [language, languageId]);

  let str_grade = '';
  switch (grade) {
    case 1:
      str_grade = 'A1';
      break;
    case 2:
      str_grade = 'A2';
      break;
    case 3:
      str_grade = 'B1';
      break;
    case 4:
      str_grade = 'B2';
      break;
    case 5:
      str_grade = 'C1';
      break;
    case 6:
      str_grade = 'C2';
      break;
    default:
  }
  return (
    <div className="m-3">
      <h4>{language}</h4>
      { grade && (
        <h5> Your grade is {str_grade} </h5>
      )}
    </div>
  );
};

export default Sidebar;
