package app.proseidon;

import app.common.HttpResponses;
import app.common.Response;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class ContentService {

  private static final String LANGUAGE_NOT_FOUND = "Language not found.";

  private LanguageService languageService;

  private ContentRepository contentRepository;

  public ContentService(LanguageService languageService, ContentRepository contentRepository) {
    this.languageService = languageService;
    this.contentRepository = contentRepository;
  }

  public Response<List<Exercise>> getAllExercises() {
    return HttpResponses.from(contentRepository.getAllExercises());
  }

  public Response<List<Exercise>> getProficiencyExam(String language) {
    Integer languageId = languageService.getLanguageIdByName(language);
    if (isNull(languageId)) {
      return HttpResponses.notFound(LANGUAGE_NOT_FOUND);
    }
    return HttpResponses.from(contentRepository.getProficiencyExam(languageId));
  }

  public LanguageService getLanguageService() {
    return languageService;
  }

  public Response<Exercise> addExercise(Exercise exercise) {
    contentRepository.addExercise(exercise.getLanguageId(), exercise.getTypeId(), exercise.getImageUrl(),
                                  exercise.getSoundUrl(), exercise.getQuestionBody(), exercise.getOptionA(),
                                  exercise.getOptionB(), exercise.getOptionC(), exercise.getOptionD(),
                                  exercise.getCorrectAnswer());
    return HttpResponses.from(exercise);
  }

  public void deleteExercise(Long id) {
    contentRepository.deleteExercise(id);
  }
}
