import React from 'react';
import {CenterView} from '../../../Layouts';
import {Card} from 'antd';
import {Link} from 'react-router-dom';

class Dashboard extends React.PureComponent {
  render() {
    return (
      <CenterView>
        <Card>
          <h1 style={styles.heading}>
            Welcome to Kereviz !
          </h1>
          <Link to={'/language-select'}>Please select a language to continue</Link>
        </Card>
      </CenterView>
    )
  }
}

const styles = {
  heading: {
    fontSize: 36
  }
};

export default Dashboard;
