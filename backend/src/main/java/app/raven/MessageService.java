package app.raven;

import app.actor.service.LoginService;
import app.actor.service.UserService;
import app.common.HttpResponses;
import app.common.Response;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class MessageService {

  private final UserService userService;

  public MessageService(UserService userService) {
    this.userService = userService;
  }

  public Response<List<Message>> createMessage(CreateMessageRequest request) {
    if (userService.getUserById(request.getReceiverId()).getStatus() != HttpResponses.SUCCESSFUL
        || userService.getUserById(request.getSourceId()).getStatus() != HttpResponses.SUCCESSFUL) {
      return HttpResponses.badRequest(LoginService.USER_NOT_FOUND_MESSAGE);
    }
  }
}
