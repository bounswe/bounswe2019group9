import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import { Row, Col } from 'reactstrap';

import Sidebar from './Sidebar';
import ProfExam from './ProfExam';
import Main from './Main';

class LanguageRoutes extends React.PureComponent {
  render() {
    const { language } = this.props.match.params;
    return (
      <Row className="flex-grow-1 flex-wrap-reverse">
        <Col sm={12} md={4} lg={3} xl={2} className="bg-light p-3 shadow mt-1">
          <Sidebar />
        </Col>
        <Col sm={12} md={8} lg={9} xl={10}>
          <Switch>
            <Route exact path='/:language' component={Main} />
            <Route path='/:language/proficiency-exam' component={ProfExam} />
            <Redirect to={`/${language}`}/>
          </Switch>
        </Col>
      </Row>
    )
  }
}

export default LanguageRoutes;
