package app.raven.bean;

public class InvitationState {

    private Long userId1;

    private Long userId2;

    private boolean pendingRequestFromOneToTwo;

    private boolean pendingRequestFromTwoToOne;

    private boolean startedConversation;

    public InvitationState(Long userId1, Long userId2, boolean pendingRequestFromOneToTwo, boolean pendingRequestFromTwoToOne, boolean startedConversation) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.pendingRequestFromOneToTwo = pendingRequestFromOneToTwo;
        this.pendingRequestFromTwoToOne = pendingRequestFromTwoToOne;
        this.startedConversation = startedConversation;
    }

    public Long getUserId1() {
        return userId1;
    }

    public void setUserId1(Long userId1) {
        this.userId1 = userId1;
    }

    public Long getUserId2() {
        return userId2;
    }

    public void setUserId2(Long userId2) {
        this.userId2 = userId2;
    }

    public boolean isPendingRequestFromOneToTwo() {
        return pendingRequestFromOneToTwo;
    }

    public void setPendingRequestFromOneToTwo(boolean pendingRequestFromOneToTwo) {
        this.pendingRequestFromOneToTwo = pendingRequestFromOneToTwo;
    }

    public boolean isPendingRequestFromTwoToOne() {
        return pendingRequestFromTwoToOne;
    }

    public void setPendingRequestFromTwoToOne(boolean pendingRequestFromTwoToOne) {
        this.pendingRequestFromTwoToOne = pendingRequestFromTwoToOne;
    }

    public boolean isStartedConversation() {
        return startedConversation;
    }

    public void setStartedConversation(boolean startedConversation) {
        this.startedConversation = startedConversation;
    }

    @Override
    public String toString() {
        return "InvitationState{" +
                "userId1=" + userId1 +
                ", userId2=" + userId2 +
                ", pendingRequestFromOneToTwo=" + pendingRequestFromOneToTwo +
                ", pendingRequestFromTwoToOne=" + pendingRequestFromTwoToOne +
                ", startedConversation=" + startedConversation +
                '}';
    }
}
