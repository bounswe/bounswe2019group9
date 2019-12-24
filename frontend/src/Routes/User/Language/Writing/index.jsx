import React from 'react';
import {Form, Input, Button, PageHeader} from 'antd';
import {addWriting} from "../../../../Api/Writing";
import { useParams } from "react-router-dom";
import {LanguagesHelper} from "../../../../Helpers";


class Writing extends React.Component {
    constructor() {
        super();
        this.state = {
           subject: "",
            text: "",
            submitted: false
        };
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    submit() {
        const { subject, text } = this.state;
        const { language } = this.props.match.params;
        const { languageId } = LanguagesHelper.nameToLanguage(language);

        addWriting({
            languageId,
            subject,
            text
        }).then(()=> {
            this.setState({subject: "", text: "", submitted: true});
        })
    }

    render() {
        const { subject, text, submitted } = this.state;
        return (
            !submitted ? (<div>
                <PageHeader
                    title="Writing Exercise"
                />
                <Form layout="vertical">
                    <Form.Item >
                        <Input addonBefore="Subject" name="subject" value={subject} onChange={this.onChange.bind(this)} placeholder="Write your subject here" />
                    </Form.Item>
                    <Form.Item >
                        <Input.TextArea rows={30} name="text" value={text} onChange={this.onChange.bind(this)} placeholder="Write your essay here" />
                    </Form.Item>
                    <Form.Item >
                        <Button onClick={this.submit.bind(this)} type="primary">Submit</Button>
                    </Form.Item>
                </Form>
            </div>) : (
                <div>
                    <PageHeader
                        title="Writing Exercise"
                    />
                    <span>You have successfully submitted your writing exercise!</span>
                </div>
            )
        );
    }
}

export default Writing;