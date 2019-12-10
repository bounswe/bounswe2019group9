package app.mahmuthoca;

/**
 * @author ahmet.gedemenli
 */

public class CreateRatingRequest {

  private Long senderId;

  private Long receiverId;

  private Integer rating;

  public CreateRatingRequest(Long senderId, Long receiverId, Integer rating) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.rating = rating;
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
    return "CreateRatingRequest{" +
           "senderId=" + senderId +
           ", receiverId=" + receiverId +
           ", rating=" + rating +
           '}';
  }
}
