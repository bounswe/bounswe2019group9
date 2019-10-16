package app.actor.controller;

import app.Response;
import app.actor.GradeRequest;
import app.actor.service.GradeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RestController
@RequestMapping("/grades")
public class GradeController {

  private GradeService gradeService;

  public GradeController(GradeService gradeService) {
    this.gradeService = gradeService;
  }

  @PostMapping("/add")
  public Response<GradeRequest> addGrade(@RequestBody GradeRequest request) {
    return gradeService.addGrade(request);
  }
}
