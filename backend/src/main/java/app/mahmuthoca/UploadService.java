package app.mahmuthoca;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class UploadService {

  private static final String BUCKET_NAME = "kereviz-upload";

  private static final String IMAGE_FOLDER_URL = "https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/";

  private final AmazonClient amazonClient;

  public UploadService(AmazonClient amazonClient) {
    this.amazonClient = amazonClient;
  }

  public String uploadImage(FileUploadRequest request) {
    try {
      byte[] bI =
          Base64.decodeBase64(request.getBase64Data().substring(request.getBase64Data().indexOf(",") + 1).getBytes());
      InputStream fis = new ByteArrayInputStream(bI);
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(bI.length);
      metadata.setContentType("images/png");
      metadata.setCacheControl("public, max-age=31536000");
      this.amazonClient.getS3client()
                       .putObject(BUCKET_NAME, "image/".concat(request.getExerciseId().toString().concat(".png")), fis,
                                  metadata);
      this.amazonClient.getS3client()
                       .setObjectAcl(BUCKET_NAME, "image/".concat(request.getExerciseId().toString().concat(".png")),
                                     CannedAccessControlList.PublicRead);
    } catch (Exception e) {
      return null;
    }
    return IMAGE_FOLDER_URL.concat(request.getExerciseId().toString()).concat(".png");
  }
}
