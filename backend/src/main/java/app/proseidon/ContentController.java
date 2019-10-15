package app.proseidon;

import app.HttpResponses;
import app.Response;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RestController
@RequestMapping("/contents")
public class ContentController {

  private LanguageService languageService;

  private ContentService contentService;

  public ContentController(LanguageService languageService, ContentService contentService) {
    this.languageService = languageService;
    this.contentService = contentService;
  }

  @GetMapping("/languages")
  public Response<List<String>> getAllLanguages() {
    return languageService.getAllLanguages();
  }

  @GetMapping("/all")
  public Response<List<Exercise>> getAllExercises() {
    return contentService.getAllExercises();
  }
}
