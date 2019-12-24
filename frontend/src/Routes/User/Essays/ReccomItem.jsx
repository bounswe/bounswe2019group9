import React, {useState} from "react";
import {addRequest} from "../../../Api/Request";
import {Button, Descriptions, List, Rate} from "antd";
import {connect} from "../../../Store";

const ReccomItem = ({store, userId, firstName, grade,
                      lastName, progressLevel, rating, essayId}) => {
  const [sent, setSent] = useState(false);
  const [isloading, setLoading] = useState(false)

  const handleClick = () => {
    setLoading(true);
    addRequest({ sourceId: store.userId, receiverId: userId, essayId})
      .then(res => {
        setSent(true);
        setLoading(false);
      })
  };

  return (
    <List.Item>
      <Descriptions
        title="Recommendations"
        bordered
        column={1}
      >
        <Descriptions.Item label="Name">{firstName} {lastName}</Descriptions.Item>
        <Descriptions.Item label="Rating">
          <Rate allowHalf disabled defaultValue={Math.round(rating*2)/2} />
        </Descriptions.Item>
      </Descriptions>
      <Button
        onClick={handleClick}
        disabled={sent}
        loading={isloading}
      >{!sent ? "Send Request for Review" : "Request has been sent"}</Button>
    </List.Item>
  );
};

export default connect(ReccomItem);