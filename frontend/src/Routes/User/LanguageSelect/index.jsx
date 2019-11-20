import React from 'react';
import { Link } from 'react-router-dom';
import { Card } from 'antd';
import {getLanguages} from '../../../Api/Content';
import {CenterView} from '../../../Layouts';

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
      <CenterView>
        <Card title="Select a Language">
          <ul>
            { languages.map((language) => (
              <li key={language}>
                <Link to={`/${language}/`}>
                  { language }
                </Link>
              </li>
            )) }
          </ul>
        </Card>
      </CenterView>
    )
  }
}

export default LanguageSelect;
