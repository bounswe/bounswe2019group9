package app.raven.service;

import app.actor.bean.ProfileInfo;
import app.actor.service.UserService;
import app.actor.service.ProfileService;
import app.common.HttpResponses;
import app.common.Response;
import app.raven.repository.ConversationRepository;
import app.raven.repository.InvitationRepository;
import app.raven.bean.CreateInvitationRequest;
import app.raven.bean.AnswerInvitationRequest;
import app.raven.bean.InvitationState;
import app.raven.entity.Invitation;
import app.raven.entity.Conversation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author arda.budak
 */

@Service
public class InvitationService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  private static final String SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE = "Sender and receiver can't be same.";

  private static final String INVITATION_EXISTS_MESSAGE = "Invitation already exists";

  private static final String CONVERSATION_EXISTS_MESSAGE = "Conversation already exists";

  private final InvitationRepository invitationRepository;

  private final ConversationRepository conversationRepository;

  private final UserService userService;

  private final ProfileService profileService;

  public InvitationService(InvitationRepository invitationRepository, ConversationRepository conversationRepository,
                           UserService userService, ProfileService profileService) {
    this.invitationRepository = invitationRepository;
    this.conversationRepository = conversationRepository;
    this.userService = userService;
    this.profileService = profileService;
  }

  public Response<List<ProfileInfo>> getInviterProfileInfosByReceiverId(Long userId) {
    List<Invitation> invitations = invitationRepository.getInvitationsByReceiverId(userId);

    ArrayList<ProfileInfo> responseBody = new ArrayList<>();

    for (Invitation invitation : invitations) {
      responseBody.add(profileService.getProfileInfoByUserId(invitation.getSourceId()));
    }
    return HttpResponses.from(responseBody);
  }

  public Response<InvitationState> getInvitationState(Long userId1, Long userId2) {
    if (!isIdValid(userId1) || !isIdValid(userId2)) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }

    InvitationState invitationState = new InvitationState(
        userId1,
        userId2,
        !isNull(invitationRepository.getInvitationFromAtoB(userId1, userId2)),
        !isNull(invitationRepository.getInvitationFromAtoB(userId2, userId1)),
        !isNull(conversationRepository.getConversationById(userId1, userId2))
    );

    return HttpResponses.from(invitationState);
  }

  public Response<Invitation> createInvitation(CreateInvitationRequest request) {
    if (!isIdValid(request.getSourceId()) || !isIdValid(request.getReceiverId())) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }

    if (request.getReceiverId() == request.getSourceId()) {
      return HttpResponses.badRequest(SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE);
    }

    Date now = new Date();

    if (!isNull(invitationRepository.getInvitationFromAtoB(request.getSourceId(), request.getReceiverId()))
        ||
        !isNull(invitationRepository.getInvitationFromAtoB(request.getReceiverId(), request.getSourceId()))
    ) {
      return HttpResponses.badRequest(INVITATION_EXISTS_MESSAGE);
    } else if (
        !isNull(conversationRepository.getConversationById(request.getSourceId(), request.getReceiverId()))
    ) {
      return HttpResponses.badRequest(CONVERSATION_EXISTS_MESSAGE);
    } else {
      invitationRepository.createInvitation(request.getSourceId(), request.getReceiverId(), now);
    }

    return HttpResponses.from(
        invitationRepository.getInvitationFromAtoB(request.getSourceId(), request.getReceiverId()));
  }

  public Response<Conversation> answerToInvitation(AnswerInvitationRequest request) {

    if (request.isApproved()) {
      invitationRepository.deleteInvitation(request.getSourceId(), request.getReceiverId());

      invitationRepository.deleteInvitation(request.getReceiverId(), request.getSourceId());

      Date now = new Date();
      conversationRepository.createConversation(request.getSourceId(), request.getReceiverId(), now);

      return HttpResponses.from(
          conversationRepository.getConversationById(request.getSourceId(), request.getReceiverId()));
    } else {
      invitationRepository.deleteInvitation(request.getSourceId(), request.getReceiverId());

      invitationRepository.deleteInvitation(request.getReceiverId(), request.getSourceId());

      return HttpResponses.successful();
    }
  }

  private boolean isIdValid(Long userId) {
    return userService.getUserById(userId).getStatus() == HttpResponses.SUCCESSFUL;
  }
}