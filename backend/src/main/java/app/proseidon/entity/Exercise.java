package app.proseidon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author ahmet.gedemenli
 */

@Entity
@Table(name = "exercises")
public class Exercise extends Content {

  @Column(name = "question_body")
  @NotNull
  private String questionBody;

  @Column(name = "option_a")
  @NotNull
  private String optionA;

  @Column(name = "option_b")
  @NotNull
  private String optionB;

  @Column(name = "option_c")
  @NotNull
  private String optionC;

  @Column(name = "option_d")
  @NotNull
  private String optionD;

  @Column(name = "correct_answer")
  @NotNull
  private Integer correctAnswer;

  public Exercise() {
  }

  public Exercise(Integer languageId, Integer grade, Integer typeId, String imageUrl, String soundUrl,
                  @NotNull String questionBody, @NotNull String optionA,
                  @NotNull String optionB, @NotNull String optionC,
                  @NotNull String optionD, @NotNull Integer correctAnswer) {
    super(languageId, grade, typeId, imageUrl, soundUrl);
    this.questionBody = questionBody;
    this.optionA = optionA;
    this.optionB = optionB;
    this.optionC = optionC;
    this.optionD = optionD;
    this.correctAnswer = correctAnswer;
  }

  public String getQuestionBody() {
    return questionBody;
  }

  public void setQuestionBody(String questionBody) {
    this.questionBody = questionBody;
  }

  public String getOptionA() {
    return optionA;
  }

  public void setOptionA(String optionA) {
    this.optionA = optionA;
  }

  public String getOptionB() {
    return optionB;
  }

  public void setOptionB(String optionB) {
    this.optionB = optionB;
  }

  public String getOptionC() {
    return optionC;
  }

  public void setOptionC(String optionC) {
    this.optionC = optionC;
  }

  public String getOptionD() {
    return optionD;
  }

  public void setOptionD(String optionD) {
    this.optionD = optionD;
  }

  public Integer getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(Integer correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  @Override
  public String toString() {
    return "Exercise{" +
           "questionBody='" + questionBody + '\'' +
           ", optionA='" + optionA + '\'' +
           ", optionB='" + optionB + '\'' +
           ", optionC='" + optionC + '\'' +
           ", optionD='" + optionD + '\'' +
           ", correctAnswer=" + correctAnswer +
           '}';
  }
}
