import React from "react";
import {Card, Form, Input, Button, Alert} from 'antd';

import { updateStore } from '../../../Store';
import {Link} from "react-router-dom";
import {register} from '../../../Api/User';
import {CenterView} from '../../../Layouts';
import {FormIcon} from '../../../Components';

class Register extends React.PureComponent {
  state = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    loading: false,
    error: '',
    confirmDirty: false,
  };
  formItemLayout = {
    labelCol: {
      xs: { span: 24 },
      sm: { span: 8 },
    },
    wrapperCol: {
      xs: { span: 24 },
      sm: { span: 16 }
    }
  };
  tailFormItemLayout = {
    wrapperCol: {
      xs: { span: 24, offset: 0 },
      sm: { span: 16, offset: 8 }
    }
  };
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, {confirmPassword, ...values}) => {
      if (err) {
        this.setState({ error: err.message });
        return;
      }
      this.setState({
        loading: true
      }, () => {
        register(values, {successMessage: 'Successfully Registered'})
          .then((response) => {
            const { data } = response.data || {};
            const { id: userId } = data || {};
            updateStore({
              token: 'abc123',
              userId
            });
          }).catch(({message}) => {
          this.setState({
            error: message,
            loading: false
          });
        });
      })
    });
  };
  handleConfirmBlur = (e) => {
    const { value } = e.target;
    this.setState(({ confirmDirty }) =>
      ({ confirmDirty: confirmDirty || !!value}));
  };
  validatePassword = (rule, value, callback) => {
    const { form } = this.props;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirmPassword'], { force: true });
    }
    callback();
  };
  validateConfirmPassword = (rule, value, callback) => {
    const { form } = this.props;
    if (value && value !== form.getFieldValue('password')) {
      callback("Two passwords you've entered are different!");
    } else {
      callback();
    }
  };
  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  render() {
    const {getFieldDecorator} = this.props.form;
    const { loading, error } = this.state;
    return (
      <CenterView>
        <Card title={"Register"} style={styles.registerCard}>
          {error && (
            <Alert type="error" message={error} showIcon/>
          )}
          <Form
            onSubmit={this.handleSubmit}
            {...this.formItemLayout}
          >
            <Form.Item label={'First Name'} hasFeedback>
              {getFieldDecorator('firstName', {
                rules: [{required: true, message: 'Please enter your first name.'}]
              })(
                <Input
                  placeholder="Please enter your first name."
                  prefix={<FormIcon type="user" />}
                />
              )}
            </Form.Item>
            <Form.Item label={'Last Name'} hasFeedback>
              {getFieldDecorator('lastName', {
                rules: [{ required: true, message: 'Please enter your last name.'}]
              })(
                <Input
                  placeholder="Please enter your last name."
                  prefix={<FormIcon type="user" />}
                />
              )}
            </Form.Item>
            <Form.Item label="E-Mail" hasFeedback>
              {getFieldDecorator('email', {
                rules: [
                  {required: true, message: 'Please enter your email.'},
                  {type: 'email', message: 'Please enter valid email'}
                ]
              })(
                <Input
                  type="email"
                  name="email"
                  placeholder="Please enter your email."
                  prefix={<FormIcon type="mail"/>}
                />
              )}
            </Form.Item>
            <Form.Item label="Password" hasFeedback>
              {getFieldDecorator('password', {
                rules: [
                  {required: true, message: 'Please enter your password.'},
                  {validator: this.validatePassword}
                ]
              })(
                <Input.Password
                  placeholder="Please enter your password."
                  prefix={<FormIcon type="lock"/>}
                />
              )}
            </Form.Item>
            <Form.Item label="Confirm Password" hasFeedback>
              {getFieldDecorator('confirmPassword', {
                rules: [
                  {required: true, message: 'Please confirm your password.'},
                  { validator: this.validateConfirmPassword}
                ]
              })(
                <Input.Password
                  placeholder="Please confirm your password."
                  prefix={<FormIcon type="lock"/>}
                  onBlur={this.handleConfirmBlur}
                />
              )}
            </Form.Item>
            <Form.Item {...this.tailFormItemLayout}>
              <div style={styles.registerLinks}>
                <Link to={"/login"}>
                  Login Instead
                </Link>
              </div>
              <Button
                style={styles.registerButton}
                type="primary"
                htmlType="submit"
                loading={loading}
              >
                { loading ? 'Registering...' : 'Register' }
              </Button>
            </Form.Item>
          </Form>
        </Card>
      </CenterView>
    )
  }
}

const styles = {
  registerCard: {
    maxWidth: 500,
    width: '90%',
    minWidth: 300
  },
  registerLinks: {
    display: 'flex',
    justifyContent: 'flex-end',
  },
  registerButton: {
    width: '100%'
  }
};

export default Form.create({ name: 'register_form' })(Register);
