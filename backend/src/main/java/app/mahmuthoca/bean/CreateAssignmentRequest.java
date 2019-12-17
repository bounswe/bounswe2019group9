package app.mahmuthoca.bean;

/**
 * @author ahmet.gedemenli
 */

public class CreateAssignmentRequest {

  private String text;

  public CreateAssignmentRequest(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "CreateAssignmentRequest{" +
           "text='" + text + '\'' +
           '}';
  }
}
