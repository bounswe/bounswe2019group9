import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import {Link, useLocation} from 'react-router-dom';
import {Typography, Menu, Icon} from 'antd';

import {connect, storeType, updateStore} from '../../../Store';
import {getUserById} from '../../../Api/User';
import {ColorsHelper} from '../../../Helpers';

import AppLogo from '../../../Images/app_icon_transparent.png';

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
  },
  {
    name: 'User Search',
    route: '/users',
  },
  {
    name: 'Chat',
    route: '/chat',
  },
  {
    name: "My Essays",
    route: "/essays",
  },
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
      <div style={styles.logo}>
        <img src={AppLogo} alt="kereviz-logo" style={styles.logoImage}/>
        <Typography.Title level={3} style={styles.logoTitle}>
          Kereviz
        </Typography.Title>
      </div>
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
          <Menu.Item key={`/users/${userId}`}>
            Profile
            <Link to={`/users/${userId}`} />
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
  logo: {
    height: '100%',
    float: 'left',
    margin: '0 24px 0 -8px',
    display: 'flex',
    alignItems: 'center'
  },
  logoTitle: {
    color: '#fff',
    margin: '0 0 0 4px'
  },
  logoImage: {
    height: 36
  },
  menu: {
    lineHeight: '64px',
    backgroundColor: ColorsHelper.appColorDark
  },
  profileSubmenu: {
    float: 'right'
  }
};

export default connect(UserNavbar);
