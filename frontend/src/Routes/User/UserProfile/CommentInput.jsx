import React, {useEffect, useState} from 'react';
import { Button, Form, Input } from "antd";

import {connect} from '../../../Store';
import {commentAdd} from "../../../Api/Comment";


const CommentInput = ({ store: { userId }, toUser, onSubmit }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [content, setContent] = useState("");

  const handleSubmit = () => {
    if (!content) {
      return
    }
    setIsLoading(true);
    commentAdd({ content, receiverId: toUser, sourceId: userId })
      .then(res => {
        setIsLoading(false);
        setContent("");
        onSubmit();
      })
  };

  const handleChange = e => {
    setContent(e.target.value);
  };

  return (
    <Input.Search
      placeholder="Your comment..."
      onSearch={handleSubmit}
      size="large"
      enterButton="Comment"
      loading={isLoading}
      onChange={handleChange}
      onPressEnter={handleSubmit}
      value={content}
    />
  );
};

export default connect(CommentInput);