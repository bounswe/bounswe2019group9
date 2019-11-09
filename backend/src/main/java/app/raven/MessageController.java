package app.raven;

import app.common.HttpResponses;
import app.common.Response;
import java.util.List;
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

  @GetMapping
  public Response<List<Message>> getMessagesByUserId(@RequestParam("userId") Long userId) {
    return HttpResponses.from(null);
  }

  @PostMapping
  public Response<List<Message>> createMessage(@RequestBody CreateMessageRequest request){
    return HttpResponses.from(null);
  }
}
