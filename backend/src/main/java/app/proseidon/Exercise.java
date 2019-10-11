package app.proseidon;

/**
 * @author ahmet.gedemenli
 */

public class Exercise extends Content {

  private String text;

  private String optionA;

  private String optionB;

  private String optionC;

  private String optionD;

  private Integer correctAnswer;

  public Exercise(Integer languageId, Integer typeId, String imageUrl, String soundUrl, String text, String optionA,
                  String optionB, String optionC, String optionD, Integer correctAnswer) {
    super(languageId, typeId, imageUrl, soundUrl);
    this.text = text;
    this.optionA = optionA;
    this.optionB = optionB;
    this.optionC = optionC;
    this.optionD = optionD;
    this.correctAnswer = correctAnswer;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
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
           "text='" + text + '\'' +
           ", optionA='" + optionA + '\'' +
           ", optionB='" + optionB + '\'' +
           ", optionC='" + optionC + '\'' +
           ", optionD='" + optionD + '\'' +
           ", correctAnswer=" + correctAnswer +
           '}';
  }
}
