import React from 'react';
import {Card, CardTitle, CardSubtitle, CardBody, CardFooter, CardHeader, Button} from 'reactstrap';
import { toast } from 'react-toastify';
import {getProfExam} from '../../../../Api/Content';
import {addGrade} from '../../../../Api/Grade';
import store from '../../../../Store';

class ProfExam extends React.PureComponent {
  state = {
    questions: [],
    currentQuestion: 0,
    currentGrade: 0,
    lastAnswered: -1,
    languageId: null
  };

  componentDidMount() {
    const { language } = this.props.match.params;
    getProfExam(language).then((response) => {
      const { data } = response.data;
      if (data) {
        this.setState({
          questions: data.map(({ optionA, optionB, optionC, optionD, correctAnswer, ...rest }) => ({
            ...rest,
            options: [ optionA, optionB, optionC, optionD ],
            correctAnswer: correctAnswer - 1
          })),
          languageId: data[0].languageId
        });
      }
    }).catch(console.error);
  }

  answerQuestion = (optionIndex) => {
    const { currentQuestion, currentGrade, lastAnswered, questions } = this.state;
    if (currentQuestion <= lastAnswered) {
      toast.error("You can't change your choice!");
    } else {
      const question = questions[currentQuestion] || {};
      const { correctAnswer } = question;
      this.setState({
        lastAnswered: lastAnswered + 1,
        currentGrade: currentGrade + (optionIndex === correctAnswer ? 1 : 0)
      });
    }
  };

  getGrade = (correct, total) => {
    let percent = 0
    let str_grade = "A1";
    let num_grade = 1
    if (total <= 0 || correct < 0 || correct > total) {
      return null;
    } else {
      percent = correct / total
    }

    if (percent > 90) {
      num_grade = 6
      str_grade = "C2"
    } else if (percent > 85) {
      num_grade = 5
      str_grade = "C1"
    } else if (percent > 75) {
      num_grade = 4
      str_grade = "B2"
    } else if (percent > 60) {
      num_grade = 3
      str_grade = "B1"
    } else if (percent > 40) {
      num_grade = 2
      str_grade = "A2"
    } else {
      num_grade = 1
      str_grade = "A1"
    }

    return { num_grade, str_grade }
  }

  nextQuestion = (increment = 1) => {
    const { currentQuestion, questions, languageId, currentGrade } = this.state;
    if (currentQuestion + increment === questions.length) {
      const { num_grade, str_grade } = this.getGrade(currentGrade, questions.length);
      addGrade({
        userId: store.userId,
        languageId,
        grade: num_grade
      }).then((response) => {
        toast.info(`Congrats, you got ${currentGrade} correct answers out of ${questions.length} questions.\nYour grade is ${str_grade}`);
        const { match: { params: { language } }, history } = this.props;
        history.push(`/${language}`);
        console.log(response);
        // what to do next?
      }).catch(console.error);
    } else {
      this.setState({
        currentQuestion: Math.max(0, Math.min(questions.length - 1, currentQuestion + increment))
      });
    }
  };

  render() {
    const { questions, currentQuestion, currentGrade, lastAnswered } = this.state;
    const question = questions[currentQuestion] || {};
    const { questionBody = '', options = [], correctAnswer = 0 } = question;

    return (
      <div className="d-flex flex-column align-items-center justify-content-between h-100 py-5 mb-3 mr-2">
        <h3>Proficiency Exam</h3>
        <Card>
          <CardHeader>
            <CardTitle>
              { currentQuestion + 1 }/{ questions.length }: {questionBody}
            </CardTitle>
            <CardSubtitle>

              Your Score: { currentGrade }
            </CardSubtitle>
          </CardHeader>
          <CardBody className="d-flex justify-content-between">
            {
              options.map((option, optionIndex) => (
                <Button
                  key={`Option_${optionIndex}`}
                  onClick={() => this.answerQuestion(optionIndex)}
                  color={ lastAnswered < currentQuestion ? 'warning' :
                    correctAnswer === optionIndex ? 'success' : 'danger' }
                >
                  { option }
                </Button>
              ))
            }
          </CardBody>
          <CardFooter className="d-flex justify-content-between">
            <Button
              color="secondary"
              onClick={() => this.nextQuestion(-1)}
              disabled={currentQuestion === 0}
            >
              Previous Question
            </Button>
            <Button
              color="primary"
              onClick={() => this.nextQuestion()}
              disabled={lastAnswered < currentQuestion}
            >
              { currentQuestion + 1 === questions.length ? 'Finish Exam' : 'Next Question' }
            </Button>
          </CardFooter>
        </Card>
        <p/>
      </div>
    );
  }
}

export default ProfExam;
