package app.actor.service;

import app.actor.bean.ExerciseSolvedRequest;
import app.actor.entity.ExerciseSolvedInfo;
import app.actor.repository.SolvedExercisesRepository;
import app.common.HttpResponses;
import app.common.Response;
import app.actor.entity.User;
import app.actor.repository.UserRepository;
import app.proseidon.repository.ContentRepository;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class UserService {

  private static final String USER_NOT_FOUND = "User not found.";

  private static final String USER_SOLVED_THIS_EXERCISE_ALREADY = "User solved this exercise already.";

  private static final String EXERCISE_NOT_FOUND = "Exercise not found.";

  private UserRepository userRepository;

  private SolvedExercisesRepository solvedExercisesRepository;

  private ContentRepository contentRepository;

  public UserService(UserRepository userRepository,
                     SolvedExercisesRepository solvedExercisesRepository,
                     ContentRepository contentRepository) {
    this.userRepository = userRepository;
    this.solvedExercisesRepository = solvedExercisesRepository;
    this.contentRepository = contentRepository;
  }

  public Response<User> getUserById(Long id) {
    User user = userRepository.getUserById(id);
    if (isNull(user)) {
      return HttpResponses.notFound(USER_NOT_FOUND);
    }
    return HttpResponses.from(user);
  }

  public Response<ExerciseSolvedInfo> createExerciseSolvedInfo(ExerciseSolvedRequest request) {
    if (solvedExercisesRepository.getSolvedExercisesByUserId(request.getUserId()).contains(request.getExerciseId())) {
      return HttpResponses.badRequest(USER_SOLVED_THIS_EXERCISE_ALREADY);
    } else if (isNull(userRepository.getUserById(request.getUserId()))) {
      return HttpResponses.badRequest(USER_NOT_FOUND);
    } else if (isNull(contentRepository.getExerciseById(request.getExerciseId()))) {
      return HttpResponses.badRequest(EXERCISE_NOT_FOUND);
    }
    solvedExercisesRepository.createExerciseSolvedInfo(request.getUserId(), request.getExerciseId());
    return HttpResponses.successful();
  }
}
