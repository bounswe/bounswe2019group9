package actor;

import actor.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping
  public User getUserById(Long id) {
    return userRepository.getUserById(id);
  }

  @GetMapping("/get")
  public User getUser1(@RequestParam Long id) {
    System.out.println("geldi");
    return userRepository.getUserById(id);
  }
}
