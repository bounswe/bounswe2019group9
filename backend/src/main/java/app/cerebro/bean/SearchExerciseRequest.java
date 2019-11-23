package app.cerebro.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ahmet.gedemenli
 */

public class SearchExerciseRequest {

  private Integer languageId;

  private Integer grade;

  private Integer typeId;

  private List<String> tags;

  public SearchExerciseRequest() {
  }

  public SearchExerciseRequest(Integer languageId, Integer grade, Integer typeId) {
    this.languageId = languageId;
    this.grade = grade;
    this.typeId = typeId;
    this.tags = new ArrayList<>();
  }

  public SearchExerciseRequest(Integer languageId, Integer grade, Integer typeId, List<String> tags) {
    this.languageId = languageId;
    this.grade = grade;
    this.typeId = typeId;
    this.tags = tags;
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

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "SearchExerciseRequest{" +
           "languageId=" + languageId +
           ", grade=" + grade +
           ", typeId=" + typeId +
           ", tags=" + tags +
           '}';
  }
}
