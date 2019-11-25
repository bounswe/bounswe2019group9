import React from 'react';
import { Descriptions } from "antd";
import PropTypes from 'prop-types';

import { numGradeToStrGrade } from '../../../Helpers/grades';


const UserView = ({ 
  email, firstName, grades, 
  languages, lastName, progressLevels }) => {

  const languagesTuple = languages.map((lang, i) => (
    [lang, grades[i], progressLevels[i]]
  ));

  return (
    <>
      <Descriptions title="User Info">
        <Descriptions.Item label="Full Name" className={"capitalize-text"}>
        {[firstName, lastName].join(' ')}
        </Descriptions.Item>
        <Descriptions.Item label="Email">{email}</Descriptions.Item>
      </Descriptions>
      { languagesTuple.length ? languagesTuple.map( tuple => 
        <Descriptions title={tuple[0]}>
        <Descriptions.Item label="Grade">
          {numGradeToStrGrade(tuple[1])}
        </Descriptions.Item>
        <Descriptions.Item label="Progress Level">
          {tuple[2]}
        </Descriptions.Item>
        </Descriptions>
      ) : <p>No languages found.</p>}
    </>
  );
}

UserView.propTypes = {
  userId: PropTypes.number.isRequired,
  firstName: PropTypes.string.isRequired,
  lastName: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  languages: PropTypes.arrayOf(PropTypes.string).isRequired,
  grades: PropTypes.arrayOf(PropTypes.number).isRequired,
  progressLevels: PropTypes.arrayOf(PropTypes.number).isRequired,
};
  
export default UserView;