import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { Form, Row, Col, Input, Button, Icon, Card, Layout } from 'antd';
import PropTypes from "prop-types";

import { connect, storeType } from '../../../Store';
import AdvancedSearch from './AdvancedSearch';
import InlineSearch from './Search';
import { search } from '../../../Api/User';
import { User } from '../UserProfile';

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
        <Row type="flex" align="center">
            <Col md={16}>
                <InlineSearch onSubmit={handleSearch}/>
                {users.map(user => (
                    <Card key={user.userId}>
                        <User {...user} />
                    </Card>
                ))}
            </Col>
        </Row>
    )
}

const styles = {
    layout: {
        maxWidth: 760,
    }
};

export default connect(Users);