import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import { connect, storeType } from '../Store';

import GuestRoutes from './Guest';
import UserRoutes from './User';

class Routes extends React.PureComponent {
  static propTypes = {
    store: storeType
  };

  render() {
    const {store: {token}} = this.props;
    return (
      <BrowserRouter>
        {token ? <UserRoutes/> : <GuestRoutes/>}
      </BrowserRouter>
    );
  }
}

export default connect(Routes);
