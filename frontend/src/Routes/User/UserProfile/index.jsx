import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { useParams } from "react-router-dom";
import { Row, Col } from "antd";

import { connect, storeType } from '../../../Store';
import { getProfileInfoByUserId } from '../../../Api/User';


const BoxedList = ({ title, keyValueList }) => {
  const styles = {
    key: {
      fontWeight: "bold",
    },
    title: {
      fontWeight: "bold",
      fontSize: "%120"
    }
  }
  return (
  <Col>
    <Row style={styles.title}>{title}</Row>
    {keyValueList.map( ({key, value}) => (
      <Row>
        <Col span={10} style={styles.key}>{key}</Col>
        <Col span={14}>{value}</Col>
      </Row>
    ))}
  </Col>
)};

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

  const languagesBoxFriendly = (languages || []).map((lang, i) => (
    [lang, grades[i], progressLevels[i]]
  )).map( tuple => ({
    title: tuple[0],
    keyValueList: [
      {
        key: "Grade",
        value: tuple[1],
      },{
        key: "Progress Level",
        value: tuple[2],
      },
    ],
  }));

  const basic = {
    title: "Basic Info",
    keyValueList: [
      {
        key: "Full Name",
        value: [firstName, lastName].join(' '),
      },
      {
        key: "Email",
        value: email,
      },
    ]
  };

  return (
    <>
      <Row type="flex" justify="center" align="middle" span={8}>
        <Col span={8}>
          <BoxedList style={styles.BoxedList} title={basic.title} keyValueList={basic.keyValueList} />
        </Col>
        <Col span={8}>
          {(languagesBoxFriendly || []).map( box => 
            <BoxedList 
              style={styles.BoxedList}
              title={box.title} 
              keyValueList={box.keyValueList} /> 
          )}
        </Col>
      </Row>
    </>
  )
}

UserProfile.propTypes = {
  store: storeType
};

const styles = {
};

export default connect(UserProfile);