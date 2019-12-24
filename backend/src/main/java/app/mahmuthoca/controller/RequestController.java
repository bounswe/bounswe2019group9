package app.mahmuthoca.controller;

import app.actor.entity.User;
import app.actor.service.UserService;
import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.CreateReviewRequestRequest;
import app.mahmuthoca.bean.ReviewRequestDTO;
import app.mahmuthoca.entity.Essay;
import app.mahmuthoca.entity.Request;
import app.mahmuthoca.service.AssignmentService;
import app.mahmuthoca.service.EssayService;
import app.mahmuthoca.service.RequestService;
import java.util.ArrayList;
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

@RequestMapping("/requests")
@RestController
public class RequestController {

  private final RequestService requestService;

  private final AssignmentService assignmentService;

  private final EssayService essayService;

  private final UserService userService;

  public RequestController(RequestService requestService, AssignmentService assignmentService,
                           EssayService essayService, UserService userService) {
    this.requestService = requestService;
    this.assignmentService = assignmentService;
    this.essayService = essayService;
    this.userService = userService;
  }

  @GetMapping
  public Response<List<ReviewRequestDTO>> getRequestsByReceiverId(@RequestParam("userId") Long id) {
    List<Request> requests = requestService.getRequestsByReceiverId(id);
    List<ReviewRequestDTO> response = new ArrayList<>();
    for (Request request : requests) {
      User user = userService.getUserById(request.getSourceId()).getData();
      Essay essay = essayService.getEssayById(request.getEssayId());
      response.add(new ReviewRequestDTO(request.getEssayId(),
                                        user.getFirstName().concat(" ").concat(user.getLastName()),
                                        assignmentService.getAssignmentById(essay.getAssignmentId()).getQuestion()));
    }
    return HttpResponses.from(response);
  }

  @PostMapping
  public Response<Request> addRequest(@RequestBody CreateReviewRequestRequest request) {
    requestService.addRequest(request);
    return HttpResponses.from(null);
  }
}
