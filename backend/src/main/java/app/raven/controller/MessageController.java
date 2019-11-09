package app.raven.controller;

import app.common.Response;
import app.raven.bean.CreateMessageRequest;
import app.raven.entity.Message;
import app.raven.service.MessageService;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping
  public Response<List<Message>> getMessagesByUserId(@RequestParam("userId") Long userId) {
    return messageService.getMessagesByUserId(userId);
  }

  @PostMapping
  public Response<List<Message>> createMessage(@Validated @RequestBody CreateMessageRequest request){
    return messageService.createMessage(request);
  }
}
