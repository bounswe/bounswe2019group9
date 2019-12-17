package app.mahmuthoca.bean;

/**
 * @author ahmet.gedemenli
 */

public class CreateReviewRequestRequest {

  private Long sourceId;

  private Long receiverId;

  private Long essayId;

  public CreateReviewRequestRequest(Long sourceId, Long receiverId, Long essayId) {
    this.sourceId = sourceId;
    this.receiverId = receiverId;
    this.essayId = essayId;
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
    return "CreateReviewRequestRequest{" +
           "sourceId=" + sourceId +
           ", receiverId=" + receiverId +
           ", essayId=" + essayId +
           '}';
  }
}
