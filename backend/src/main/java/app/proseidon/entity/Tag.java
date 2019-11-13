package app.proseidon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ahmet.gedemenli
 */

@Entity
@Table(name = "tags")
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long exerciseId;

  private String tagText;

  public Tag() {
  }

  public Tag(Long exerciseId, String tagText) {
    this.exerciseId = exerciseId;
    this.tagText = tagText;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return "Tag{" +
           "id=" + id +
           ", exerciseId=" + exerciseId +
           ", tagText='" + tagText + '\'' +
           '}';
  }
}
