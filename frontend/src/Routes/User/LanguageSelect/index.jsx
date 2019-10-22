import React from 'react';
import { Link } from 'react-router-dom';
import {Form, FormGroup, Input, Label, Jumbotron, Button, } from "reactstrap";
import axios from 'axios';
import {getLanguages} from '../../../Api/Content';

class LanguageSelect extends React.PureComponent {
  state = {
    languages: []
  };
  componentDidMount() {
    getLanguages()
      .then((response) => {
        const { data = [] } = response.data || {};
        this.setState({
          languages: data
        });
      }).catch(console.error);
  }

  handleChange = (e) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };
  render() {
    const { languages } = this.state;
    return (
      <Jumbotron>
        <h1 className="display-3">Select a Language!</h1>
        <ul>
          { languages.map((language) => (
            <li key={language}>
              <Link to={`/${language}/`}>
                { language }
              </Link>
            </li>
          )) }
        </ul>
      </Jumbotron>
    )
  }
}

export default LanguageSelect;
