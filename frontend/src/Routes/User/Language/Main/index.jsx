import React, { useEffect, useState } from 'react';
import { Container, Row, Col } from 'reactstrap';
import { useParams, Link } from 'react-router-dom';

import store from '../../../../Store';
import {getGrade} from '../../../../Api/Grade';

const Main = () => {

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
  }, [language, languageId]);

  if (languageId === 0) {
    return (
      <div>
        Unknown Language
      </div>
    )
  };

  let str_grade = '';
  switch (grade) {
    case 1:
     str_grade = 'A1';
     break;
    case 2:
      str_grade = 'A2';
      break;
    case 3:
      str_grade = 'B1';
      break;
    case 4:
      str_grade = 'B2';
      break;
    case 5:
      str_grade = 'C1';
      break;
    case 6:
      str_grade = 'C2';
      break;
    default:
  }

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

export default Main;
