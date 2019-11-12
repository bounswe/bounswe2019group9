package app.actor.service;

import app.actor.bean.ExerciseSolvedRequest;
import app.actor.entity.ExerciseSolvedInfo;
import app.common.HttpResponses;
import app.common.Response;
import app.actor.entity.User;
import app.actor.repository.UserRepository;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Response<User> getUserById(Long id) {
    User user = userRepository.getUserById(id);
    if (isNull(user)) {
      return HttpResponses.notFound("User not found.");
    }
    return HttpResponses.from(user);
  }

  public Response<ExerciseSolvedInfo> createExerciseSolvedInfo(ExerciseSolvedRequest request) {
    return null;
  }
}
