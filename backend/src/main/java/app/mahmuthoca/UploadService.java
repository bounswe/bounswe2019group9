package app.mahmuthoca;

import app.actor.service.UserService;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class UploadService {

  private static final String BUCKET_NAME = "kereviz-upload";

  private static final String PNG_TYPE = "image/png";

  private static final String JPEG_TYPE = "image/jpeg";

  private static final String IMAGE_FOLDER_URL = "https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/";

  private final AmazonClient amazonClient;

  private final UserService userService;

  public UploadService(AmazonClient amazonClient, UserService userService) {
    this.amazonClient = amazonClient;
    this.userService = userService;
  }

  public String uploadImage(FileUploadRequest request) {
    String extension = "";
    try {
      String base64Data = request.getBase64Data();
      byte[] bI =
          Base64.decodeBase64(base64Data.substring(base64Data.indexOf(",") + 1).getBytes());
      InputStream fis = new ByteArrayInputStream(bI);
      String type = base64Data.substring(base64Data.indexOf(":") + 1, base64Data.indexOf(";"));
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(bI.length);
      if (type.equals(PNG_TYPE) || type.equals(JPEG_TYPE)) {
        metadata.setContentType(type);
      } else {
        return null;
      }
      extension = getExtension(type);
      metadata.setCacheControl("public, max-age=31536000");
      this.amazonClient.getS3client()
                       .putObject(BUCKET_NAME, "image/exercise/".concat(request.getExerciseId().toString().concat(extension)),
                                  fis, metadata);
      this.amazonClient.getS3client()
                       .setObjectAcl(BUCKET_NAME, "image/exercise/".concat(request.getExerciseId().toString().concat(extension)),
                                     CannedAccessControlList.PublicRead);
    } catch (Exception e) {
      return null;
    }
    return IMAGE_FOLDER_URL.concat(request.getExerciseId().toString()).concat(extension);
  }

  public String uploadEssayImage(FileUploadRequest request) {
    if (isNull(request.getAuthorId()) || isNull(userService.getUserById(request.getAuthorId()).getData())) {
      return null;
    }
    String extension = "";
    try {
      String base64Data = request.getBase64Data();
      byte[] bI =
          Base64.decodeBase64(base64Data.substring(base64Data.indexOf(",") + 1).getBytes());
      InputStream fis = new ByteArrayInputStream(bI);
      String type = base64Data.substring(base64Data.indexOf(":") + 1, base64Data.indexOf(";"));
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(bI.length);
      if (type.equals(PNG_TYPE) || type.equals(JPEG_TYPE)) {
        metadata.setContentType(type);
      } else {
        return null;
      }
      extension = getExtension(type);
      metadata.setCacheControl("public, max-age=31536000");
      this.amazonClient.getS3client()
                       .putObject(BUCKET_NAME, "image/essay/".concat(request.getExerciseId().toString())
                                                             .concat("_")
                                                             .concat(request.getAuthorId().toString())
                                                             .concat(extension),
                                  fis, metadata);
      this.amazonClient.getS3client()
                       .setObjectAcl(BUCKET_NAME, "image/essay/".concat(request.getExerciseId().toString())
                                                                .concat("_")
                                                                .concat(request.getAuthorId().toString())
                                                                .concat(extension),
                                     CannedAccessControlList.PublicRead);
    } catch (Exception e) {
      return null;
    }
    return IMAGE_FOLDER_URL.concat("essay/")
                           .concat(request.getExerciseId().toString())
                           .concat("_")
                           .concat(request.getAuthorId().toString())
                           .concat(extension);
  }

  private String getExtension(String type) {
    return ".".concat(type.substring(type.indexOf("/") + 1));
  }
}
