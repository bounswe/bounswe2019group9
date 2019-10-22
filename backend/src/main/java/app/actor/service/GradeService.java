package app.actor.service;

import app.HttpResponses;
import app.Response;
import app.actor.GradeRequest;
import app.actor.entity.Grade;
import app.actor.repository.GradeRepository;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class GradeService {

  private GradeRepository gradeRepository;

  public GradeService(GradeRepository gradeRepository) {
    this.gradeRepository = gradeRepository;
  }

  public Response<GradeRequest> addGrade(GradeRequest request) {
    gradeRepository.addGrade(request.getUserId(), request.getLanguageId(), request.getGrade());
    return HttpResponses.from(request);
  }

  public Response<Grade> getGradeByUserIdAndLanguageId(Long userId, Integer languageId) {
    return HttpResponses.from(gradeRepository.getGradeByUserIdAndLanguageId(userId, languageId));
  }
}
