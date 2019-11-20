import React, { useEffect, useState } from 'react';
import { Container, Row, Col } from 'reactstrap';
import { useParams, Link } from 'react-router-dom';

import {connect, storeType} from '../../../../Store';
import {getGrade} from '../../../../Api/Grade';
import {GradesHelper} from '../../../../Helpers';

const Main = ({ store }) => {

  const { language } = useParams();

  const languageId = language === 'English' ? 1 : language === 'Turkish' ? 2 : language === 'Italian' ? 3 : 0;

  const [loading, setLoading] = useState(false);

  const [grade, setGrade] = useState();

  useEffect(() => {
    if (languageId) {
      setLoading(true);
      getGrade({userId: store.userId, languageId}).then((response) => {
        const {data = {}} = response.data || {};
        setGrade(data.grade || 0);
        setLoading(false);
      })
    }
  }, [language, languageId, store.userId]);

  if (languageId === 0) {
    return (
      <div>
        Unknown Language
      </div>
    )
  };

  let str_grade = GradesHelper.numGradeToStrGrade(grade);

  return (
    <Container>
      <Row className="mt-4">
        <Col>
          <h3>{ language }</h3>
          { loading ? (
            <h4>Loading your grade ...</h4>
          ) : (
            <>
              <h4> { grade ? `Your grade is ${str_grade}` : "You don't have a grade in this language" } </h4>
              <Link to={`/${language}/proficiency-exam`}>
                { grade ? 'Click here to re-take proficiency exam' : 'Click here to take proficiency exam' }
              </Link>
            </>
          )}
        </Col>
      </Row>
    </Container>
  )
};

Main.propTypes = {
  store: storeType
};

export default connect(Main);
