package app.proseidon;

/**
 * @author ahmet.gedemenli
 */

public class ProficiencyExamRequest {

  private String language;

  public ProficiencyExamRequest(String language) {
    this.language = language;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public String toString() {
    return "ProficiencyExamRequest{" +
           "language='" + language + '\'' +
           '}';
  }
}
