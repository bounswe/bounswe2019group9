package app.proseidon.service;

import app.common.HttpResponses;
import app.common.Response;
import app.proseidon.bean.TagCreateRequest;
import app.proseidon.entity.Tag;
import app.proseidon.repository.ContentRepository;
import app.proseidon.repository.TagRepository;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class TagService {

  private static final String WRONG_EXERCISE_ID = "Exercise not found with this id.";

  private final ContentRepository contentRepository;

  private final TagRepository tagRepository;

  public TagService(ContentRepository contentRepository, TagRepository tagRepository) {
    this.contentRepository = contentRepository;
    this.tagRepository = tagRepository;
  }

  public List<Tag> getTagsByExerciseId(Long exerciseId) {
    return tagRepository.getTagsByExerciseId(exerciseId);
  }

  public void deleteTagsByExerciseId(Long exerciseId) {
    tagRepository.deleteTagsByExerciseId(exerciseId);
  }

  public Response<Tag> createTag(TagCreateRequest request) {
    if(isNull(contentRepository.getExerciseById(request.getExerciseId()))){
      return HttpResponses.badRequest(WRONG_EXERCISE_ID);
    }
    Tag tag = tagRepository.getTagByExerciseAndText(request.getExerciseId(), request.getTagText());
    if (isNull(tag)) {
      tagRepository.createTag(request.getExerciseId(), request.getTagText());
      return HttpResponses.from(tagRepository.getTagByExerciseAndText(request.getExerciseId(), request.getTagText()));
    }
    return HttpResponses.from(tag);
  }
}
