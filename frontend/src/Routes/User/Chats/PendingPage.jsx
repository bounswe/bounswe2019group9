import React, {useEffect, useState} from "react";

import {connect} from "../../../Store";
import UserView from "../UserView";
import {getInvitationsOf} from "../../../Api/Invitation";
import { Card, Spin } from "antd";

const PendingPage = ({ store: { userId } }) => {
  const [pendingUsers, setPendingUsers] = useState(null);

  useEffect(() => {
    if (userId) {
      getInvitationsOf({userId})
        .then(response => {
          const { data = [] } = response.data || {};
          setPendingUsers(data);
        }).catch(console.log)
    }
  }, [userId]);

  return (
    <div style={{
      height: "100%",
      display: "grid",
    }}>
      {Array.isArray(pendingUsers)
        ?
        (pendingUsers.length > 0
            ?
            <ul>
              {pendingUsers.map(user =>
                <Card key={user.userId} style={styles.card}><UserView {...user} isButtonPresent/></Card>
              )}
            </ul>
            :
            <Card style={{
              margin: "auto",
            }}>
              <div style={{fontSize: 18}}>
                <div>No new chat invitations!</div>
              </div>
            </Card>
        )
        :
        <Spin size={"large"} style={{margin: "auto"}}/>
      }
      </div>
  );
};

const styles = {
  card: {
    padding: 20,
    margin: 20,
  },
};


export default connect(PendingPage)