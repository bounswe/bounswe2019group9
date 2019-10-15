package app.proseidon;

import app.Response;
import java.util.List;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/contents")
public class ContentController {

  private ContentService contentService;

  public ContentController(ContentService contentService) {
    this.contentService = contentService;
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
}
