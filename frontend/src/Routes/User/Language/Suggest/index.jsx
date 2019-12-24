import React from 'react';
import { Form, Row, Col, Input, Button, Icon } from 'antd';
import {addSuggestion} from "../../../../Api/Suggest";

class Suggest extends React.Component {
    state = {
        expand: false,
    };

    handleSubmit = e => {
        e.preventDefault();
        const form = {
            question: e.target["question"].value,
            choice1: e.target["choice1"].value,
            choice2: e.target["choice2"].value,
            choice3: e.target["choice3"].value,
            choice4: e.target["choice4"].value,
        }
        addSuggestion(form);
        console.log(form);
    };

    // To generate mock Form.Item
    getFields() {
        const count = this.state.expand ? 10 : 6;
        const { getFieldDecorator } = this.props.form;
        const children = [];
        for (let i = 0; i < 4; i++) {
            children.push(
                <Col span={6} key={i} style={{ display: i < count ? 'block' : 'none' }}>
                    <Form.Item>
                        <Input name={`choice${i+1}`} addonBefore={`Choice ${i+1}`} placeholder="Choices"></Input>
                    </Form.Item>
                </Col>,
            );
        }
        return children;
    }

    render() {
        return (
            <Form className="suggest-form" onSubmit={this.handleSubmit}>
                <Row gutter={24}><Form.Item><Input name={"question"} addonBefore={"Question"} placeholder="Write Your question here"></Input></Form.Item></Row>
                <Row gutter={24}>{this.getFields()}</Row>
                <Row>
                    <Col span={12} style={{ textAlign: 'right' }}>
                        <Button type="primary" htmlType="submit">
                            Suggest Exercise
                        </Button>
                    </Col>
                </Row>
            </Form>
        );
    }
}

export default Form.create({ name: 'suggest' })(Suggest);
;