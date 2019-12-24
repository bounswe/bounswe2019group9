import React, {useEffect, useState} from 'react';
import {connect} from "../../../Store";
import {Button, Col, Descriptions, Layout, List, Modal, Rate, Row, Spin} from "antd";
import {getRecommendations} from "../../../Api/Recommendation";
import {getProfileInfoByUserId} from "../../../Api/User";
import {idToLanguage, languages} from "../../../Helpers/languages";
import UserView from "../UserView";
import {lang} from "moment";
import delay from "../../../Helpers/delay";
import {addRequest} from "../../../Api/Request";

import ReccomItem from "./ReccomItem";



const RequestReviewModal = ({ store: { userId }, visible, onOk, languageId, onCancel, essayId }) => {

  const [profile, setProfile] = useState();
  const [gradeLangs, setGradeLangs] = useState();
  const [recs, setRecs] = useState([]);

  useEffect(() => {
    getProfileInfoByUserId(userId)
      .then(res => {
        const { data = {} } = res.data || {};
        if (Object.entries(data).length !== 0 || data.constructor === Object) {
          setProfile(data);
        }
      })
  }, [userId]);

  useEffect(() => {
    if (profile &&
      profile.hasOwnProperty("grades") && Array.isArray(profile.grades) &&
      profile.hasOwnProperty("languages") && Array.isArray(profile.languages) &&
      profile.grades.length === profile.languages.length
    ) {
      setGradeLangs(profile.languages.reduce((acc, x, index) => ({...acc, [x]: profile.grades[index]}), {}))
    } else {
      console.log("no grade")
    }
  }, [profile]);

  useEffect(() => {
    if (gradeLangs) {
      const grade = gradeLangs[idToLanguage(languageId)];
      getRecommendations({grade, languageId})
        .then(res => {
          const { data = [] } = res.data || [];
          setRecs(data);
        }).catch(console.log)
    }
  }, [gradeLangs]);

  return (
    <Modal
      visible={visible}
      onOk={onOk}
      width={600}
      onCancel={onCancel}
    >
      { recs ?
        <List>
          { recs.map(r => (
            <ReccomItem {...r} key={r.userId} essayId={essayId}/>
          ))}
        </List>
        :
        <Spin size="large" />
      }
    </Modal>
  );
};

export default connect(RequestReviewModal)