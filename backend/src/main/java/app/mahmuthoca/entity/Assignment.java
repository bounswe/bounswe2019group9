package app.mahmuthoca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author ahmet.gedemenli
 */

@Entity
@Table(name = "assignments")
public class Assignment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "question")
  @NotNull
  private String question;

  @Column(name = "language_id")
  private Long languageId;

  public Assignment(@NotNull String question, Long languageId) {
    this.question = question;
    this.languageId = languageId;
  }

  public Assignment() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public Long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Long languageId) {
    this.languageId = languageId;
  }

  @Override
  public String toString() {
    return "Assignment{" +
           "id=" + id +
           ", question='" + question + '\'' +
           ", languageId=" + languageId +
           '}';
  }
}
