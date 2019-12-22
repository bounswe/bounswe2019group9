import React, { useEffect, useState } from 'react';
import { MessageList, Input, Button } from 'react-chat-elements';
import moment from 'moment';
import { Layout } from 'antd';

import { connect, storeType } from '../../../Store';
import { getChatBetween, createMessage } from '../../../Api/Chat';
import "./index.css";



const Chat = ({ store: { userId }, otherUserId }) => {
  const [msgs, setMsgs] = useState([])

  const handleChatResponse = (response) => {
    const { data = {} } = response.data || {};
    setMsgs(data.map(msg => ({
      id: msg.id,
      position: (msg.sourceId === userId ? 'right' : 'left'),
      type: "text",
      text: msg.content,
      dateString: moment(msg.createdAt).fromNow(),
    })));
  }

  useEffect(() => {
    if (otherUserId) {
      getChatBetween({userId1: userId, userId2: otherUserId})
        .then((response) => {
          handleChatResponse(response);
        })
        .catch(console.log);
      }
  }, [otherUserId, userId]);

  const [input, setInput] = useState("");
  const [inputDisabled, setInputDisabled] = useState("false");
  const handleChange = (e) => {
    setInput(e.target.value);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    if (input !== "") {
      setInputDisabled("true")
      createMessage({
        content: input,
        receiverId: otherUserId,
        sourceId: userId,
      }).then(handleChatResponse)
      .then(() => {
        setInputDisabled("false");
        setInput("");
        console.log("done");
      }).catch(console.log);
    }
  };

  return (
    <Layout style={{height: '100%'}}>
      { otherUserId && <>
      <Layout.Content>
        <MessageList className='message-list'
          lockable={true}
          toBottomHeight={'100%'}
          dataSource={msgs} />
      </Layout.Content>
      <Layout.Footer>
        <form onSubmit={handleSubmit}>
        <Input 
          autofocus="true"
          onChange={handleChange}
          placeholder="Type here..."
          rightButtons={
              <Button
                disabled={inputDisabled}
                color='white'
                backgroundColor='black'
                text='Send'/>} />
        </form>
      </Layout.Footer></>
    }
    </Layout>
  );
}


export default connect(Chat);


