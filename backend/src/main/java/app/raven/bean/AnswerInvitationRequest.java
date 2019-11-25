package app.raven.bean;

import javax.validation.constraints.NotNull;

/**
 * @author arda.budak
 */

public class AnswerInvitationRequest {

  @NotNull
  private Long sourceId;

  @NotNull
  private Long receiverId;

  private boolean isApproved;

  public AnswerInvitationRequest(@NotNull Long sourceId, @NotNull Long receiverId, boolean isApproved) {
    this.sourceId = sourceId;
    this.receiverId = receiverId;
    this.isApproved = isApproved;
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

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean approved) {
    isApproved = approved;
  }

  @Override
  public String toString() {
    return "CreateInvitationRequest{" +
           "sourceId=" + sourceId +
           ", receiverId=" + receiverId +
           ", isApproved=" + isApproved +
           '}';
  }
}
