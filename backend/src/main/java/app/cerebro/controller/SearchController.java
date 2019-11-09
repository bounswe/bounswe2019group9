package app.cerebro.controller;

import app.actor.bean.ProfileInfo;
import app.cerebro.service.SearchOperatorService;
import app.cerebro.bean.SearchUserRequest;
import app.common.Response;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RestController
@RequestMapping("/search")
public class SearchController {

  private final SearchOperatorService searchOperatorService;

  public SearchController(SearchOperatorService searchOperatorService) {
    this.searchOperatorService = searchOperatorService;
  }

  @PostMapping("/users")
  public Response<List<ProfileInfo>> searchUser(@RequestBody SearchUserRequest request) {
    return searchOperatorService.getSearchUserService().searchUser(request);
  }
}
