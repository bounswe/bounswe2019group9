package app.proseidon.controller;

import app.common.Response;
import app.proseidon.bean.TagCreateRequest;
import app.proseidon.entity.Tag;
import app.proseidon.service.TagService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/tags")
@RestController
public class TagController {

  private final TagService tagService;

  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @PostMapping
  public Response<Tag> addTag(@RequestBody TagCreateRequest request) {
    return tagService.createTag(request);
  }
}
