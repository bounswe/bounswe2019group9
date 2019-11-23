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

  private String tag;

  public SearchExerciseRequest() {
  }

  public SearchExerciseRequest(Integer languageId, Integer grade, Integer typeId, String tag) {
    this.languageId = languageId;
    this.grade = grade;
    this.typeId = typeId;
    this.tag = tag;
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
           "languageId=" + languageId +
           ", grade=" + grade +
           ", typeId=" + typeId +
           ", tag='" + tag + '\'' +
           '}';
  }
}
