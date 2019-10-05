package app.actor.controller;

import app.Response;
import app.actor.LoginRequest;
import app.actor.RegisterRequest;
import app.actor.entity.User;
import app.actor.repository.UserRepository;
import app.actor.service.LoginService;
import app.actor.service.RegisterService;
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

  private final LoginService loginService;

  public UserController(UserRepository userRepository, RegisterService registerService,
                        LoginService loginService) {
    this.userRepository = userRepository;
    this.registerService = registerService;
    this.loginService = loginService;
  }

  @GetMapping("/get")
  public User getUserById(@RequestParam Long id) {
    return userRepository.getUserById(id);
  }

  @PostMapping("/register")
  public Response<User> register(@RequestBody RegisterRequest registerRequest) {
    return registerService.register(registerRequest);
  }

  @PostMapping("/login")
  public Response<User> login(@RequestBody LoginRequest loginRequest) {
    return loginService.login(loginRequest);
  }
}
