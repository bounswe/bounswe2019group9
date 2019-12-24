package app.raven.controller;

import app.actor.bean.ProfileInfo;
import app.common.Response;
import app.raven.service.ConversationService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RestController
@RequestMapping("/conversations")
public class ConversationController {

  private final ConversationService conversationService;

  public ConversationController(ConversationService conversationService) {
    this.conversationService = conversationService;
  }

  @GetMapping
  public Response<List<ProfileInfo>> getConversationsByUserId(@RequestParam("id") Long userId){
    return conversationService.getConversationProfileInfosByUserId(userId);
  }
}
