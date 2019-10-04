import React from 'react';
import {Form, FormGroup, Input, Label, Jumbotron, Button, } from "reactstrap";
import axios from 'axios';

class Sample extends React.PureComponent {
  state = {
    name: '',
    greeting: '',
  };
  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  handleSubmit = async (e) => {
    e.preventDefault();
    let res = axios.get("https://api.bounswe2019group9.tk/greeting", {
      params: {
        name: this.state.name,
      }
    });
    console.log(res.data);
    let { content } = res.data;
    this.setState({
      greeting: content,
    });
  };
  render() {
    const { name, greeting } = this.state;
    return (
      <div>
        <Jumbotron>
          <h1 className="display-3">Welcome!</h1>
          <p className="lead">This is a simple endpoint tester, which we can use to test the api in a simple way.</p>
          <hr className="my-2" />
          <p>You can type your name in order for server to greet you :)</p>
          <Form inline onSubmit={this.handleSubmit}>
            <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
              <Label className="mr-sm-2">Your name: </Label>
              <Input
                  placeholder="Egemen"
                  name="name"
                  onChange={this.handleChange}
                  valid={name !== ""}
              />
            </FormGroup>
            <Button type="submit" disabled={name === ""} color="primary">Greet</Button>
          </Form>
          { greeting ? <div>
            <hr className="my-2" />
            <p className="lead">{greeting}</p>
          </div> : null }
        </Jumbotron>
      </div>
    )
  }
}

export default Sample;
