package app.mahmuthoca.controller;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.FileUploadRequest;
import app.mahmuthoca.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/files")
@RestController
public class FileController {

  private static final String FILE_NOT_UPLOADED = "File not uploaded.";

  private final UploadService uploadService;

  public FileController(UploadService uploadService) {
    this.uploadService = uploadService;
  }

  @PostMapping("/images")
  public Response<String> addImage(@RequestBody FileUploadRequest request) {
    String url = uploadService.uploadImage(request);
    if (isNull(url)) {
      return HttpResponses.badRequest(FILE_NOT_UPLOADED);
    }
    return HttpResponses.from(url);
  }

  @PostMapping("/images/essay")
  public Response<String> addEssayImage(@RequestBody FileUploadRequest request) {
    String url = uploadService.uploadEssayImage(request);
    if (isNull(url)) {
      return HttpResponses.badRequest(FILE_NOT_UPLOADED);
    }
    return HttpResponses.from(url);
  }

  @PostMapping("/sounds")
  public Response<String> addSound(@RequestBody FileUploadRequest request) {
    String url = uploadService.uploadSound(request);
    if (isNull(url)) {
      return HttpResponses.badRequest(FILE_NOT_UPLOADED);
    }
    return HttpResponses.from(url);
  }
}
