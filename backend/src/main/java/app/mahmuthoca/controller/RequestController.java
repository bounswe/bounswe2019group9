package app.mahmuthoca.controller;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.CreateReviewRequestRequest;
import app.mahmuthoca.entity.Request;
import app.mahmuthoca.service.RequestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/requests")
@RestController
public class RequestController {

  private final RequestService requestService;

  public RequestController(RequestService requestService) {
    this.requestService = requestService;
  }

  @PostMapping
  public Response<Request> addRequest(@RequestBody CreateReviewRequestRequest request) {
    requestService.addRequest(request);
    return HttpResponses.from(null);
  }
}
