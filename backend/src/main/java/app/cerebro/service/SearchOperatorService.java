package app.cerebro.service;

import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class SearchOperatorService {

  private final SearchUserService searchUserService;

  private final SearchExerciseService searchExerciseService;

  public SearchOperatorService(SearchUserService searchUserService,
                               SearchExerciseService searchExerciseService) {
    this.searchUserService = searchUserService;
    this.searchExerciseService = searchExerciseService;
  }

  public SearchUserService getSearchUserService() {
    return this.searchUserService;
  }

  public SearchExerciseService getSearchExerciseService() {
    return this.searchExerciseService;
  }
}
