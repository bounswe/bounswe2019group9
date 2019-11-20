import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {useParams} from 'react-router-dom';
import {getGrade} from '../../../../Api/Grade';
import { connect, storeType } from '../../../../Store';
import {GradesHelper} from '../../../../Helpers';

const Sidebar = ({ store }) => {
  const {language} = useParams();

  const languageId = language === 'English' ? 1 : language === 'Turkish' ? 2 : language === 'Italian' ? 3 : 0;

  const [grade, setGrade] = useState();

  useEffect(() => {
    if (languageId) {
      getGrade({userId: store.userId, languageId}).then((response) => {
        const {data = {}} = response.data || {};
        setGrade(data.grade || 0);
      }).catch(console.log)
    }
  }, [language, languageId]);

  let str_grade = GradesHelper.numGradeToStrGrade(grade);

  return (
    <div className="m-3">
      <h4>{language}</h4>
      { grade && (
        <h5> Your grade is {str_grade} </h5>
      )}
    </div>
  );
};

Sidebar.propTypes = {
  store: storeType
};

export default connect(Sidebar);
