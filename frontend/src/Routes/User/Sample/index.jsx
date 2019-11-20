import React from 'react';
import { Card, Form, Input, Button } from 'antd';
import axios from 'axios';
import {getGreeting} from '../../../Api/Greeting';
import {CenterView} from '../../../Layouts';

class Sample extends React.PureComponent {
  state = {
    name: '',
    greeting: '',
    loading: false,
  };
  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  handleSubmit = async (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (err) {
        this.setState({ error: err.message });
        return;
      }
      this.setState({
        loading: true
      }, async () => {
        try {
          const res = await getGreeting(values);
          const {content} = res.data || {};
          this.setState({
            greeting: content,
            loading: false
          });
        } catch (e) {
          console.error(e);
        }
      });
    });
  };
  render() {
    const { loading, greeting } = this.state;
    const { getFieldDecorator } = this.props.form;
    return (
      <CenterView>
        <Card>
          <h1 className="display-3">Welcome!</h1>
          <p className="lead">This is a simple endpoint tester, which we can use to test the api in a simple way.</p>
          <hr className="my-2" />
          <p>You can type your name in order for server to greet you :)</p>
          <Form onSubmit={this.handleSubmit} layout={'inline'}>
            <Form.Item label={'Your name:'}>
              { getFieldDecorator('name', {
                rules: [{ required: true, message: 'Please enter a name.'}]
              })(
                <Input
                  placeholder="Egemen"
                />
              )}
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit" loading={loading}>
                Greet
              </Button>
            </Form.Item>
          </Form>
          { greeting ? <div>
            <hr className="my-2" />
            <p className="lead">{greeting}</p>
          </div> : null }
        </Card>
      </CenterView>
    )
  }
}

const styles = {
};

export default Form.create({ name: 'sample_form' })(Sample);
