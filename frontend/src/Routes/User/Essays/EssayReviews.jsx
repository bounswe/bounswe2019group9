import React, {useEffect, useState} from 'react';
import {connect} from "../../../Store";
import {Button, Layout, Modal} from "antd";

import EssaysSider from "./EssaysSider";
import EssayView from "./EssayView";
import {getRequestsByUserId} from "../../../Api/Request";

const { Header, Footer, Sider, Content } = Layout;

const EssayReviews = ({ store: { userId } }) => {

    const [essays, setEssays] = useState([]);
    const [focusEssayId, setFocusEssayId] = useState(null);

    const updateEssays = () => {
        getRequestsByUserId(userId)
            .then(res => {
                const { data = [] } = res.data || {};
                setEssays(data);
            }).catch(console.log)
    };

    useEffect(() => {
        updateEssays();
    }, [userId]);

    const menu_essays = essays.map(ess => ({
        key: ess.essayId,
        text: `${ess.question} (${ess.username})`,
    }));

    const handleClick = ({ key }) => {
        setFocusEssayId(key);
    };

    return (
        <Layout style={{height: "100%"}}>
            <EssaysSider essays={menu_essays} onClick={handleClick} />
            <Layout>
                <Content style={styles.content}>
                    <EssayView essayId={focusEssayId} />
                </Content>
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

export default connect(EssayReviews);