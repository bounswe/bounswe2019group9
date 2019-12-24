package app.raven.service;

import app.actor.bean.ProfileInfo;
import app.actor.service.ProfileService;
import app.common.HttpResponses;
import app.common.Response;
import app.raven.entity.Conversation;
import app.raven.repository.ConversationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class ConversationService {

  private final ProfileService profileService;

  private final ConversationRepository conversationRepository;

  public ConversationService(ProfileService profileService,
                             ConversationRepository conversationRepository) {
    this.profileService = profileService;
    this.conversationRepository = conversationRepository;
  }

  public List<Conversation> getConversationsByUserId(Long userId) {
    return conversationRepository.getConvesationsByUserId(userId);
  }

  public Response<List<ProfileInfo>> getConversationProfileInfosByUserId(Long userId) {
    List<Conversation> conversations = getConversationsByUserId(userId);
    ArrayList<ProfileInfo> responseBody = new ArrayList<>();
    for (Conversation conversation : conversations) {
      responseBody.add(getProfileInfoFromConversation(conversation, userId));
    }
    return HttpResponses.from(responseBody);
  }

  private ProfileInfo getProfileInfoFromConversation(Conversation conversation, Long userId) {
    return profileService.getProfileInfoByUserId(
        conversation.getUserIdOne() == userId ? conversation.getUserIdTwo() : conversation.getUserIdOne());
  }
}
