import React from "react";
import { Container, Row, Col, Card, CardHeader, CardTitle, CardBody, CardFooter,
Alert, Form, FormGroup, Label, Input, Button } from "reactstrap";

import { updateStore } from '../../../Store';
import {Link} from "react-router-dom";
import {login} from '../../../Api/User';

class LogIn extends React.PureComponent {
  state = {
    email: '',
    password: '',
    loading: false,
    error: ''
  };
  handleSubmit = (e) => {
    const { email, password, loading } = this.state;
    e.preventDefault();
    if (!loading) {
      this.setState({
        loading: true
      }, () => {
        login({email, password}, {successMessage: 'Successfully Logged In'})
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
      });
    }
  };
  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  render() {
    const { loading, email, password, error } = this.state;
    return (
      <Container fluid className="h-100">
        <Row className="h-100 justify-content-center">
          <Col sm="10" md="8" lg="7" xl="5" className="align-self-center">
            <Form onSubmit={this.handleSubmit}>
              <Card className="mb-5">
                <CardHeader>
                  <CardTitle tag="h3">
                    Log In
                  </CardTitle>
                </CardHeader>
                <CardBody>
                  <Alert color="danger" isOpen={!loading && error !== ''}>
                    { error }
                  </Alert>
                  <FormGroup>
                    <Label for="email">E-Mail</Label>
                    <Input
                      type="email"
                      id="email"
                      name="email"
                      value={email}
                      placeholder="Please enter your email."
                      onChange={this.handleChange}
                      valid={email !== ''}
                    />
                  </FormGroup>
                  <FormGroup>
                    <Label for="password">Password</Label>
                    <Input
                      type="password"
                      id="password"
                      name="password"
                      value={password}
                      placeholder="Please enter your password."
                      onChange={this.handleChange}
                      valid={password !== ''}
                    />
                  </FormGroup>
                </CardBody>
                <CardFooter>
                  <Button type="submit" disabled={email === '' || password === '' || loading}>
                    { loading ? 'Logging in ...' : 'Log In' }
                  </Button>
                  <p>Aren't you a user yet? <Button tag={Link} to={"/register"}>Go to Register Page</Button></p>
                  <Button tag={Link} to={"/forgot"}>Forgot my password</Button>
                </CardFooter>
              </Card>
            </Form>
          </Col>
        </Row>
      </Container>
    )
  }
}

export default LogIn;
