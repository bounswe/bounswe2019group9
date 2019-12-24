import React from 'react';
import { Layout, Breadcrumb } from 'antd';
import {Switch, Route, Redirect, useParams, useLocation, NavLink} from 'react-router-dom';

import LanguageSidebar from './LanguageSidebar';
import ProfExam from './ProfExam';
import Suggest from "./Suggest";
import Suggest from "./Writing";
import Main from './Main';
import Exercises from './Exercises';
import {ExercisesHelper} from '../../../Helpers';
import ExerciseSearch from './Exercises/ExerciseSearch';

const pathToLabel = {
  'proficiency-exam': 'Proficiency Exam',
  'exercises': 'Exercises',
  ...ExercisesHelper.exerciseTypes.reduce((acc, { route, name }) => ({ [route]: name, ...acc }), {}),
  'search': 'Search',
};

const LanguageRoutes = () => {
    const { language } = useParams();
    const { pathname } = useLocation();
    const paths = pathname.split('/').filter((x) => x);
    return (
      <Layout style={styles.container}>
        <Layout.Sider theme={'light'} width={200} collapsible>
          <LanguageSidebar />
        </Layout.Sider>
        <Layout style={styles.layout}>
          <Breadcrumb style={styles.breadcrumb}>
            { paths.map((path, index) => (
              <Breadcrumb.Item key={path}>
                <NavLink to={`/${paths.slice(0, index + 1).join('/')}`}>
                  { pathToLabel[path] || path }
                </NavLink>
              </Breadcrumb.Item>
            )) }
          </Breadcrumb>
          <Layout.Content style={styles.content}>
            <Switch>
              <Route exact path='/:language' component={Main} />
              <Route exact path='/:language/exercises/search' component={ExerciseSearch} />
	      <Route path='/:language/suggest' component={Suggest} />
	      <Route path='/:language/suggest' component={Writing} />
              <Route exact path='/:language/exercises/:exerciseType' component={Exercises} />
              <Route path='/:language/proficiency-exam' component={ProfExam} />
              <Redirect to={`/${language}`}/>
            </Switch>
          </Layout.Content>
        </Layout>
      </Layout>
    )
};

const styles = {
  container: {
    height: '100%'
  },
  layout: {
    height: '100%',
    padding: '0 24px 24px',
  },
  content: {
    background: '#fff',
    padding: 24,
    margin: 0
  },
  breadcrumb: {
    margin: '16px 0'
  }
};

export default LanguageRoutes;
