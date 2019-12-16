package app.cerebro.bean;

/**
 * @author ahmet.gedemenli
 */

public class RecommendationRequest {

  private Integer languageId;

  private Integer grade;

  public RecommendationRequest(Integer languageId, Integer grade) {
    this.languageId = languageId;
    this.grade = grade;
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
    return "RecommendationRequest{" +
           "languageId=" + languageId +
           ", grade=" + grade +
           '}';
  }
}
