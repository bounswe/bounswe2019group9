import React from 'react';
import PropTypes from 'prop-types';
import {useParams} from 'react-router-dom';

const Sidebar = () => {
  const {language} = useParams();

  return (
    <>
      <h4>{language}</h4>
      I am sidebar
    </>
  );
};

export default Sidebar;
