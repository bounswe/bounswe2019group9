package app.raven.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author arda.budak
 */

@Entity
@Table(name = "invitations")
public class Invitation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "source_id")
  @NotNull
  private Long sourceId;

  @Column(name = "receiver_id")
  @NotNull
  private Long receiverId;

  @Column(name = "created_at")
  @NotNull
  private Date createdAt;

  public Invitation() {
  }

  public Invitation(@NotNull Long sourceId, @NotNull Long receiverId, @NotNull Date createdAt) {
    this.sourceId = sourceId;
    this.receiverId = receiverId;
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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Invitation{" +
           "id=" + id +
           ", sourceId=" + sourceId +
           ", receiverId=" + receiverId +
           ", createdAt=" + createdAt +
           '}';
  }
}