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
@Table(name = "requests")
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "source_id")
  private Long sourceId;

  @Column(name = "receiver_id")
  private Long receiverId;

  @Column(name = "essay_id")
  private Long essayId;

  public Request(Long sourceId, Long receiverId, Long essayId) {
    this.sourceId = sourceId;
    this.receiverId = receiverId;
    this.essayId = essayId;
  }

  public Request() {
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

  public Long getEssayId() {
    return essayId;
  }

  public void setEssayId(Long essayId) {
    this.essayId = essayId;
  }

  @Override
  public String toString() {
    return "Request{" +
           "id=" + id +
           ", sourceId=" + sourceId +
           ", receiverId=" + receiverId +
           ", essayId=" + essayId +
           '}';
  }
}
