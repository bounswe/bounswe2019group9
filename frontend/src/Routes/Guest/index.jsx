import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import LogIn from './LogIn';
import Register from './Register';
import Forgot from './Forgot';

class GuestRoutes extends React.PureComponent {
  render() {
    return (
      <Switch>
        <Route exact path="/login" component={LogIn} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/forgot" component={Forgot} />
        <Redirect to="/login" />
      </Switch>
    );
  }
}

export default GuestRoutes;
