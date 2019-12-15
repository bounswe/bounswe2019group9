package app.mahmuthoca.bean;

import javax.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

/**
 * @author ahmet.gedemenli
 */

public class FileUploadRequest {

  @NotNull
  private Long exerciseId;

  @Nullable
  private Long authorId;

  @NotNull
  private String base64Data;

  public FileUploadRequest(Long exerciseId, Long authorId, String base64Data) {
    this.exerciseId = exerciseId;
    this.authorId = authorId;
    this.base64Data = base64Data;
  }

  public Long getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(Long exerciseId) {
    this.exerciseId = exerciseId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getBase64Data() {
    return base64Data;
  }

  public void setBase64Data(String base64Data) {
    this.base64Data = base64Data;
  }

  @Override
  public String toString() {
    return "FileUploadRequest{" +
           "exerciseId=" + exerciseId +
           ", authorId=" + authorId +
           ", base64Data='" + base64Data + '\'' +
           '}';
  }
}
