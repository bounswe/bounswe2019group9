import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import Dashboard from './Dashboard';
import UserNavbar from './Navbar';
import SamplePage from './Sample';

class GuestRoutes extends React.PureComponent {
  render() {
    return (
      <>
        <UserNavbar />
        <Switch>
          <Route exact path="/" component={Dashboard} />
          <Route exact path="/sample" component={SamplePage} />
          <Redirect to="/" />
        </Switch>
      </>
    );
  }
}

export default GuestRoutes;
