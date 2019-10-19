import React from 'react';
import {Link} from 'react-router-dom';
import {Navbar, NavbarBrand, NavbarToggler, Collapse, Nav, NavItem, NavLink} from 'reactstrap';

import { updateStore } from '../../../Store';

const logOut = (e) => {
  e.preventDefault();
  updateStore({
    token: '',
    userId: ''
  });
};

function UserNavbar(props) {
  const [isOpen, setIsOpen] = React.useState(false);
  return (
    <Navbar color="light" light expand="md" className="shadow">
      <NavbarBrand tag={Link} to="/home">
        Home
      </NavbarBrand>
      <NavbarToggler onClick={() => setIsOpen(!isOpen)} />
      <Collapse isOpen={isOpen} navbar>
        <Nav className="ml-auto" navbar>
          <NavItem>
            <NavLink
              tag={Link}
              exact
              to="/sample"
              activeClassName="active"
            >
              Sample Page
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              tag={Link}
              exact
              to="/language-select"
              activeClassName="active"
            >
              Change Language
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="#" onClick={logOut}>
              Log Out
            </NavLink>
          </NavItem>
        </Nav>
      </Collapse>
    </Navbar>
  );
}

export default UserNavbar;
