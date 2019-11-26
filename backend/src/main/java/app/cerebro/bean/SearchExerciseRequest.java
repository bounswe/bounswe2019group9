package app.cerebro.bean;

/**
 * @author ahmet.gedemenli
 */

public class SearchExerciseRequest {

  private Long userId;

  private Integer languageId;

  private Integer grade;

  private Integer typeId;

  private String tag;

  public SearchExerciseRequest() {
  }

  public SearchExerciseRequest(Long userId, Integer languageId, Integer grade, Integer typeId, String tag) {
    this.userId = userId;
    this.languageId = languageId;
    this.grade = grade;
    this.typeId = typeId;
    this.tag = tag;
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

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "SearchExerciseRequest{" +
           "userId=" + userId +
           ", languageId=" + languageId +
           ", grade=" + grade +
           ", typeId=" + typeId +
           ", tag='" + tag + '\'' +
           '}';
  }
}
