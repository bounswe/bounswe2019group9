import React, {useEffect, useState} from 'react';
import { Rate } from "antd";

import {connect} from '../../../Store';
import {addRating, getRatingBetween, updateRating} from "../../../Api/Rate";


const RateUser = ({ store: { userId }, otherUserId }) => {

  const [value, setValue] = useState(0);
  const [disabled, setDisabled] = useState(true);

  useEffect(() => {
    setDisabled(true);
    getRatingBetween({receiverId: otherUserId, senderId: userId })
      .then(res => {
        const {data = {}} = res.data || {};
        setValue(data);
        setDisabled(false);
      }).catch();
  }, [userId]);

  const handleChange = cur => {
    setDisabled(true);
    if (value === 0) {
      addRating({receiverId: otherUserId, senderId: userId, rating: cur})
        .then(res => {
          const {data = {}} = res.data || {};
          setValue(data);
          setDisabled(false);
        });
    } else {
      updateRating({receiverId: otherUserId, senderId: userId, rating: cur})
        .then(res => {
          const {data = {}} = res.data || {};
          setValue(data);
          setDisabled(false);
        });
    }
  };

  return (
    <Rate value={value} onChange={handleChange} disabled={disabled}/>
  );
}

export default connect(RateUser);