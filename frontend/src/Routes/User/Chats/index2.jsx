import React, { useEffect, useState } from 'react';
import { MessageBox, ChatList } from 'react-chat-elements';
import moment from 'moment';

import { connect, storeType } from '../../../Store';
import { getConversationsOf, getMessagesByUserId } from '../../../Api/Chat';
import "./index.css";

const AVATAR_SIZE = 40;

const ConversationList = ({ convs }) => {

  return (
    <>
      <ChatList 
        className='chat-list'
        dataSource={convs} /> 
      <ul>
        {Array.isArray(convs) && convs.map(conv => <li>{conv.userId}</li>)}
      </ul>
    </>
  );
}

const Chat = ({ store: { userId }}) => {
  const [contactedUsers, setContactedUsers] = useState([]);
  useEffect(() => {
    if (userId) {
      getConversationsOf(userId)
        .then((response) => {
          const { data = {} } = response.data || {};
          setContactedUsers(data);
        })
        .catch(console.log);
    }
  }, [userId]);

  const [chats, setChats] = useState([]);
  useEffect(() => {
    if (userId) {
      getMessagesByUserId(userId)
        .then((response) => {
          const { data = {} } = response.data || {};
          console.log(data);
          const messagesByUserId = data
            .map(msg => ({
              ...msg, 
              createdAt: moment(msg.createdAt),
            }))
            .reduce((acc, msg) => ({
            ...acc,
            [msg.sourceId === userId ? msg.receiverId : msg.sourceId]: [
              ...(acc[msg.sourceId === userId ? msg.receiverId : msg.sourceId] || []),
              msg
            ].sort((a, b) => b.createdAt - a.createdAt)
          }), {});
          setChats(messagesByUserId);
        })
        .catch(console.log);
    }
  }, [userId])

  const [chatList, setChatList] = useState([]);
  useEffect(() => {
    if (contactedUsers.length > 0) {
      setChatList(contactedUsers.map(user => {
        const chat = chats[user.userId][0];
        console.log(chat);
        return {
          avatar: `https://api.adorable.io/avatars/${AVATAR_SIZE}/${user.userId}`,
          title: `${user.firstName} ${user.lastName}`,
          subtitle: chat.content,
          date: chat.createdAt,
        }
      }))
    }
  }, [contactedUsers, chats])

  return (
    <div>
      <h1>CHAT!!</h1>
      <ConversationList convs={chatList}/>
    </div>
  );
}


export default connect(Chat);