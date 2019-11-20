import React from 'react';
import PropTypes from 'prop-types';
import { Layout } from 'antd';

const CenterView = ({ children }) => {
  return (
    <Layout style={styles.layout}>
      { children }
    </Layout>
  )
};

CenterView.propTypes = {
  children: PropTypes.node
};

const styles = {
  layout: {
    height: '100vh',
    alignItems: 'center',
    justifyContent: 'center',
    paddingBottom: '10vh'
  }
};

export default CenterView;
