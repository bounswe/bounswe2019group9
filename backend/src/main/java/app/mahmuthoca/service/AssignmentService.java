package app.mahmuthoca.service;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.CreateAssignmentRequest;
import app.mahmuthoca.entity.Assignment;
import app.mahmuthoca.repository.AssignmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class AssignmentService {

  private final AssignmentRepository assignmentRepository;

  public AssignmentService(AssignmentRepository assignmentRepository) {
    this.assignmentRepository = assignmentRepository;
  }

  public void addAssignment(CreateAssignmentRequest request) {
    assignmentRepository.addAssignment(request.getText(), request.getLanguageId());
  }

  public Assignment getAssignmentById(Long id) {
    return assignmentRepository.getAssignmentById(id);
  }

  public Response<List<Assignment>> getAssignmentsByLanguageId(Long languageId) {
    return HttpResponses.from(assignmentRepository.getAssignmentsByLanguageId(languageId));
  }
}
