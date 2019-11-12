package app.raven.service;

import app.common.HttpResponses;
import app.common.Response;
import app.raven.entity.Conversation;
import app.raven.repository.ConversationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class ConversationService {

  private final ConversationRepository conversationRepository;

  public ConversationService(ConversationRepository conversationRepository) {
    this.conversationRepository = conversationRepository;
  }

  public Response<List<Conversation>> getConversationsByUserId(Long userId){
    return HttpResponses.from(conversationRepository.getConvesationsByUserId(userId));
  }
}
