package app.mahmuthoca;

import app.common.HttpResponses;
import app.common.Response;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/files")
@RestController
public class FileController {

  private static final String BUCKET_NAME = "kereviz-upload";

  private AmazonClient amazonClient;

  public FileController(AmazonClient amazonClient) {
    this.amazonClient = amazonClient;
  }

  @PostMapping("/images")
  public Response<String> addImage(@RequestBody FileUploadRequest request) {
    byte[] bI = Base64.decodeBase64(request.getBase64Data().substring(request.getBase64Data().indexOf(",") + 1).getBytes());
    InputStream fis = new ByteArrayInputStream(bI);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(bI.length);
    metadata.setContentType("images/png");
    metadata.setCacheControl("public, max-age=31536000");
    this.amazonClient.getS3client().putObject(BUCKET_NAME, "image/".concat(request.getExerciseId().toString().concat(".png")), fis, metadata);
    this.amazonClient.getS3client().setObjectAcl(BUCKET_NAME, "image/".concat(request.getExerciseId().toString().concat(".png")),
                          CannedAccessControlList.PublicRead);
    return HttpResponses.from("");
  }
}
