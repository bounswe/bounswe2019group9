package app.mahmuthoca;

public class FileUploadRequest {

  private Long exerciseId;

  private String base64Data;

  public FileUploadRequest(Long exerciseId, String base64Data) {
    this.exerciseId = exerciseId;
    this.base64Data = base64Data;
  }

  public Long getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(Long exerciseId) {
    this.exerciseId = exerciseId;
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
           ", base64Data='" + base64Data + '\'' +
           '}';
  }
}
