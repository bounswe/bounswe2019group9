package app.actor.service;

import app.HttpResponses;
import app.Response;
import app.actor.LoginRequest;
import app.actor.UserNotFoundException;
import app.actor.entity.User;
import app.actor.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class LoginService {

  public static final String USER_NOT_FOUND_MESSAGE = "No user with email: ";

  private static final String WRONG_CREDENTIALS = "Wrong credentials";

  private final UserRepository userRepository;

  public LoginService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Response<User> login(LoginRequest loginRequest) {
    User user;
    try {
      user = getUserByEmail(loginRequest.getEmail());
    } catch (UserNotFoundException ex) {
      return HttpResponses.badRequest(WRONG_CREDENTIALS);
    }
    if (!EncryptionService.encrypt(loginRequest.getPassword()).equals(user.getPassword())) {
      return HttpResponses.badRequest(WRONG_CREDENTIALS);
    }
    user.setPassword(loginRequest.getPassword());
    return HttpResponses.from(user);
  }

  private User getUserByEmail(String email) throws UserNotFoundException {
    User user = userRepository.getUserByEmail(email);
    if (user == null) {
      throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + email);
    }
    return user;
  }
}
