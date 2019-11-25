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
    <Row type="flex" align="middle">
      <Col md={16}>
        <InlineSearch onSubmit={handleSearch}/>
        {users.map(user => (
          <Card key={user.userId}>
            <UserView {...user} />
          </Card>
        ))}
      </Col>
    </Row>
  )
}

export default connect(Users);
