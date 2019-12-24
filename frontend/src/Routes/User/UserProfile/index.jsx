import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import {Card, Layout, Skeleton} from "antd";

import { connect, storeType } from '../../../Store';
import { getProfileInfoByUserId } from '../../../Api/User';
import "./index.css";
import UserView from '../UserView';
import CommentsView from "./CommentsView";
import RateUser from "./RateUser";

const UserProfile = ({ store }) => {
  const params = useParams();
  const paramUserId = params.userId;
  let userId = store.userId;
  if (paramUserId) {
    userId = paramUserId;
  }
  const [profile, setProfile] = useState();

  const updateProfile = () => {
    getProfileInfoByUserId(userId)
      .then((response) => {
        const { data = {} } = response.data || {};
        setProfile(data);
      })
      .catch(console.log);
  }

  useEffect(() => {
    if (userId) {
      updateProfile();
    }
  }, [userId]);

  const handleRate = () => {
    updateProfile();
  }

  return (
    <Layout style={styles.layout}>
      <Card title="Profile Page" style={styles.card}>
        <Skeleton loading={!profile} title={true} active>
          <UserView {...profile} />
        </Skeleton>
      </Card>

      { store.userId !== params.userId ?
        <Card style={styles.card}>
          { profile ?
            <span>Rate this user: <br/> <RateUser otherUserId={paramUserId} onSubmit={handleRate}/></span>
            : null }
        </Card> : null }


      <CommentsView ofUser={paramUserId} />

    </Layout>
  );
};

UserProfile.propTypes = {
  store: storeType
};

const styles = {
  layout: {
    height: '100%',
    alignItems: 'center',
    overflow: "auto",
    padding: 16,
  },
  card: {
    maxWidth: 740,
    width: "calc(90% - 40px)",
    margin: 10,
  }
};

export default connect(UserProfile);


