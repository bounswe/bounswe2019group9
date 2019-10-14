import React from "react";
import { Container, Row, Col, Card, CardHeader, CardTitle, CardBody, CardFooter,
    Alert, Form, FormGroup, Label, Input, Button } from "reactstrap";

import {Link} from "react-router-dom";

class Forgot extends React.PureComponent {
    state = {
        email: "",
        loading: false,
        error: "",
        success: false,
    };
    handleSubmit = (e) => {
        e.preventDefault();
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
    };
    handleChange = (e) => {
        const { value, name } = e.target;
        this.setState({
            [name]: value
        });
    };
    render() {
        const { loading, email, error, success } = this.state;
        return (
            <Container fluid className="h-100">
                <Row className="h-100 justify-content-center">
                    <Col sm="10" md="8" lg="7" xl="5" className="align-self-center">
                        { success ?
                            <Card className="mb-5">
                                <CardHeader>
                                    <CardTitle tag="h3">
                                        Forgot My Password
                                    </CardTitle>
                                </CardHeader>
                                <CardBody>
                                    <p>Please check your email.</p>
                                </CardBody>
                                <CardFooter>
                                    <p>In order to log in, please click </p>
                                    <Button tag={Link} to={"/login"}>Log In</Button>
                                </CardFooter>
                            </Card>
                                :
                            <Form onSubmit={this.handleSubmit}>
                                <Card className="mb-5">
                                    <CardHeader>
                                        <CardTitle tag="h3">
                                            Forgot My Password
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
                                    </CardBody>
                                    <CardFooter>
                                        <Button type="submit" disabled={email === '' || loading}>
                                            { loading ? 'Sending...' : 'Send' }
                                        </Button>
                                    </CardFooter>
                                </Card>
                            </Form>
                        }
                    </Col>
                </Row>
            </Container>
        )
    }
}

export default Forgot;
