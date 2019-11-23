package app.proseidon.service;

import app.common.HttpResponses;
import app.common.Response;
import app.proseidon.repository.ContentRepository;
import app.proseidon.entity.Exercise;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class ContentService {

  private static final String LANGUAGE_NOT_FOUND = "Language not found.";

  private final LanguageService languageService;

  private final TagService tagService;

  private final ContentRepository contentRepository;

  public ContentService(LanguageService languageService, TagService tagService,
                        ContentRepository contentRepository) {
    this.languageService = languageService;
    this.tagService = tagService;
    this.contentRepository = contentRepository;
  }

  public Response<List<Exercise>> getAllExercises() {
    List<Exercise> allExercises = contentRepository.getAllExercises();
    for (Exercise exercise : allExercises) {
      exercise.setTags(tagService.getTagsByExerciseId(exercise.getId()));
    }
    return HttpResponses.from(allExercises);
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

  public Exercise getExerciseById(Long id) {
    Exercise exercise = contentRepository.getExerciseById(id);
    if (nonNull(exercise)) {
      exercise.setTags(tagService.getTagsByExerciseId(exercise.getId()));
    }
    return exercise;
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
    tagService.deleteTagsByExerciseId(id);
  }

  public Integer getNumberOfExercisesByGrade(Integer langId, Integer grade) {
    return contentRepository.getNumberOfExercisesByGrade(langId, grade);
  }
}
