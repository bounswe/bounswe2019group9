package app.mahmuthoca.bean;

/**
 * @author ahmet.gedemenli
 */

public class ReviewRequestDTO {

  private Long essayId;

  private String username;

  private String question;

  public ReviewRequestDTO(Long essayId, String username, String question) {
    this.essayId = essayId;
    this.username = username;
    this.question = question;
  }

  public Long getEssayId() {
    return essayId;
  }

  public void setEssayId(Long essayId) {
    this.essayId = essayId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  @Override
  public String toString() {
    return "ReviewRequestDTO{" +
           "essayId=" + essayId +
           ", username='" + username + '\'' +
           ", question='" + question + '\'' +
           '}';
  }
}
