import React, {createRef, useEffect, useState} from 'react';
import { MessageList, Input } from 'react-chat-elements';
import moment from 'moment';
import { Layout, Button } from 'antd';
import { scroller, Element, Events } from "react-scroll";

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

    (new Promise((resolve, reject) => {

      Events.scrollEvent.register('end', () => {
        resolve();
        Events.scrollEvent.remove('end');
      });

      scroller.scrollTo('contentScrollable', {
        duration: 800,
        delay: 0,
        smooth: 'easeInOutQuart'
      });

    })).then(() => {
      scroller.scrollTo("scroll-to-bottom", {
        duration: 1500,
        delay: 100,
        smooth: true,
        containerId: 'contentScrollable',
        offset: 50, // Scrolls to element + 50 pixels down the page
      });
    });
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

  const inputRef = createRef();
  const [input, setInput] = useState("");
  const [inputDisabled, setInputDisabled] = useState(false);
  const handleChange = (e) => {
    setInput(e.target.value);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    if (input !== "" && !inputDisabled) {
      setInputDisabled(true)

      if(inputRef.current) {
        inputRef.current.clear();
      }
      createMessage({
        content: input,
        receiverId: otherUserId,
        sourceId: userId,
      }).then(handleChatResponse)
      .then(() => {
        setInputDisabled(false);
        setInput("");
        // console.log("done");
      }).catch(e => {
        setInputDisabled(false);
        if(inputRef.current) {
          inputRef.current.onChange({ target: { value: input } });
        }
        console.log("error", e);
      });
    }
  };

  return (
    <Layout style={{height: '100%'}}>
      { otherUserId && <>
      <Layout.Content id="contentScrollable" style={{ overflow: 'auto', position: 'relative' }}>
        <MessageList className='message-list'
          lockable={true}
          toBottomHeight={'100%'}
          dataSource={msgs} />
        <Element id="scroll-to-bottom" />
      </Layout.Content>
      <Layout.Footer >
        <form onSubmit={handleSubmit}>
        <Input
          ref={(ref) => {if(ref)inputRef.current = ref}}
          autofocus="true"
          onChange={handleChange}
          placeholder="Type here..."
          rightButtons={
              <Button
                disabled={inputDisabled}
                color='white'
                backgroundColor='black'
                htmlType={"submit"}
                >Send</Button>} />
        </form>
      </Layout.Footer></>
    }
    </Layout>
  );
}


export default connect(Chat);


