import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { Card, Descriptions, Skeleton } from "antd";
import PropTypes from 'prop-types';

import { connect, storeType } from '../../../Store';
import { getProfileInfoByUserId } from '../../../Api/User';
import { numGradeToStrGrade } from '../../../Helpers/grades';
import { CenterView } from '../../../Layouts/index';
import "./index.css";


const User = ({ 
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

User.propTypes = {
  userId: PropTypes.number,
  firstName: PropTypes.string.isRequired,
  lastName: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  languages: PropTypes.arrayOf(PropTypes.string).isRequired,
  grades: PropTypes.arrayOf(PropTypes.number).isRequired,
  progressLevels: PropTypes.arrayOf(PropTypes.number).isRequired,
};



const UserProfile = ({ store: { userId } }) => {
  const params = useParams();
  const paramUserId = params.userId;
  if (paramUserId) {
    userId = paramUserId;
  }
  const [profile, setProfile] = useState();
  useEffect(() => {
    if (userId) {
      getProfileInfoByUserId(userId)
        .then((response) => {
          const { data = {} } = response.data || {};
          setProfile(data);
        })
        .catch(console.log);
    }
  }, [userId]);

  return (
    <CenterView>
      <Card title="Profile Page" style={styles.card}>
        <Skeleton loading={!profile} title={true} active>
          <User {...profile} />
        </Skeleton>
      </Card>
    </CenterView>
  );
}

UserProfile.propTypes = {
  store: storeType
};

const styles = {
  card: {
    maxWidth: 560,
  }
};

export default connect(UserProfile);
export { User };
