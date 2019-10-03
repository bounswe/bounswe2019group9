import React from "react";
import { Container, Row, Col } from "reactstrap";

import { updateStore } from '../../../Store';

class Register extends React.PureComponent {
  state = {
    email: '',
    password: ''
  };
  handleSubmit = () => {
    updateStore({
      token: 'abc123'
    });
  };
  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  render() {
    return (
      <Container fluid>
        <Row>
          <Col>
            Test !
          </Col>
        </Row>
      </Container>
    )
  }
}

export default Register;
