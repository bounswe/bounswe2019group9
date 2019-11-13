package app.proseidon.bean;

/**
 * @author ahmet.gedemenli
 */

public class TagCreateRequest {

  private Long exerciseId;

  private String tagText;

  public TagCreateRequest(Long exerciseId, String tagText) {
    this.exerciseId = exerciseId;
    this.tagText = tagText;
  }

  public Long getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(Long exerciseId) {
    this.exerciseId = exerciseId;
  }

  public String getTagText() {
    return tagText;
  }

  public void setTagText(String tagText) {
    this.tagText = tagText;
  }

  @Override
  public String toString() {
    return "TagCreateRequest{" +
           "exerciseId=" + exerciseId +
           ", tagText='" + tagText + '\'' +
           '}';
  }
}
