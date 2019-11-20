import React from 'react';
import {Link, useLocation} from 'react-router-dom';
import {Navbar, NavbarBrand, NavbarToggler, Collapse, Nav, NavItem, NavLink} from 'reactstrap';
import {Menu} from 'antd';

import { updateStore } from '../../../Store';

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
    name: 'Change Language',
    route: '/language-select'
  }
];

function UserNavbar(props) {
  const [isOpen, setIsOpen] = React.useState(false);
  const { pathname } = useLocation();
  return (
    <>
      <Menu
        theme="dark"
        mode="horizontal"
        style={styles.menu}
        defaultSelectedKeys={[pathname]}
      >
        {
          routes.map(({ name, route }) => (
            <Menu.Item key={route}>
              { name }
              <Link to={route} />
            </Menu.Item>
          ))
        }
        <Menu.Item onClick={logOut}>
          Log Out
        </Menu.Item>
      </Menu>
    </>
  );
}

const styles = {
  menu: {
    lineHeight: '64px'
  }
};

export default UserNavbar;
