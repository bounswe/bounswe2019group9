package app.raven.bean;

import javax.validation.constraints.NotNull;

/**
 * @author arda.budak
 */

public class CreateInvitationRequest {

    @NotNull
    private Long sourceId;

    @NotNull
    private Long receiverId;


    public CreateInvitationRequest(@NotNull Long sourceId, @NotNull Long receiverId) {
        this.sourceId = sourceId;
        this.receiverId = receiverId;
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


    @Override
    public String toString() {
        return "CreateInvitationRequest{" +
                "sourceId=" + sourceId +
                ", receiverId=" + receiverId +
                '}';
    }
}
