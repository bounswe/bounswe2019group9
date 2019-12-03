package app.mahmuthoca;

import app.common.HttpResponses;
import app.common.Response;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/files")
@RestController
public class FileController {

  private static final String BUCKET_NAME = "kereviz-upload";

  @PostMapping("/images")
  public Response<String> addImage(FileUploadRequest request) {
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                                             .withRegion(Regions.EU_CENTRAL_1)
                                             .build();
    byte[] bI = org.apache.commons.codec.binary.Base64.decodeBase64(
        (request.getBase64Data().substring(request.getBase64Data().indexOf(",") + 1)).getBytes());

    InputStream fis = new ByteArrayInputStream(bI);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(bI.length);
    metadata.setContentType("image/png");
    metadata.setCacheControl("public, max-age=31536000");
    s3Client.putObject(BUCKET_NAME, request.getExerciseId().toString().concat(".png"), fis, metadata);
    s3Client.setObjectAcl(BUCKET_NAME, request.getExerciseId().toString().concat(".png"),
                          CannedAccessControlList.PublicRead);
    return HttpResponses.from("");
  }
}
