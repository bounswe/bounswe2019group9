package app.actor.service;

import app.common.HttpResponses;
import app.common.Response;
import app.actor.bean.RegisterRequest;
import app.actor.entity.User;
import app.actor.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class RegisterService {

  private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

  private static final String EMAIL_IN_USE = "This e-mail has already been registered";

  private static final String INVALID_EMAIL = "Invalid email";

  private final UserRepository userRepository;

  public RegisterService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Response<User> register(RegisterRequest registerRequest) {
    if (userRepository.getUserByEmail(registerRequest.getEmail()) != null) {
      return HttpResponses.badRequest(EMAIL_IN_USE);
    }
    if (!registerRequest.getEmail().matches(EMAIL_REGEX)) {
      return HttpResponses.badRequest(INVALID_EMAIL);
    }
    userRepository.addUser(registerRequest.getEmail(), EncryptionService.encrypt(registerRequest.getPassword()),
                           registerRequest.getFirstName(), registerRequest.getLastName());
    User user = userRepository.getUserByEmail(registerRequest.getEmail());
    user.setPassword(registerRequest.getPassword());
    return HttpResponses.from(user);
  }
}
