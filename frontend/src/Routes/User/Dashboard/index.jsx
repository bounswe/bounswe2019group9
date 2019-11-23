import React from 'react';
import {CenterView} from '../../../Layouts';
import {Card} from 'antd';

class Dashboard extends React.PureComponent {
  render() {
    return (
      <CenterView>
        <Card>
          Hello world
        </Card>
      </CenterView>
    )
  }
}

export default Dashboard;
