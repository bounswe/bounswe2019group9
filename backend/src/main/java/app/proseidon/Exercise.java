package app.proseidon;

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

  @Column
  @NotNull
  private String question;

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

  public Exercise(Integer languageId, Integer typeId, String imageUrl, String soundUrl, String question, String optionA,
                  String optionB, String optionC, String optionD, Integer correctAnswer) {
    super(languageId, typeId, imageUrl, soundUrl);
    this.question = question;
    this.optionA = optionA;
    this.optionB = optionB;
    this.optionC = optionC;
    this.optionD = optionD;
    this.correctAnswer = correctAnswer;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String text) {
    this.question = question;
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
           "question='" + question + '\'' +
           ", optionA='" + optionA + '\'' +
           ", optionB='" + optionB + '\'' +
           ", optionC='" + optionC + '\'' +
           ", optionD='" + optionD + '\'' +
           ", correctAnswer=" + correctAnswer +
           '}';
  }
}
