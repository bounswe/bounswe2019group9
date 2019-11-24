package app.raven.service;

import app.actor.service.UserService;
import app.common.HttpResponses;
import app.common.Response;
import app.raven.repository.ConversationRepository;
import app.raven.repository.InvitationRepository;
import app.raven.bean.CreateInvitationRequest;
import app.raven.bean.AnswerInvitationRequest;
import app.raven.bean.InvitationState;
import app.raven.entity.Invitation;
import app.raven.entity.Conversation;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author arda.budak
 */

@Service
public class InvitationService
{
    private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

    private static final String SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE = "Sender and receiver can't be same.";

    private static final String INVITATION_EXISTS_MESSAGE = "Invitation already exists";

    private final InvitationRepository invitationRepository;

    private final ConversationRepository conversationRepository;

    private final UserService userService;

    public InvitationService(InvitationRepository invitationRepository, ConversationRepository conversationRepository, UserService userService) {
        this.invitationRepository = invitationRepository;
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    public Response<List<Invitation>> getInvitationsByReceiverId(Long userId)
    {
        if (!isIdValid(userId))
        {
            return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
        }
        return HttpResponses.from(invitationRepository.getInvitationsByReceiverId(userId));
    }


    public Response<InvitationState> getInvitationState(Long userId1, Long userId2)
    {
        if (!isIdValid(userId1) || !isIdValid(userId2))
        {
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

    /*
    public InvitationState getInvitationState(Long userId1, Long userId2)
    {

        if (!isIdValid(userId1) || !isIdValid(userId2))
        {
            return null;
        }

        InvitationState invitationState = new InvitationState(
                userId1,
                userId2,
                !isNull(invitationRepository.getInvitationFromAtoB(userId1, userId2)),
                !isNull(invitationRepository.getInvitationFromAtoB(userId2, userId1)),
                !isNull(conversationRepository.getConversationById(userId1, userId2))
        );

        return invitationState;

    }*/

    public Response<Invitation> createInvitation(CreateInvitationRequest request)
    {
        if (!isIdValid(request.getSourceId()) || !isIdValid(request.getReceiverId()))
        {
            return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
        }


        if (request.getReceiverId() == request.getSourceId())
        {
            return HttpResponses.badRequest(SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE);
        }

        Date now = new Date();

        if (isNull(invitationRepository.getInvitationFromAtoB(request.getSourceId(), request.getReceiverId()))) ///////STATELI
        {
            invitationRepository.createInvitation(request.getSourceId(), request.getReceiverId(), now);
        }
        else
            {
                return HttpResponses.badRequest(INVITATION_EXISTS_MESSAGE);
        }

        return HttpResponses.from(invitationRepository.getInvitationFromAtoB(request.getSourceId(), request.getReceiverId()));
    }


    public Response<Conversation> answerToInvitation(AnswerInvitationRequest request)
    {
        //Response response = getInvitationState(request.getSourceId(), request.getReceiverId());

        //InvitationState invitationState = response.getData();

        //InvitationState invitationState = getInvitationState(request.getSourceId(), request.getReceiverId());

        if(request.isApproved())
        {
            //////////////////// Handling mistakes

            invitationRepository.deleteInvitation(request.getSourceId(), request.getReceiverId());

            Date now = new Date();
            conversationRepository.createConversation(request.getSourceId(), request.getReceiverId(), now);

            return HttpResponses.from(conversationRepository.getConversationById(request.getSourceId(), request.getReceiverId()));

        }

        else
        {
            invitationRepository.deleteInvitation(request.getSourceId(), request.getReceiverId());

            return HttpResponses.from(conversationRepository.getConversationById(request.getSourceId(), request.getReceiverId()));
        }

    }



    private boolean isIdValid(Long userId) {
        return userService.getUserById(userId).getStatus() == HttpResponses.SUCCESSFUL;
    }

}