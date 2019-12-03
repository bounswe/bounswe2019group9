package app.mahmuthoca;

import app.common.HttpResponses;
import app.common.Response;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
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
    //BasicAWSCredentials awsCreds =
        //new BasicAWSCredentials("AKIAWLBEIOKST5RJG56E", "GRvfNzUuELyFM6OeX2QtjyduDLVG6a1F6qXSuE4H");
    //AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
    //                                         .withRegion(Regions.EU_CENTRAL_1)
    //                                         .withCredentials(new SystemPropertiesCredentialsProvider())
                                             //.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                                             //.build();
    String base64Data = request.getBase64Data();
    System.out.println(base64Data.length());
    String s = base64Data.substring(0, 50);
    int beginIndex = s.indexOf(",") + 1;
    String substring = base64Data.substring(beginIndex);
    byte[] bI = org.apache.commons.codec.binary.Base64.decodeBase64(substring.getBytes());
    System.getProperties().list(System.out);
    InputStream fis = new ByteArrayInputStream(bI);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(bI.length);
    metadata.setContentType("images/png");
    metadata.setCacheControl("public, max-age=31536000");
    this.amazonClient.s3client.putObject(BUCKET_NAME, "image/".concat(request.getExerciseId().toString().concat(".png")), fis, metadata);
    //s3Client.putObject(BUCKET_NAME, "/images/".concat(request.getExerciseId().toString().concat(".png")), fis, metadata);
    //s3Client.setObjectAcl(BUCKET_NAME, "/images/".concat(request.getExerciseId().toString().concat(".png")),
    this.amazonClient.s3client.setObjectAcl(BUCKET_NAME, "image/".concat(request.getExerciseId().toString().concat(".png")),
                          CannedAccessControlList.PublicRead);
    return HttpResponses.from("");
  }
}
