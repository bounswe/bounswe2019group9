package app.mahmuthoca.controller;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.CreateAssignmentRequest;
import app.mahmuthoca.entity.Assignment;
import app.mahmuthoca.service.AssignmentService;
import java.util.List;
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

@RequestMapping("/assignments")
@RestController
public class AssignmentController {

  private final AssignmentService assignmentService;

  public AssignmentController(AssignmentService assignmentService) {
    this.assignmentService = assignmentService;
  }

  @PostMapping
  public Response<Assignment> addAssignment(@RequestBody CreateAssignmentRequest request) {
    assignmentService.addAssignment(request);
    return HttpResponses.from(null);
  }

  @GetMapping
  public Response<Assignment> getAssignmentById(@RequestParam("id") Long id) {
    Assignment assignment = assignmentService.getAssignmentById(id);
    if (isNull(assignment)) {
      return HttpResponses.notFound("No assignment with id: ".concat(id.toString()));
    }
    return HttpResponses.from(assignment);
  }

  @GetMapping("/language")
  public Response<List<Assignment>> getAssignmentsByLanguage(@RequestParam("id") Long id) {
    return assignmentService.getAssignmentsByLanguageId(id);
  }
}
