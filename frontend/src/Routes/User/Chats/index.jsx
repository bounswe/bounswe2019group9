import React, { useEffect, useState } from 'react';
import { MessageList, ChatList } from 'react-chat-elements';
import moment from 'moment';
import { Layout } from 'antd';

import { connect, storeType } from '../../../Store';
import { getConversationsOf, getMessagesByUserId } from '../../../Api/Chat';
import "./index.css";
import Chat from "./Chat";

const { Sider } = Layout;

const AVATAR_SIZE = 40;

const Chats = ({ store: { userId }}) => {
  const [focusUser, setFocusUser] = useState();
  const [contactedUsers, setContactedUsers] = useState([]);
  useEffect(() => {
    if (userId) {
      getConversationsOf(userId)
        .then((response) => {
          const { data = {} } = response.data || {};
          setContactedUsers(data.map(user => ({
            avatar: `https://api.adorable.io/avatars/${AVATAR_SIZE}/${user.userId}`,
            title: `${user.firstName} ${user.lastName}`,
            userId: user.userId,
          })));
        })
        .catch(console.log);
    }
  }, [userId]);

  return (
    <Layout style={{height: '100%'}}>
      <Sider width={400} theme="light">
        <ChatList 
        dataSource={contactedUsers} onClick={({ userId }) => setFocusUser(userId)}/>
      </Sider>
      <Layout.Content style={{height: '100%'}}>
        <Chat otherUserId={focusUser} />
      </Layout.Content>
    </Layout>
  );
}


export default connect(Chats);


