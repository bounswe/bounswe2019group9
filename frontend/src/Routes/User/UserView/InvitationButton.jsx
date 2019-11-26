import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Button } from "antd";
import { getInvitationState, createInvitation, answerToInvitation } from '../../../Api/Invitation';

const InvitationButton = ({sourceId, receiverId}) => {
  const msgs = {
    ready: "Send Invite",
    loading: "Loading",
    pending: "Pending",
    accept: "Accept?",
    connected: "Connected",
  };

  const [isDisabled, setDisabled] = useState(false);
  const [isLoading, setLoading] = useState(true);
  const [message, setMessage] = useState(msgs.loading);

  useEffect(() => {
    // check state from server
    // 1 is current user.
    getInvitationState({userId1: sourceId, userId2: receiverId})
      .then(res => {
        const { data = {} } = res.data || {};
        if (data.userId1 == sourceId && data.userId2 == receiverId) {
          if (data.pendingRequestFromTwoToOne && data.pendingRequestFromOneToTwo) {
            // illegal state
            console.error("Concurrent pendings.")
          } else if (data.startedConversation) {
            setMessage(msgs.connected);
            setDisabled(true);
          } else if (data.pendingRequestFromTwoToOne) {
            // Acceptable state
            setMessage(msgs.accept);
            setDisabled(false);
          } else if (data.pendingRequestFromOneToTwo) {
            // Pending state
            setMessage(msgs.pending);
            setDisabled(true);
          } else {
            // Ready state
            setMessage(msgs.ready);
            setDisabled(false);
          }
          setLoading(false);
        } else {
          // Peers do not match
          console.error("Peers do not match.");
        }
      }).catch(console.error);
  }, [sourceId]);

  const handleClick = e => {
    e.preventDefault();
    if (!isDisabled && !isLoading) {
      setLoading(true);
      setMessage(msgs.loading);
      if (message == msgs.ready) {
        createInvitation({sourceId, receiverId})
          .then(res => {
            // pending state
            const { data = {} } = res.data || {};
            setDisabled(true);
            setLoading(false);
            setMessage(msgs.pending);
          })
          .catch(console.log);
      } else if (message == msgs.accept) {
        // Will accept invitation
        answerToInvitation({sourceId, receiverId, approved: true})
          .then(res => {
            // connected state
            const { data = {} } = res.data || {};
            setDisabled(true);
            setLoading(false);
            setMessage(msgs.connected);
          })
      }
    }
  }
  return (
    <Button 
      onClick={handleClick}
      type="primary"
      loading={isLoading}
      disabled={isDisabled}>
        {message}
      </Button>
  )
}

InvitationButton.propTypes = {
    sourceId: PropTypes.number.isRequired,
    receiverId: PropTypes.number.isRequired,
}

export default InvitationButton;
