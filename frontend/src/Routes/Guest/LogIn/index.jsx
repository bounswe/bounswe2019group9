import React from "react";
import { Container, CardHeader, CardTitle, CardBody, CardFooter } from "reactstrap";
import { Card, Row, Col, Form, Icon, Input, Button, Checkbox, Alert } from "antd";

import { updateStore } from '../../../Store';
import {Link} from "react-router-dom";
import {login} from '../../../Api/User';
import {CenterView} from '../../../Layouts';
import {FormIcon} from '../../../Components';

class LogIn extends React.PureComponent {
  state = {
    loading: false,
    error: ''
  };
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (err) {
        this.setState({ error: err.message });
        return;
      }
      this.setState({
        loading: true
      }, () => {
        login(values, {successMessage: 'Successfully Logged In'})
          .then((response) => {
            const { data } = response.data || {};
            const { id: userId } = data || {};
            updateStore({
              token: 'abc123',
              userId
            });
          })
          .catch(({ message }) => {
            this.setState({
              loading: false,
              error: message
            })
          });
      })
    })
  };
  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  render() {
    const { getFieldDecorator } = this.props.form;
    const { loading, email, password, error } = this.state;

    return (
      <CenterView>
        <Row type="flex" justify="center" align="center">
          <Col xs={23} sm={19} md={15} lg={11} xl={9}>
            <Card title={"Log In"} >
              { error && (
                <Alert type="error" message={error} showIcon />
              )}
              <Form
                onSubmit={this.handleSubmit}
                layout={'vertical'}
              >
                <Form.Item label="E-Mail">
                  {getFieldDecorator('email', {
                    rules: [{ required: true, message: 'Please enter your email.'}]
                  })(
                    <Input
                      type="email"
                      name="email"
                      value={email}
                      placeholder="Please enter your email."
                      prefix={<FormIcon type="mail" />}
                    />
                  )}
                </Form.Item>
                <Form.Item label="Password">
                  {getFieldDecorator('password', {
                    rules: [{ required: true, message: 'Please enter your password.'}]
                  })(
                    <Input
                      type="password"
                      id="password"
                      name="password"
                      value={password}
                      placeholder="Please enter your password."
                      prefix={<FormIcon type="lock" />}
                    />
                  )}
                </Form.Item>
                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    loading={loading}
                  >
                    { loading ? 'Logging in ...' : 'Log In' }
                  </Button>
                  <p>Aren't you a user yet? <Button tag={Link} to={"/register"}>Go to Register Page</Button></p>
                  <Button tag={Link} to={"/forgot"}>Forgot my password</Button>
                </Form.Item>
              </Form>
            </Card>
          </Col>
        </Row>
      </CenterView>
    )
  }
}

export default Form.create({ name: 'login_form' })(LogIn);
