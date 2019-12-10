package app.actor.service;

import app.actor.bean.ProfileInfo;
import app.actor.UserNotFoundException;
import app.actor.entity.Grade;
import app.actor.entity.User;
import app.mahmuthoca.RatingService;
import app.proseidon.service.ContentService;
import app.proseidon.service.LanguageService;
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

  private ContentService contentService;

  private RatingService ratingService;

  public ProfileService(UserService userService, GradeService gradeService, LanguageService languageService,
                        ContentService contentService, RatingService ratingService) {
    this.userService = userService;
    this.gradeService = gradeService;
    this.languageService = languageService;
    this.contentService = contentService;
    this.ratingService = ratingService;
  }

  public ProfileInfo getProfileInfoByUserId(Long userId) throws UserNotFoundException {
    User user = userService.getUserById(userId).getData();
    if (isNull(user)) {
      throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + userId);
    }
    ProfileInfo profileInfo = new ProfileInfo(userId, user.getFirstName(), user.getLastName(), user.getEmail(),
                                              ratingService.getAverageRatingByUserId(userId));
    List<String> allLanguages = languageService.getAllLanguages().getData();
    for (String lang : allLanguages) {
      Integer langId = languageService.getLanguageIdByName(lang);
      Grade grade = gradeService.getGradeByUserIdAndLanguageId(userId, langId).getData();
      if (isNull(grade)) {
        continue;
      }
      profileInfo.getLanguages().add(lang);
      profileInfo.getGrades().add(grade.getGrade());
      Integer numberOfSolvedExercises = userService.getNumberOfSolvedExercisesById(userId, langId, grade.getGrade());
      Integer numberOfExercisesByGrade = contentService.getNumberOfExercisesByGrade(langId, grade.getGrade());
      if (numberOfExercisesByGrade == 0) {
        profileInfo.getProgressLevels().add(0);
      } else {
        profileInfo.getProgressLevels()
                   .add(100 * numberOfSolvedExercises
                        / numberOfExercisesByGrade);
      }
    }
    return profileInfo;
  }
}
