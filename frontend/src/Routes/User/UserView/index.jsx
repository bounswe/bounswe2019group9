import React from 'react';
import { Descriptions, Button } from "antd";
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";

import { numGradeToStrGrade } from '../../../Helpers/grades';
import InvitationButton from './InvitationButton';
import { connect } from './../../../Store';


const UserView = ({ store,
  email, firstName, grades, userId,
  languages, lastName, progressLevels,
  isButtonPresent = false }) => {

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
        <Descriptions.Item>
          <Link to={`/users/${userId}`}>
            <InvitationButton sourceId={store.userId} receiverId={userId}/>
          </Link>
        </Descriptions.Item>
        {isButtonPresent ? 
        <Descriptions.Item>
          <Link to={`/users/${userId}`}>
            <Button type="primary">Go to profile page!</Button>
          </Link>
        </Descriptions.Item> : null }
      </Descriptions>

      { languagesTuple.length ? languagesTuple.map( tuple => 
        <Descriptions title={tuple[0]} key={tuple[0]}>
          <Descriptions.Item label="Grade">
            {numGradeToStrGrade(tuple[1])}
          </Descriptions.Item>
          <Descriptions.Item label="Progress Level">
            {tuple[2]}
          </Descriptions.Item>
        </Descriptions>
      ) : <p>No languages found.</p> }
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
  isButtonPresent: PropTypes.bool
};

UserView.defaultProps = {
  isButtonPresent: false,
}
  
export default connect(UserView);