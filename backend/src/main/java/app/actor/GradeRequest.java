package app.actor;

/**
 * @author ahmet.gedemenli
 */

public class GradeRequest {

  private Long userId;

  private Integer languageId;

  private Integer grade;

  public GradeRequest(Long userId, Integer languageId, Integer grade) {
    this.userId = userId;
    this.languageId = languageId;
    this.grade = grade;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Integer languageId) {
    this.languageId = languageId;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "GradeRequest{" +
           "userId=" + userId +
           ", languageId=" + languageId +
           ", grade=" + grade +
           '}';
  }
}
