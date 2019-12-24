import React, { useEffect, useState } from 'react';
import { MessageList, ChatList } from 'react-chat-elements';
import moment from 'moment';
import {Button, Layout} from 'antd';

import { connect, storeType } from '../../../Store';
import { getConversationsOf, getMessagesByUserId } from '../../../Api/Chat';
import "./index.css";
import Chat from "./Chat";
import PendingPage from "./PendingPage"
import {ColorsHelper} from "../../../Helpers";

const { Sider, Content } = Layout;

const AVATAR_SIZE = 40;


const ViewPendingsButton = ({ onClick}) => {
    return (
      <div style={{
        width: "100%",
        height: 72,
        paddingTop: 10,
        paddingBottom: 10,
        paddingRight: 20,
        paddingLeft: 20,
      }}>
        <Button
          onClick={onClick}
          type="primary"
          shape="round"
          style={{
            height: "100%",
            width: "100%",
            backgroundColor: ColorsHelper.appColorDark,
          }}
        >View Pendings!</Button>
      </div>
    )
};


const Chats = ({store: {userId}}) => {
  const [focusUser, setFocusUser] = useState();
  const [contactedUsers, setContactedUsers] = useState([]);
  useEffect(() => {
    if (userId) {
      getConversationsOf(userId)
        .then((response) => {
          const {data = {}} = response.data || {};
          setContactedUsers(data.map(user => ({
            avatar: `https://api.adorable.io/avatars/${AVATAR_SIZE}/${user.userId}`,
            title: `${user.firstName} ${user.lastName}`,
            userId: user.userId,
          })));
        })
        .catch(console.log);
    }
  }, [userId]);

  const goPendingsPage = () => {
    setFocusUser(null);
  };

  return (
    <Layout style={{height: '100%'}}>
      <Sider width={300} collapsedWidth={72} style={{
        overflow: "auto",
      }} theme="light" collapsible>
        <ViewPendingsButton onClick={goPendingsPage}/>
        <ChatList
          className="no-horizontal-scroll"
          dataSource={contactedUsers} onClick={({userId}) => setFocusUser(userId)}/>
      </Sider>
      <Content style={{}}>
        { focusUser ?
          <Chat otherUserId={focusUser}/>
          :
          <PendingPage />
        }

      </Content>
    </Layout>
  );
}


export default connect(Chats);


