package app.proseidon;

import app.common.HttpResponses;
import app.common.Response;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class LanguageService {

  private LanguageRepository languageRepository;

  public LanguageService(LanguageRepository languageRepository) {
    this.languageRepository = languageRepository;
  }

  public Response<List<String>> getAllLanguages() {
    return HttpResponses.from(languageRepository.getAllLanguages());
  }

  public Integer getLanguageIdByName(String name) {
    return languageRepository.getLanguageIdByName(name);
  }
}
