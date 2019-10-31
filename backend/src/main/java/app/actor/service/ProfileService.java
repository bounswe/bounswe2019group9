package app.actor.service;

import app.actor.ProfileInfo;
import app.actor.UserNotFoundException;
import app.actor.entity.Grade;
import app.actor.entity.User;
import app.proseidon.LanguageService;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class ProfileService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found with id: ";

  private UserService userService;

  private GradeService gradeService;

  private LanguageService languageService;

  public ProfileService(UserService userService, GradeService gradeService, LanguageService languageService) {
    this.userService = userService;
    this.gradeService = gradeService;
    this.languageService = languageService;
  }

  public ProfileInfo getProfileInfoByUserId(Long userId) throws UserNotFoundException {
    User user = userService.getUserById(userId).getData();
    if (isNull(user)) {
      throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + userId);
    }
    ProfileInfo profileInfo = new ProfileInfo(user.getFirstName(), user.getLastName(), user.getEmail());
    List<String> allLanguages = languageService.getAllLanguages().getData();
    for (String lang : allLanguages) {
      Integer langId = languageService.getLanguageIdByName(lang);
      Grade grade = gradeService.getGradeByUserIdAndLanguageId(userId, langId).getData();
      if (isNull(grade)) {
        continue;
      }
      profileInfo.getLanguages().add(lang);
      profileInfo.getGrades().add(grade.getGrade());
    }
    return profileInfo;
  }
}
