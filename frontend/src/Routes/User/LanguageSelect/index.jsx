import React from 'react';
import { Link } from 'react-router-dom';
import {Card, Skeleton} from 'antd';
import {getLanguages} from '../../../Api/Content';
import {CenterView} from '../../../Layouts';

class LanguageSelect extends React.PureComponent {
  state = {
    languages: [],
    loading: true
  };
  componentDidMount() {
    getLanguages()
      .then((response) => {
        const { data = [] } = response.data || {};
        this.setState({
          languages: data,
          loading: false
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
    const { languages, loading } = this.state;
    return (
      <CenterView>
        <Card title="Select a Language" style={styles.card}>
          <Skeleton loading={loading} title={false}>
            <ul>
              { languages.map((language) => (
                <li key={language}>
                  <Link to={`/${language}/`}>
                    { language }
                  </Link>
                </li>
              )) }
            </ul>
          </Skeleton>
        </Card>
      </CenterView>
    )
  }
}

const styles = {
  card: {
    marginBottom: 120,
    width: 300
  }
};

export default LanguageSelect;
