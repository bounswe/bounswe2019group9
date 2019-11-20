import React from 'react';
import {PageHeader, Card, Button, Icon, Skeleton, Radio} from 'antd';
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
    answers: [],
    currentQuestion: 0,
    currentGrade: 0,
    lastAnswered: -1,
    languageId: null,
    loading: true,
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
          languageId: data[0].languageId,
          loading: false
        });
      }
    }).catch(console.error);
  }

  answerQuestion = (optionIndex) => {
    const { currentQuestion, currentGrade, lastAnswered, questions } = this.state;
    /*if (currentQuestion <= lastAnswered) {
      toast.error("You can't change your choice!");
    } else {*/
      const question = questions[currentQuestion] || {};
      const answers = [...this.state.answers];
      answers[currentQuestion] = optionIndex + 1;
      const { correctAnswer } = question;
      this.setState({
        answers,
        lastAnswered: lastAnswered + 1,
        currentGrade: currentGrade + (optionIndex === correctAnswer ? 1 : 0)
      });
    //}
  };


  nextQuestion = (increment = 1) => {
    const { store } = this.props;
    const { currentQuestion, questions, answers, languageId, currentGrade } = this.state;
    if (currentQuestion + increment === questions.length) {
      const currentGrade = questions
        .reduce((grade, {correctAnswer}, questionIndex) =>
          grade + (((answers[questionIndex] || 0) === (correctAnswer + 1)) ? 0 : 1), 0);
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
    const { questions,answers, currentQuestion, currentGrade, lastAnswered, loading } = this.state;
    const question = questions[currentQuestion] || {};
    const answer = answers[currentQuestion];
    const { questionBody = '', options = [], correctAnswer = 0 } = question;

    return (
      <div>
        <PageHeader
          title={"Proficiency Exam"}
          subTitle={loading ? 'Loading ...' : `${ currentQuestion + 1 } / ${questions.length}`}
        />
        <Card
          title={(
            <Skeleton loading={loading} paragraph={false}>
              {questionBody}
            </Skeleton>
          )}
          actions={[
            <Button
              color="dashed"
              onClick={() => this.nextQuestion(-1)}
              disabled={currentQuestion === 0}
            >
              <Icon type="left" />
              Previous Question
            </Button>,
            <Button
              color="primary"
              onClick={() => this.nextQuestion()}
              disabled={lastAnswered < currentQuestion}
            >
              <Icon type="right" />
              { currentQuestion + 1 === questions.length ? 'Finish Exam' : 'Next Question' }
            </Button>
          ]}
          >
          <Skeleton loading={loading} paragraph={false}>
            <Radio.Group value={answer}>
              {
                options.map((option, optionIndex) => (
                  <Radio.Button
                    key={`Option_${optionIndex}`}
                    value={optionIndex + 1}
                    onClick={() => this.answerQuestion(optionIndex)}
                  >
                    { option }
                  </Radio.Button>
                ))
              }
            </Radio.Group>
          </Skeleton>
        </Card>
      </div>
    );
  }
}

export default connect(ProfExam);
