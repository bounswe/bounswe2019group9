package app.cerebro;

import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class SearchOperatorService {

  private final SearchUserService searchUserService;

  public SearchOperatorService(SearchUserService searchUserService) {
    this.searchUserService = searchUserService;
  }

  public SearchUserService getSearchUserService() {
    return this.searchUserService;
  }
}
