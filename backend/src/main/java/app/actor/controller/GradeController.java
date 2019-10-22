package app.actor.controller;

import app.Response;
import app.actor.GradeRequest;
import app.actor.entity.Grade;
import app.actor.service.GradeService;
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
@RequestMapping("/grades")
public class GradeController {

  private GradeService gradeService;

  public GradeController(GradeService gradeService) {
    this.gradeService = gradeService;
  }

  @GetMapping("/get")
  public Response<Grade> getGrade(@RequestParam Long userId, @RequestParam Integer languageId) {
    return gradeService.getGradeByUserIdAndLanguageId(userId, languageId);
  }

  @PostMapping("/add")
  public Response<GradeRequest> addGrade(@RequestBody GradeRequest request) {
    return gradeService.addGrade(request);
  }
}
