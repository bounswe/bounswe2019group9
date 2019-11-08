package app.cerebro;

import app.actor.bean.ProfileInfo;
import app.common.HttpResponses;
import app.common.Response;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RestController
@RequestMapping("/search")
public class SearchController {

  @GetMapping("/people")
  public Response<List<ProfileInfo>> searchPeople() {
    return HttpResponses.from(null);
  }
}
