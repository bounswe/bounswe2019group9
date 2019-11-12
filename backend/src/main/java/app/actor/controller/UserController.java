package app.actor.controller;

import app.actor.bean.ExerciseSolvedRequest;
import app.actor.entity.ExerciseSolvedInfo;
import app.common.HttpResponses;
import app.common.Response;
import app.actor.bean.LoginRequest;
import app.actor.bean.ProfileInfo;
import app.actor.bean.RegisterRequest;
import app.actor.UserNotFoundException;
import app.actor.entity.User;
import app.actor.service.LoginService;
import app.actor.service.ProfileService;
import app.actor.service.RegisterService;
import app.actor.service.UserService;
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

  private UserService userService;

  private RegisterService registerService;

  private LoginService loginService;

  private ProfileService profileService;

  public UserController(UserService userService,
                        RegisterService registerService,
                        LoginService loginService,
                        ProfileService profileService) {
    this.userService = userService;
    this.registerService = registerService;
    this.loginService = loginService;
    this.profileService = profileService;
  }

  @GetMapping("/get")
  public Response<User> getUserById(@RequestParam Long id) {
    return userService.getUserById(id);
  }

  @GetMapping("/profile")
  public Response<ProfileInfo> getProfileInfoByUserId(@RequestParam Long id) {
    try {
      return HttpResponses.from(profileService.getProfileInfoByUserId(id));
    } catch (UserNotFoundException ex) {
      return HttpResponses.notFound(ex.getMessage());
    }
  }

  @PostMapping("/register")
  public Response<User> register(@RequestBody RegisterRequest registerRequest) {
    return registerService.register(registerRequest);
  }

  @PostMapping("/login")
  public Response<User> login(@RequestBody LoginRequest loginRequest) {
    return loginService.login(loginRequest);
  }

  @PostMapping("/solved")
  public Response<ExerciseSolvedInfo> createExerciseSolvedInfo(@RequestBody ExerciseSolvedRequest request) {
    return userService.createExerciseSolvedInfo(request);
  }
}
