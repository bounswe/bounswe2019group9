import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import {Link, useLocation} from 'react-router-dom';
import {Menu, Icon} from 'antd';

import {connect, storeType, updateStore} from '../../../Store';
import {getUserById} from '../../../Api/User';

const logOut = () => {
  updateStore({
    token: '',
    userId: ''
  });
};

const routes = [
  {
    name: 'Home',
    route: '/home'
  },
  {
    name: 'Sample Page',
    route: '/sample'
  },
  {
    name: 'Select Language',
    route: '/language-select'
  }
];

const UserNavbar = ({ store: { userId } }) => {
  const { pathname } = useLocation();
  const [user, setUser] = useState();
  useEffect(() => {
    if (userId) {
      getUserById(userId)
        .then((response) => {
          const { data = {} } = response.data || {};
          setUser(data);
        })
        .catch(console.log)
    }
  }, [userId]);

  const { firstName, lastName } = user || {};

  return (
    <>
      <Menu
        theme="dark"
        mode="horizontal"
        style={styles.menu}
        selectedKeys={[pathname]}
      >
        {
          routes.map(({ name, route }) => (
            <Menu.Item key={route}>
              { name }
              <Link to={route} />
            </Menu.Item>
          ))
        }
        <Menu.SubMenu
          style={styles.profileSubmenu}
          title={
            <span className="submenu-title-wrapper">
              <Icon type="user" />
              { firstName } { lastName}
            </span>
          }
        >
          <Menu.Item key={'/profile'}>
            Profile
            <Link to={'/profile'} />
          </Menu.Item>
          <Menu.Item onClick={logOut}>
            Log Out
          </Menu.Item>
        </Menu.SubMenu>
      </Menu>
    </>
  );
};

UserNavbar.propTypes = {
  store: storeType
};

const styles = {
  menu: {
    lineHeight: '64px'
  },
  profileSubmenu: {
    float: 'right'
  }
};

export default connect(UserNavbar);
