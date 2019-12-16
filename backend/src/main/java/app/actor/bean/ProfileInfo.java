package app.actor.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ahmet.gedemenli
 */

public class ProfileInfo implements Comparable<ProfileInfo> {

  private Long userId;

  private String firstName;

  private String lastName;

  private String email;

  private Double rating;

  private List<String> languages;

  private List<Integer> grades;

  private List<Integer> progressLevels;

  public ProfileInfo(Long userId, String firstName, String lastName, String email, Double rating) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.rating = rating;
    languages = new ArrayList<>();
    grades = new ArrayList<>();
    progressLevels = new ArrayList<>();
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

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
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

  public List<Integer> getProgressLevels() {
    return progressLevels;
  }

  public void setProgressLevels(List<Integer> progressLevels) {
    this.progressLevels = progressLevels;
  }

  @Override
  public int compareTo(ProfileInfo profileInfo) {
    if (getRating() == null || profileInfo.getRating() == null) {
      return 0;
    }
    return getRating().compareTo(profileInfo.getRating());
  }

  @Override
  public String toString() {
    return "ProfileInfo{" +
           "userId=" + userId +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", email='" + email + '\'' +
           ", rating=" + rating +
           ", languages=" + languages +
           ", grades=" + grades +
           ", progressLevels=" + progressLevels +
           '}';
  }
}
