import React, {useEffect, useState} from 'react';
import {connect} from "../../../Store";
import {Button, Layout, Modal} from "antd";

import EssaysSider from "./EssaysSider";
import {getEssaysByUserId} from "../../../Api/Essay";
import RequestReviewModal from "./RequestReview";
import AnnotationContainer from "../Annotation";
import EssayView from "./EssayView";

const { Header, Footer, Sider, Content } = Layout;

const Essays = ({ store: { userId } }) => {

  const [essays, setEssays] = useState([]);
  const [focusEssayId, setFocusEssayId] = useState(null);
  const [isModalVisible, setModalVisible] = useState(false);
  const [focusLangId, setFocusLangId] = useState();

  const updateEssays = () => {
    getEssaysByUserId({ id: userId })
      .then(res => {
        const { data = [] } = res.data || [];
        setEssays(data);
      }).catch(console.log)
  };

  useEffect(() => {
    updateEssays();
  }, [userId]);

  const menu_essays = essays.map(ess => ({
    key: ess.essay.id,
    text: ess.assignment.question,
  }));

  const handleClick = ({ key }) => {
    setFocusEssayId(key);
  };

  useEffect(() => {
    for (let ess of essays) {
      if (ess.essay.id === focusEssayId) {
        setFocusLangId(ess.assignment.languageId);
      }
    }
  }, [focusEssayId]);

  return (
    <Layout style={{height: "100%"}}>
      <EssaysSider essays={menu_essays} onClick={handleClick} />
      <Layout>
        <Content style={styles.content}>
            <EssayView essayId={focusEssayId} />
        </Content>
        <Footer style={styles.footer}>
          <Button onClick={() => setModalVisible(true)}>Request Review</Button>
          <RequestReviewModal essayId={focusEssayId} visible={isModalVisible} languageId={focusLangId} onCancel={() => setModalVisible(false)}/>
        </Footer>
      </Layout>
    </Layout>
  )
};

const styles = {
  content: {
    display: "flex",
    alignItems: "center",
    flexDirection: "column",
    overflow: 'auto'
  },
  footer: {
    width: "100%",
    display: "grid",
    justifyContent: "center",
    alignContent: "center",
  },

}

export default connect(Essays);