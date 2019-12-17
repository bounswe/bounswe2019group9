package app.mahmuthoca.controller;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.CreateEssayRequest;
import app.mahmuthoca.entity.Essay;
import app.mahmuthoca.service.EssayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/essays")
@RestController
public class EssayController {

  private final EssayService essayService;

  public EssayController(EssayService essayService) {
    this.essayService = essayService;
  }

  @PostMapping
  public Response<Essay> addEssay(@RequestBody CreateEssayRequest request) {
    essayService.addEssay(request);
    return HttpResponses.from(null);
  }

  @GetMapping
  public Response<Essay> getEssayById(@RequestParam("id") Long id) {
    Essay essay = essayService.getEssayById(id);
    if (isNull(essay)) {
      return HttpResponses.notFound("No essay with id: ".concat(id.toString()));
    }
    return HttpResponses.from(essay);
  }
}
