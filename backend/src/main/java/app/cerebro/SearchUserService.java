package app.cerebro;

/**
 * @author ahmet.gedemenli
 */

import app.actor.bean.ProfileInfo;
import app.actor.entity.Grade;
import app.actor.entity.User;
import app.actor.repository.GradeRepository;
import app.actor.repository.UserRepository;
import app.actor.service.ProfileService;
import app.common.HttpResponses;
import app.common.Response;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class SearchUserService {

  private static final String EMPTY = "";

  private final ProfileService profileService;

  private final UserRepository userRepository;

  private final GradeRepository gradeRepository;

  public SearchUserService(ProfileService profileService, UserRepository userRepository,
                           GradeRepository gradeRepository) {
    this.profileService = profileService;
    this.userRepository = userRepository;
    this.gradeRepository = gradeRepository;
  }

  public Response<List<ProfileInfo>> searchUser(SearchUserRequest request) {
    List<User> users;
    List<ProfileInfo> profiles = new ArrayList<>();
    request = getFirstNameNotNull(request);
    request = getLastNameNotNull(request);
    users = userRepository.getUsersByFirstNameAndLastName(request.getFirstName(), request.getLastName());
    for (User user : users) {
      if (isNull(request.getLanguageId())) {
        profiles.add(profileService.getProfileInfoByUserId(user.getId()));
      } else {
        Grade grade = gradeRepository.getGradeByUserIdAndLanguageId(user.getId(), request.getLanguageId());
        if (nonNull(grade) && (isNull(request.getGrade()) || grade.getGrade() >= request.getGrade())) {
          profiles.add(profileService.getProfileInfoByUserId(user.getId()));
        }
      }
    }
    return HttpResponses.from(profiles);
  }

  private SearchUserRequest getFirstNameNotNull(SearchUserRequest request) {
    if (isNull(request.getFirstName())) {
      request.setFirstName(EMPTY);
    }
    return request;
  }

  private SearchUserRequest getLastNameNotNull(SearchUserRequest request) {
    if (isNull(request.getLastName())) {
      request.setLastName(EMPTY);
    }
    return request;
  }
}
