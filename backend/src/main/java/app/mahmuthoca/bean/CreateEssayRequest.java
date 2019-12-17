package app.mahmuthoca.bean;

import javax.validation.constraints.NotNull;

/**
 * @author ahmet.gedemenli
 */

public class CreateEssayRequest {

  @NotNull
  private Long assignmentId;

  @NotNull
  private Long authorId;

  @NotNull
  private String source;

  @NotNull
  private Integer sourceType;

  public CreateEssayRequest(Long assignmentId, Long authorId, String source, Integer sourceType) {
    this.assignmentId = assignmentId;
    this.authorId = authorId;
    this.source = source;
    this.sourceType = sourceType;
  }

  public Long getAssignmentId() {
    return assignmentId;
  }

  public void setAssignmentId(Long assignmentId) {
    this.assignmentId = assignmentId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Integer getSourceType() {
    return sourceType;
  }

  public void setSourceType(Integer sourceType) {
    this.sourceType = sourceType;
  }

  @Override
  public String toString() {
    return "CreateEssayRequest{" +
           "assignmentId=" + assignmentId +
           ", authorId=" + authorId +
           ", source='" + source + '\'' +
           ", sourceType=" + sourceType +
           '}';
  }
}
