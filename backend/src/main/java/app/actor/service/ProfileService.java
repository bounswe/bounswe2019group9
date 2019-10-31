package app.actor.service;

import app.proseidon.LanguageService;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class ProfileService {

  private UserService userService;

  private GradeService gradeService;

  private LanguageService languageService;

  public ProfileService(UserService userService, GradeService gradeService, LanguageService languageService) {
    this.userService = userService;
    this.gradeService = gradeService;
    this.languageService = languageService;
  }
}
