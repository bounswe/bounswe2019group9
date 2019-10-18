import React from "react";
import {
  Container,
  Row,
  Col,
  Form,
  Card,
  CardHeader,
  CardTitle,
  CardBody,
  Alert,
  FormGroup,
  Label,
  Input, CardFooter, Button
} from "reactstrap";

import { updateStore } from '../../../Store';
import {Link} from "react-router-dom";
import {register} from '../../../Api/User';

class Register extends React.PureComponent {
  state = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    loading: false,
    error: '',
  };
  handleSubmit = (e) => {
    const { firstName, lastName, email, password, loading } = this.state;
    e.preventDefault();
    if (!loading) {
      this.setState({
        loading: true
      }, () => {
        register({
          firstName,
          lastName,
          email,
          password
        }).then((response) => {
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
    const { loading, firstName, lastName, email, password, error } = this.state;
    return (
        <Container fluid className="h-100">
          <Row className="h-100 justify-content-center">
            <Col sm="10" md="8" lg="7" xl="5" className="align-self-center">
              <Form onSubmit={this.handleSubmit}>
                <Card className="mb-5">
                  <CardHeader>
                    <CardTitle tag="h3">
                      Register
                    </CardTitle>
                  </CardHeader>
                  <CardBody>
                    <Alert color="danger" isOpen={!loading && error !== ''}>
                      { error }
                    </Alert>
                    <FormGroup>
                      <Label for="firstName">First Name</Label>
                      <Input
                        type="text"
                        id="firstName"
                        name="firstName"
                        value={firstName}
                        placeholder="Please enter your first name."
                        onChange={this.handleChange}
                        valid={firstName !== ''}
                      />
                    </FormGroup>
                    <FormGroup>
                      <Label for="lastName">Last Name</Label>
                      <Input
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={lastName}
                        placeholder="Please enter your last name."
                        onChange={this.handleChange}
                        valid={lastName !== ''}
                      />
                    </FormGroup>
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
                      { loading ? 'Registering...' : 'Register' }
                    </Button>
                    <p>Are you already a user? <Button tag={Link} to={"/login"}>Go to Log In</Button></p>
                  </CardFooter>
                </Card>
              </Form>
            </Col>
          </Row>
        </Container>
    )
  }
}

export default Register;
