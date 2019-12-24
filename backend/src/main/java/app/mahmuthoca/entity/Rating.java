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
@Table(name = "ratings")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "sender_id")
  @NotNull
  private Long senderId;

  @Column(name = "receiver_id")
  @NotNull
  private Long receiverId;

  @Column(name = "rating")
  @NotNull
  private Integer rating;

  public Rating() {
  }

  public Rating(@NotNull Long senderId, @NotNull Long receiverId,
                @NotNull Integer rating) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.rating = rating;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSenderId() {
    return senderId;
  }

  public void setSenderId(Long senderId) {
    this.senderId = senderId;
  }

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Rating{" +
           "id=" + id +
           ", senderId=" + senderId +
           ", receiverId=" + receiverId +
           ", rating=" + rating +
           '}';
  }
}
