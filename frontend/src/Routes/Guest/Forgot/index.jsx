import React from "react";
import {Card, Form, Input, Button, Alert} from 'antd';

import {Link} from "react-router-dom";
import {CenterView} from '../../../Layouts';
import {FormIcon} from '../../../Components';

class Forgot extends React.PureComponent {
  state = {
    loading: false,
    error: "",
    success: false,
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
        setTimeout(() => {
          if (Math.random() < 0.5) {
            this.setState({
              loading: false,
              error: 'There is an error, please try again.'
            });
          } else {
            this.setState({
              loading: false,
              success: true,
            })
          }
        }, Math.random() * 2000);
      });
    })
  };
  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  render() {
    const { loading, error, success } = this.state;
    const {getFieldDecorator} = this.props.form;
    return (
      <CenterView>
        <Card title={'Forgot My Password'} style={styles.forgotCard}>
          { success ? (
          <p>Please check your email.</p>
          ) : (
          <>
            { error && <Alert type="error" message={error} showIcon/> }
            <Form onSubmit={this.handleSubmit} layout={'vertical'}>
              <Form.Item label="E-Mail">
                {getFieldDecorator('email', {
                  rules: [
                    {required: true, message: 'Please enter your email.'},
                    {type: 'email', message: 'Please enter valid email'}
                  ]
                })(
                  <Input
                  placeholder="Please enter your email."
                  prefix={<FormIcon type="mail"/>}
                  />
                )}
              </Form.Item>
              <Form.Item>
                <div style={styles.forgotLinks}>
                  <Link to={"/login"}>
                    Login Instead
                  </Link>
                  { ' | ' }
                  <Link to={"/register"}>
                    Create Account
                  </Link>
                </div>
                <Button
                  style={styles.forgotButton}
                  type="primary"
                  htmlType="submit"
                  loading={loading}
                >
                  {loading ? 'Sending ...' : 'Send'}
                </Button>
              </Form.Item>
            </Form>
          </>
          )}
        </Card>
      </CenterView>
    )
  }
}

const styles = {
  forgotCard: {
    maxWidth: 360,
    width: '80%',
    minWidth: 300
  },
  forgotLinks: {
    display: 'flex',
    justifyContent: 'space-between',
    marginBottom: 12
  },
  forgotButton: {
    width: '100%'
  }
};

export default Form.create({ name: 'forgot_password' })(Forgot);
