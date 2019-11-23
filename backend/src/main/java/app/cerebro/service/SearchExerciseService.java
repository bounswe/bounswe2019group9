package app.cerebro.service;

import app.cerebro.bean.SearchExerciseRequest;
import app.common.HttpResponses;
import app.common.Response;
import app.proseidon.entity.Exercise;
import app.proseidon.service.ContentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class SearchExerciseService {

  private final ContentService contentService;

  public SearchExerciseService(ContentService contentService) {
    this.contentService = contentService;
  }

  public Response<List<Exercise>> searchExercise(SearchExerciseRequest request) {
    List<Exercise> exercises = contentService.getAllExercises().getData();
    exercises = filterByLanguage(exercises, request.getLanguageId());
    exercises = filterByGrade(exercises, request.getGrade());
    exercises = filterByType(exercises, request.getTypeId());
    return HttpResponses.from(exercises);
  }

  private List<Exercise> filterByType(List<Exercise> exercises, Integer typeId) {
    if (isNull(typeId) || typeId < 1) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      if (exercise.getTypeId() == typeId) {
        filtered.add(exercise);
      }
    }
    return filtered;
  }

  private List<Exercise> filterByGrade(List<Exercise> exercises, Integer grade) {
    if (isNull(grade) || grade < 1 || grade > 6) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      if (exercise.getGrade() == grade) {
        filtered.add(exercise);
      }
    }
    return filtered;
  }

  private List<Exercise> filterByLanguage(List<Exercise> exercises, Integer languageId) {
    if (isNull(languageId) || languageId < 1) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      if (exercise.getLanguageId() == languageId) {
        filtered.add(exercise);
      }
    }
    return filtered;
  }
}
