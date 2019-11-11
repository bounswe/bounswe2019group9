package app.raven.service;

import app.actor.service.UserService;
import app.common.HttpResponses;
import app.common.Response;
import app.raven.repository.MessageRepository;
import app.raven.bean.CreateMessageRequest;
import app.raven.entity.Message;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class MessageService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  private static final String SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE = "Sender and receiver can't be same the user";

  private final UserService userService;

  private final MessageRepository messageRepository;

  public MessageService(UserService userService, MessageRepository messageRepository) {
    this.userService = userService;
    this.messageRepository = messageRepository;
  }

  public Response<List<Message>> getMessagesByUserId(Long userId) {
    if (!isIdValid(userId)) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }
    return HttpResponses.from(messageRepository.getMessagesByUserId(userId));
  }

  public Response<List<Message>> getChatContent(Long userId1, Long userId2) {
    if (!isIdValid(userId1) || !isIdValid(userId2)) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }
    return HttpResponses.from(messageRepository.getChatContent(userId1, userId2));
  }

  public Response<List<Message>> createMessage(CreateMessageRequest request) {
    if (!isIdValid(request.getSourceId()) || !isIdValid(request.getReceiverId())) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }
    if (request.getReceiverId() == request.getSourceId()) {
      return HttpResponses.badRequest(SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE);
    }
    messageRepository.createMessage(request.getSourceId(), request.getReceiverId(), request.getContent(), new Date());
    return HttpResponses.from(messageRepository.getMessagesByUserId(request.getSourceId()));
  }

  private boolean isIdValid(Long userId) {
    return userService.getUserById(userId).getStatus() == HttpResponses.SUCCESSFUL;
  }
}
