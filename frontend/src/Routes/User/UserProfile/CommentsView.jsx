import React, {useEffect, useState} from 'react';
import {Avatar, Card, Comment, List, Skeleton, Text, Title} from "antd";

import {connect} from '../../../Store';
import avatarUrl from "../../../Helpers/avatar";
import moment from "moment";
import CommentInput from "./CommentInput";
import {commentsGet} from "../../../Api/Comment";

/*
const data = [
  {
    comment: {
      content: "Comment text here.",
      createdAt: "2019-12-23T23:35:31.872Z",
      id: 123,
      receiverId: 23,
      sourceId: 34,
    },
    sourceFirstName: "Arda",
    sourceLastName: "Soyadı",
  },
  {
    comment: {
      content: "Comment text here.",
      createdAt: "2019-12-23T23:36:31.872Z",
      id: 124,
      receiverId: 25,
      sourceId: 54,
    },
    sourceFirstName: "Egemen",
    sourceLastName: "Göl",
  },
];
*/

const CommentsView = ({store: { userId }, ofUser}) => {
  const [comments, setComments] = useState([]);

  const getComments = () => {
    commentsGet({ userId: ofUser })
      .then(res => {
        const { data = [] } = res.data || [];
        setComments(data.map(o => ({
          author: <a href={`/users/${o.comment.sourceId}`}>{o.sourceFirstName} {o.sourceLastName}</a>,
          avatar: <Avatar src={avatarUrl(o.comment.sourceId, 32)} alt="avatar" size={32} />,
          content: o.comment.content,
          datetime: moment(o.comment.createdAt).fromNow(),
          id: o.comment.id,
        })));
      })
  };

  useEffect(() => {
    getComments();
  }, [userId]);

  const handleSubmit = () => {
    getComments();
  };

  return (
    <Card title="Comments" style={{
      maxWidth: 740,
      width: "calc(90% - 40px)",
      margin: 10,

    }}>
      <List
        className="comment-list"
        header={`${comments.length} comments`}
        itemLayout="horizontal"
        dataSource={comments}
        renderItem={item => (
          <li key={item.id}>
            <Comment
              {...item}
            />
          </li>
        )}
      />
      <Comment avatar={
        <Avatar src={avatarUrl(userId, 32)} size={32} alt="avatar" />
      } content={
        <CommentInput toUser={ofUser} onSubmit={handleSubmit} />
      } />
    </Card>
  )
};

export default connect(CommentsView)