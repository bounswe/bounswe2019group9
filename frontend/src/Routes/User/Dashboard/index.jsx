import React from 'react';
import {CenterView} from '../../../Layouts';
import {Typography, Card} from 'antd';
import {Link} from 'react-router-dom';

class Dashboard extends React.PureComponent {
  render() {
    return (
      <CenterView>
        <Card>
          <Typography.Title level={1}>
            Welcome to Kereviz !
          </Typography.Title>
          <Link to={'/language-select'}>Please select a language to continue</Link>
        </Card>
      </CenterView>
    )
  }
}

export default Dashboard;
