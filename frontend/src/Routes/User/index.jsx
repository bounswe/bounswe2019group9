import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';

import LanguageSelect from './LanguageSelect';
import Language from './Language';
import Dashboard from './Dashboard';
import UserNavbar from './Navbar';
import SamplePage from './Sample';

class UserRoutes extends React.PureComponent {
  render() {
    return (
      <>
        <UserNavbar />
        <Switch>
          <Redirect from="/(login|register|forgot)" to="/" />
          <Route exact path="/language-select" component={LanguageSelect} />
          <Route exact path="/sample" component={SamplePage} />
          <Route exact path="/home" component={Dashboard} />
          <Route path="/:language" component={Language} />
          <Redirect to="/language-select" />
        </Switch>
      </>
    );
  }
}

export default UserRoutes;
