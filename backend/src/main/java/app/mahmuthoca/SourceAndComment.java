package app.mahmuthoca;

public class SourceAndComment {

  private String sourceFirstName;

  private String sourceLastName;

  private Comment comment;

  public SourceAndComment(String sourceFirstName, String sourceLastName, Comment comment) {
    this.sourceFirstName = sourceFirstName;
    this.sourceLastName = sourceLastName;
    this.comment = comment;
  }

  public String getSourceFirstName() {
    return sourceFirstName;
  }

  public void setSourceFirstName(String sourceFirstName) {
    this.sourceFirstName = sourceFirstName;
  }

  public String getSourceLastName() {
    return sourceLastName;
  }

  public void setSourceLastName(String sourceLastName) {
    this.sourceLastName = sourceLastName;
  }

  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "SourceAndComment{" +
           "sourceFirstName='" + sourceFirstName + '\'' +
           ", sourceLastName='" + sourceLastName + '\'' +
           ", comment=" + comment +
           '}';
  }
}