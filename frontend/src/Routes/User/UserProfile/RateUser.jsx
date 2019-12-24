import React, {useEffect, useState} from 'react';
import {Avatar, Card, Comment, List, Rate, Skeleton, Text, Title} from "antd";

import {connect} from '../../../Store';
import avatarUrl from "../../../Helpers/avatar";
import moment from "moment";
import CommentInput from "./CommentInput";
import {commentsGet} from "../../../Api/Comment";
import delay from "../../../Helpers/delay";


const RateUser = ({ store: { userId }, otherUserId, onSubmit }) => {

  const [value, setValue] = useState(0);
  const [disabled, setDisabled] = useState(true);

  useEffect(() => {
    setDisabled(true);
    delay(500)
      .then(res => {
        setValue(4);
        setDisabled(false);
        onSubmit();
      });
    }, [value]);

  return (
    <Rate value={value} onChange={setValue} disabled={disabled}/>
  );
}

export default connect(RateUser);