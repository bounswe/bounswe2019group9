import React from "react";
import {Card, Form, Input, Button, Alert} from "antd";

import {updateStore} from '../../../Store';
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
        this.setState({error: err.message});
        return;
      }
      this.setState({
        loading: true
      }, () => {
        login(values, {successMessage: 'Successfully Logged In'})
          .then((response) => {
            const {data} = response.data || {};
            const {id: userId} = data || {};
            updateStore({
              token: 'abc123',
              userId
            });
          })
          .catch(({message}) => {
            this.setState({
              loading: false,
              error: message
            })
          });
      })
    })
  };
  handleChange = (e) => {
    const {value, name} = e.target;
    this.setState({
      [name]: value
    });
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    const {loading, error} = this.state;

    return (
      <CenterView>
        <Card title={"Log In"} style={styles.loginCard}>
          {error && (
            <Alert type="error" message={error} showIcon/>
          )}
          <Form
            onSubmit={this.handleSubmit}
            layout={'vertical'}
          >
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
            <Form.Item label="Password">
              {getFieldDecorator('password', {
                rules: [{required: true, message: 'Please enter your password.'}]
              })(
                <Input.Password
                  placeholder="Please enter your password."
                  prefix={<FormIcon type="lock"/>}
                />
              )}
            </Form.Item>
            <Form.Item>
              <div style={styles.loginLinks}>
                <Link to={"/forgot"}>
                  Forgot Password
                </Link>
                { ' | ' }
                <Link to={"/register"}>
                  Create Account
                </Link>
              </div>
              <Button
                style={styles.loginButton}
                type="primary"
                htmlType="submit"
                loading={loading}
              >
                {loading ? 'Logging in ...' : 'Log In'}
              </Button>
            </Form.Item>
          </Form>
        </Card>
      </CenterView>
    )
  }
}

const styles = {
  loginCard: {
    maxWidth: 360,
    width: '80%',
    minWidth: 300
  },
  loginLinks: {
    display: 'flex',
    justifyContent: 'space-between',
    marginBottom: 12
  },
  loginButton: {
    width: '100%'
  }
};

export default Form.create({name: 'login_form'})(LogIn);
