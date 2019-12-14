package app.mahmuthoca;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "source_id")
  @NotNull
  private Long sourceId;

  @Column(name = "receiver_id")
  @NotNull
  private Long receiverId;

  @Column(name = "content")
  @NotNull
  private String content;

  @Column(name = "created_at")
  @NotNull
  private Date createdAt;

  public Comment() {
  }

  public Comment(@NotNull Long sourceId, @NotNull Long receiverId,
                 @NotNull String content, @NotNull Date createdAt) {
    this.sourceId = sourceId;
    this.receiverId = receiverId;
    this.content = content;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
  }

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Comment{" +
           "id=" + id +
           ", sourceId=" + sourceId +
           ", receiverId=" + receiverId +
           ", content='" + content + '\'' +
           ", createdAt=" + createdAt +
           '}';
  }
}