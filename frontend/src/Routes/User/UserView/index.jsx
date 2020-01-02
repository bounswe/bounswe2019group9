import React, {useEffect, useState} from 'react';
import {Descriptions, Button, Rate, Avatar} from "antd";
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";

import { numGradeToStrGrade } from '../../../Helpers/grades';
import InvitationButton from './InvitationButton';
import { connect } from './../../../Store';
import delay from "../../../Helpers/delay";
import avatarUrl from "../../../Helpers/avatar";


const UserView = ({ store,
  firstName, grades, userId,
  languages, lastName, progressLevels,
  isButtonPresent = false }) => {

  const [rate, setRate] = useState(0);
  useEffect(() => {
    delay(1200).then(response => {
      const value = 3.7;
      const new_rate = Math.round(value*2) / 2;
      setRate(new_rate)
    });
  }, [userId]);

  const languagesTuple = languages.map((lang, i) => (
    [lang, grades[i], progressLevels[i]]
  ));

  return (
    <>
      <Descriptions title="User Info"  column={isButtonPresent ? 4 : undefined}>
        <Descriptions.Item>
          <Avatar src={avatarUrl(userId, 60)} alt="Avatar" size={60} />
        </Descriptions.Item>
        <Descriptions.Item label="Full Name" className={"capitalize-text"}>
          {[firstName, lastName].join(' ')}
        </Descriptions.Item>
        <Descriptions.Item label="Rating">
          <Rate allowHalf disabled value={rate} />
        </Descriptions.Item>
        { store.userId !== userId ?
          <Descriptions.Item>
            <Link to={`/users/${userId}`}>
              <InvitationButton sourceId={store.userId} receiverId={userId}/>
            </Link>
          </Descriptions.Item> : null }
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
  email: PropTypes.string,
  languages: PropTypes.arrayOf(PropTypes.string).isRequired,
  grades: PropTypes.arrayOf(PropTypes.number).isRequired,
  progressLevels: PropTypes.arrayOf(PropTypes.number).isRequired,
  isButtonPresent: PropTypes.bool,
  rating: PropTypes.number
};

UserView.defaultProps = {
  isButtonPresent: false,
}
  
export default connect(UserView);
