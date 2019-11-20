import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Link, useParams, useLocation} from 'react-router-dom';
import {getGrade} from '../../../../Api/Grade';
import { connect, storeType } from '../../../../Store';
import {GradesHelper} from '../../../../Helpers';
import {Menu, Tag, Descriptions, Skeleton} from 'antd';

const routes = [
  { route: 'main', name: 'Main' },
  { route: 'proficiency-exam', name: 'Proficiency Exam' }
];

const LanguageSidebar = ({ store }) => {
  const {language} = useParams();
  const { pathname } = useLocation();
  const languageId = language === 'English' ? 1 : language === 'Turkish' ? 2 : language === 'Italian' ? 3 : 0;

  const [grade, setGrade] = useState();

  const currentRoute = pathname.split('/')[2] || 'main';

  useEffect(() => {
    if (languageId) {
      getGrade({userId: store.userId, languageId}).then((response) => {
        const {data = {}} = (response.data || {});
        setGrade((data || {}).grade || 0);
      }).catch(console.log)
    }
  }, [language, languageId]);

  let str_grade = GradesHelper.numGradeToStrGrade(grade);

  return (
    <div>
      <div style={styles.header}>
        <Skeleton loading={!grade} title={false}>
          <Descriptions title={language}>
            <Descriptions.Item label={'Grade'}>
              <Tag color={'#87d068'}>{str_grade}</Tag>
            </Descriptions.Item>
          </Descriptions>
        </Skeleton>
      </div>
      <Menu selectedKeys={[currentRoute]} mode="inline">
        {
          routes.map(({ name, route }) => (
            <Menu.Item key={route}>
              { name }
              <Link to={`/${language}/${route}`} />
            </Menu.Item>
          ))
        }
      </Menu>
    </div>
  );
};

const styles = {
  header: {
    margin: '24px 12px'
  }
};

LanguageSidebar.propTypes = {
  store: storeType
};

export default connect(LanguageSidebar);
