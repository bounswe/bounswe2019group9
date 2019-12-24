package app;

import app.actor.bean.LoginRequest;
import app.actor.entity.User;
import app.actor.service.LoginService;
import app.common.HttpResponses;
import app.common.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * @author ahmet.gedemenli
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {

  @Autowired
  private LoginService loginService;

  @Test
  public void trueNegativeLoginTest() throws Exception {
    LoginRequest loginRequest = new LoginRequest("randomemail@email.com", "wrongpassword");
    Response<User> response = loginService.login(loginRequest);
    assertSame(response.getStatus(), HttpResponses.BAD_REQUEST);
    assertEquals(response.getExplanation(), "Wrong credentials");
  }
}
