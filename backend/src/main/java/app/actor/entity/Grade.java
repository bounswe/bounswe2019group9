package app.actor.entity;

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
@Table(name = "grades")
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "user_id")
  @NotNull
  private Long userId;

  @Column(name = "language_id")
  @NotNull
  private Integer languageId;

  @Column(name = "grade")
  @NotNull
  private Integer grade;

  public Grade() {
  }

  public Grade(@NotNull Long userId, @NotNull Integer languageId,
               @NotNull Integer grade) {
    this.userId = userId;
    this.languageId = languageId;
    this.grade = grade;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return "Grade{" +
           "id=" + id +
           ", userId=" + userId +
           ", languageId=" + languageId +
           ", grade=" + grade +
           '}';
  }
}
