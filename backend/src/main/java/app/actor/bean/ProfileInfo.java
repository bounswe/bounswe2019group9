package app.actor.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ahmet.gedemenli
 */

public class ProfileInfo {

  private Long userId;

  private String firstName;

  private String lastName;

  private String email;

  private List<String> languages;

  private List<Integer> grades;

  public ProfileInfo(Long userId, String firstName, String lastName, String email) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    languages = new ArrayList<>();
    grades = new ArrayList<>();
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<String> getLanguages() {
    return languages;
  }

  public void setLanguages(List<String> languages) {
    this.languages = languages;
  }

  public List<Integer> getGrades() {
    return grades;
  }

  public void setGrades(List<Integer> grades) {
    this.grades = grades;
  }

  @Override
  public String toString() {
    return "ProfileInfo{" +
           "userId=" + userId +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", email='" + email + '\'' +
           ", languages=" + languages +
           ", grades=" + grades +
           '}';
  }
}
