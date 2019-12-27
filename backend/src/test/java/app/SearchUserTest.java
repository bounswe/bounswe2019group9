package app;

import app.cerebro.bean.SearchUserRequest;
import app.cerebro.service.SearchUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchUserTest {

  @Autowired
  private SearchUserService searchUserService;

  /*
    Also testing the case-insensitivity.
   */
  @Test
  public void testSearchUser() throws Exception {
    SearchUserRequest req = new SearchUserRequest("xaVier", "herNaNdez", null, null);
    assertEquals(searchUserService.searchUser(req).getData().get(0).getFirstName(), "Xavier");
    assertEquals(searchUserService.searchUser(req).getData().get(0).getLastName(), "Hernandez");
  }
}
