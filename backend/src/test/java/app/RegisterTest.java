package app;

import app.actor.bean.RegisterRequest;
import app.actor.entity.User;
import app.actor.service.RegisterService;
import app.common.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Objects.isNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterTest {

  @Autowired
  private RegisterService registerService;

  @Test
  public void registerTest() throws Exception {
    RegisterRequest registerRequest = new RegisterRequest("test@test111.com1", "123456", "ali", "veli");
    Response<User> response = registerService.register(registerRequest);
    assertTrue(isNull(response.getData())); // Because user with that email already exists
    assertTrue(response.getExplanation().equals("This e-mail has already been registered"));
  }
}
