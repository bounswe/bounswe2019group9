package app.cerebro.bean;

/**
 * @author ahmet.gedemenli
 */

public class SearchUserRequest {

  private String firstName;

  private String lastName;

  private Integer languageId;

  private Integer grade;

  public SearchUserRequest(String firstName, String lastName, Integer languageId, Integer grade) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.languageId = languageId;
    this.grade = grade;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Integer languageId) {
    this.languageId = languageId;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "SearchUserRequest{" +
           "firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", languageId=" + languageId +
           ", grade=" + grade +
           '}';
  }
}
