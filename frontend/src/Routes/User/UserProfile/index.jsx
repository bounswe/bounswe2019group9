import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { Card, Descriptions, Skeleton } from "antd";

import { connect, storeType } from '../../../Store';
import { getProfileInfoByUserId } from '../../../Api/User';
import { numGradeToStrGrade } from '../../../Helpers/grades';
import { CenterView } from '../../../Layouts/index';
import "./index.css";


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

  const { email, firstName, grades, languages, lastName, progressLevels } = profile || {};

  const languagesTuple = (languages || []).map((lang, i) => (
    [lang, grades[i], progressLevels[i]]
  ));

  return (
    <CenterView>
      <Card title="Profile Page" style={styles.card}>
        <Skeleton loading={!profile} title={true} active>
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