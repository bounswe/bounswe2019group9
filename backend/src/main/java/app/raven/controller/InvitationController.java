package app.raven.controller;

import app.actor.bean.ProfileInfo;
import app.common.Response;
import app.raven.bean.CreateInvitationRequest;
import app.raven.bean.AnswerInvitationRequest;
import app.raven.bean.InvitationState;
import app.raven.service.InvitationService;
import app.raven.entity.Invitation;
import app.raven.entity.Conversation;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arda.budak
 */

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping("/byReceiverId")
    public Response<List<ProfileInfo>> getInviterProfileInfosByReceiverId(@RequestParam("userId") Long userId) {
        return invitationService.getInviterProfileInfosByReceiverId(userId);
    }

    @GetMapping("/state")
    public Response<InvitationState> getInvitationState(@RequestParam("userId1") Long userId1,
                                                          @RequestParam("userId2") Long userId2) {
        return invitationService.getInvitationState(userId1, userId2);
    }

    @PostMapping("/add")
    public Response<Invitation> createInvitation(@Validated @RequestBody CreateInvitationRequest request) {
        return invitationService.createInvitation(request);
    }

    @PostMapping("/answer")
    public Response<Conversation> answerToInvitation(@Validated @RequestBody AnswerInvitationRequest request) {
        return invitationService.answerToInvitation(request);
    }
}
