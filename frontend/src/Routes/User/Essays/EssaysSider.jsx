import React, {useEffect, useState} from 'react';
import {connect} from "../../../Store";
import {Layout, Menu} from "antd";
import {getEssaysByUserId} from "../../../Api/Essay";


const { Header, Footer, Sider, Content } = Layout;


const EssaysSider = ({ essays, onClick }) => {

  return (
    <Sider width={400} style={styles.sider}>
      <Menu onClick={onClick} >
        {essays.map(es => (
          <Menu.Item key={es.key}>
            {es.text}
          </Menu.Item>
        ))}
      </Menu>
    </Sider>
  )
};

const styles = {
  sider: {
    background: '#fff',
    height: "100%",
  }
}

export default EssaysSider;