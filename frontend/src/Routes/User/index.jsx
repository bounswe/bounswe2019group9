import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import { Layout } from 'antd';

import LanguageSelect from './LanguageSelect';
import Language from './Language';
import Dashboard from './Dashboard';
import UserNavbar from './Navbar';
import SamplePage from './Sample';
import UserProfile from './UserProfile';
import Users from './Users';
import {ColorsHelper} from '../../Helpers';
import Chats from './Chats';
import Essays from "./Essays";

class UserRoutes extends React.PureComponent {
  render() {
    return (
      <Layout className="layout">
        <Layout.Header style={styles.header}>
          <UserNavbar />
        </Layout.Header>
        <Layout.Content>
          <Switch>
            <Redirect from="/(login|register|forgot)" to="/" />
            <Route exact path="/language-select" component={LanguageSelect} />
            <Route exact path="/sample" component={SamplePage} />
            <Route exact path="/home" component={Dashboard} />
            <Route exact path="/users" component={Users} />
            <Route exact path="/users/:userId" component={UserProfile} />
            <Route exact path="/chat" component={Chats} />
            <Route exact path="/essays" component={Essays} />
            <Route path="/:language" component={Language} />
            <Redirect to="/home" />
          </Switch>
        </Layout.Content>
      </Layout>
    );
  }
}

const styles = {
  header: {
    backgroundColor: ColorsHelper.appColorDark
  }
};

export default UserRoutes;
