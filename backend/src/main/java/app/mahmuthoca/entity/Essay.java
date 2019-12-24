package app.mahmuthoca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ahmet.gedemenli
 */

@Entity
@Table(name = "essays")
public class Essay {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "assignment_id")
  private Long assignmentId;

  @Column(name = "author_id")
  private Long authorId;

  @Column(name = "source_type")
  private Integer sourceType;

  @Column(name = "source")
  private String source;

  public Essay() {
  }

  public Essay(Long assignmentId, Long authorId, Integer type, String source) {
    this.assignmentId = assignmentId;
    this.authorId = authorId;
    this.sourceType = sourceType;
    this.source = source;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Integer getSourceType() {
    return sourceType;
  }

  public void setSourceType(Integer sourceType) {
    this.sourceType = sourceType;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  @Override
  public String toString() {
    return "Essay{" +
           "id=" + id +
           ", assignmentId=" + assignmentId +
           ", authorId=" + authorId +
           ", sourceType=" + sourceType +
           ", source='" + source + '\'' +
           '}';
  }
}
