package app.proseidon.controller;

import app.common.HttpResponses;
import app.common.Response;
import app.proseidon.entity.Exercise;
import app.proseidon.service.ContentService;
import java.util.List;
import org.springframework.validation.annotation.Validated;
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

@RestController
@RequestMapping("/contents")
public class ContentController {

  private ContentService contentService;

  public ContentController(ContentService contentService) {
    this.contentService = contentService;
  }

  @GetMapping
  public Response<Exercise> getExerciseById(@RequestParam(value = "id") Long id) {
    Exercise exercise = contentService.getExerciseById(id);
    if (isNull(exercise)) {
      return HttpResponses.notFound("No exercise with this id.");
    }
    return HttpResponses.from(exercise);
  }

  @GetMapping("/languages")
  public Response<List<String>> getAllLanguages() {
    return contentService.getLanguageService().getAllLanguages();
  }

  @GetMapping("/all")
  public Response<List<Exercise>> getAllExercises() {
    return contentService.getAllExercises();
  }

  @GetMapping("/prof")
  public Response<List<Exercise>> getProficiencyExam(
      @RequestParam(value = "language", defaultValue = "English") String language) {
    return contentService.getProficiencyExam(language);
  }

  @PostMapping("/add")
  public Response<Exercise> addExercise(@Validated @RequestBody Exercise exercise) {
    return contentService.addExercise(exercise);
  }

  @GetMapping("/delete")
  public Response<Integer> deleteExercise(@RequestParam(value = "id") Long id) {
    contentService.deleteExercise(id);
    return HttpResponses.from(1);
  }
}