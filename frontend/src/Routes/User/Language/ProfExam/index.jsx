import React from 'react';
import {Card, CardTitle, CardSubtitle, CardBody, CardFooter, CardHeader, Button} from 'reactstrap';
import { toast } from 'react-toastify';
import {getProfExam} from '../../../../Api/Content';
import {addGrade} from '../../../../Api/Grade';
import { connect, storeType } from '../../../../Store';
import {GradesHelper} from '../../../../Helpers';

class ProfExam extends React.PureComponent {
  static propTypes = {
    store: storeType
  };
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


  nextQuestion = (increment = 1) => {
    const { store } = this.props;
    const { currentQuestion, questions, languageId, currentGrade } = this.state;
    if (currentQuestion + increment === questions.length) {
      const { num_grade, str_grade } = GradesHelper.calculateGrade(currentGrade, questions.length);
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

export default connect(ProfExam);
