package app.mahmuthoca.controller;

import app.common.Response;
import app.mahmuthoca.bean.CreateAssignmentRequest;
import app.mahmuthoca.entity.Assignment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/assignments")
@RestController
public class AssignmentController {

  @PostMapping
  public Response<Assignment> addAssignment(CreateAssignmentRequest request) {

  }
}
