package app.proseidon;

import app.HttpResponses;
import app.Response;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class ContentService {

  private ContentRepository contentRepository;

  public ContentService(ContentRepository contentRepository) {
    this.contentRepository = contentRepository;
  }

  public Response<List<Exercise>> getAllExercises() {
    return HttpResponses.from(contentRepository.getAllExercises());
  }
}
