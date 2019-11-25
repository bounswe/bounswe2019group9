import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { Card, Skeleton } from "antd";

import { connect, storeType } from '../../../Store';
import { getProfileInfoByUserId } from '../../../Api/User';
import { CenterView } from '../../../Layouts/index';
import "./index.css";
import UserView from '../UserView';

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
          <UserView {...profile} />
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
