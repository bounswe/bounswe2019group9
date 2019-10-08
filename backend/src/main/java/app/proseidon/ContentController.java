package app.proseidon;

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

  public ContentController(LanguageService languageService) {
    this.languageService = languageService;
  }

  @GetMapping("/languages")
  public Response<List<String>> getAllLanguages() {
    return languageService.getAllLanguages();
  }
}
