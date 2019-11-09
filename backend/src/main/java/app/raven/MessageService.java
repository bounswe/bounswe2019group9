package app.raven;

import app.actor.service.LoginService;
import app.actor.service.UserService;
import app.common.HttpResponses;
import app.common.Response;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class MessageService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  private final UserService userService;

  private final MessageRepository messageRepository;

  public MessageService(UserService userService, MessageRepository messageRepository) {
    this.userService = userService;
    this.messageRepository = messageRepository;
  }

  public Response<List<Message>> getMessagesByUserId(Long userId){
    return HttpResponses.from(messageRepository.getMessagesByUserId(userId));
  }

  public Response<List<Message>> createMessage(CreateMessageRequest request) {
    if (userService.getUserById(request.getReceiverId()).getStatus() != HttpResponses.SUCCESSFUL
        || userService.getUserById(request.getSourceId()).getStatus() != HttpResponses.SUCCESSFUL) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }
    messageRepository.createMessage(request.getSourceId(), request.getReceiverId(), request.getContent(), new Date());
    return HttpResponses.from(messageRepository.getMessagesByUserId(request.getSourceId()));
  }
}
