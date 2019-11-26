import React, { useState } from 'react';
import { Row, Col, Card } from 'antd';

import { connect } from '../../../Store';
import InlineSearch from './Search';
import { search } from '../../../Api/User';
import UserView from '../UserView';

const Users = () => {
  const [users, setUsers] = useState([]);

  const handleSearch = (values) => {
    console.log(values);
    search(values)
      .then( res => {
        const { data = [] } = res.data || [];
        setUsers(data);
      })
      .catch(console.log)
  }
  return (
    <Card style={styles.card} bodyStyle={styles.innerCard}>
      <InlineSearch onSubmit={handleSearch}/>
      {users.map(user => (
        <Card key={user.userId}>
          <UserView {...user} isButtonPresent />
        </Card>
      ))}
    </Card>
  )
}

const styles = {
  card: {
    padding: 20,
    margin: 20,
    height: "calc(100% - 40px)",
  },
  innerCard: {
    overflow: "auto",
    maxHeight: "100%",
  }
}

export default connect(Users);
