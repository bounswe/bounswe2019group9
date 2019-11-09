package app.raven.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author ahmet.gedemenli
 */

public class CreateMessageRequest {

  @NotNull
  private Long sourceId;

  @NotNull
  private Long receiverId;

  @NotEmpty(message = "Can't create an empty message.")
  private String content;

  public CreateMessageRequest(@NotNull Long sourceId, @NotNull Long receiverId,
                              @NotEmpty(message = "Can't create an empty message.") String content) {
    this.sourceId = sourceId;
    this.receiverId = receiverId;
    this.content = content;
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

  @Override
  public String toString() {
    return "CreateMessageRequest{" +
           "sourceId=" + sourceId +
           ", receiverId=" + receiverId +
           ", content='" + content + '\'' +
           '}';
  }
}
