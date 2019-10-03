package app.actor;

import app.actor.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;

  private final RegisterService registerService;

  public UserController(UserRepository userRepository, RegisterService registerService) {
    this.userRepository = userRepository;
    this.registerService = registerService;
  }

  @GetMapping("/get")
  public User getUserById(@RequestParam Long id) {
    return userRepository.getUserById(id);
  }

  @PostMapping("/register")
  public RegisterRequest register(@RequestBody RegisterRequest registerRequest) {
    System.out.println(registerRequest);
    registerService.register(registerRequest);
    return registerRequest;
  }
}
