import React from 'react';
import { Icon } from 'antd';

const FormIcon = ({ style, ...props }) => (
  <Icon
    { ...props }
    style={
      style ?
        { ...styles.formIcon, ...style} :
        styles.formIcon
    }
  />
);

FormIcon.propTypes = Icon.propTypes;

const styles = {
  formIcon: {
    opacity: '.25'
  }
};

export default FormIcon;
