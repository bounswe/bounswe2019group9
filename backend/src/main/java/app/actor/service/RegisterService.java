package app.actor.service;

import app.actor.RegisterRequest;
import app.actor.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class RegisterService {

  private final UserRepository userRepository;

  public RegisterService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void register(RegisterRequest registerRequest) {
    userRepository.addUser(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getFirstName(),
                           registerRequest.getLastName());
  }
}
