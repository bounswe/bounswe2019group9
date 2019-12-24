package app.mahmuthoca.bean;

/**
 * @author ahmet.gedemenli
 */

public class CreateAssignmentRequest {

  private String text;

  private Long languageId;

  public CreateAssignmentRequest(String text, Long languageId) {
    this.text = text;
    this.languageId = languageId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Long languageId) {
    this.languageId = languageId;
  }

  @Override
  public String toString() {
    return "CreateAssignmentRequest{" +
           "text='" + text + '\'' +
           ", languageId=" + languageId +
           '}';
  }
}
