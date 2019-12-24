package app.mahmuthoca.controller;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.AssignmentEssay;
import app.mahmuthoca.bean.CreateEssayRequest;
import app.mahmuthoca.entity.Essay;
import app.mahmuthoca.service.AssignmentService;
import app.mahmuthoca.service.EssayService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/essays")
@RestController
public class EssayController {

  private final EssayService essayService;

  private final AssignmentService assignmentService;

  public EssayController(EssayService essayService, AssignmentService assignmentService) {
    this.essayService = essayService;
    this.assignmentService = assignmentService;
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

  @GetMapping("/user")
  public Response<List<AssignmentEssay>> getEssaysByUserId(@RequestParam("id") Long id) {
    List<Essay> essays = essayService.getEssaysByUserId(id);
    List<AssignmentEssay> responseData = new ArrayList<>();
    for (Essay essay : essays) {
      responseData.add(new AssignmentEssay(essay, assignmentService.getAssignmentById(essay.getAssignmentId())));
    }
    return HttpResponses.from(responseData);
  }

  @GetMapping("/getSourceByEssayId")
  public String getSourceByEssayId(@RequestParam("id") Long id) {
    Essay essay = essayService.getEssayById(id);
    if (isNull(essay)) {
      return "No essay with id: ".concat(id.toString());
    }

    return essay.getSource();
  }
}
