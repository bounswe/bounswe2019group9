package app.cerebro.controller;

import app.actor.bean.ProfileInfo;
import app.cerebro.bean.RecommendationRequest;
import app.cerebro.bean.SearchUserRequest;
import app.cerebro.service.SearchUserService;
import app.common.HttpResponses;
import app.common.Response;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/recommendations")
@RestController
public class RecommendationController {

  private final SearchUserService searchUserService;

  public RecommendationController(SearchUserService searchUserService) {
    this.searchUserService = searchUserService;
  }

  public Response<List<ProfileInfo>> getRecommendations(@RequestBody RecommendationRequest request) {
    List<ProfileInfo> recommendations =
        searchUserService.searchUser(new SearchUserRequest(null, null, request.getLanguageId(), request.getGrade()))
                         .getData();
    Collections.sort(recommendations);
    return HttpResponses.from(recommendations);
  }
}
