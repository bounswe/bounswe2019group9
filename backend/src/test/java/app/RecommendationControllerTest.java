package app;

import app.actor.bean.ProfileInfo;
import app.cerebro.bean.RecommendationRequest;
import app.cerebro.controller.RecommendationController;
import app.common.Response;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author ahmet.gedemenli
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationControllerTest {

  @Autowired
  private RecommendationController recommendationController;

  @Test
  public void testSorted() throws Exception {
    RecommendationRequest request = new RecommendationRequest(1, 2);
    Response<List<ProfileInfo>> response = recommendationController.getRecommendations(request);
    ProfileInfo first = response.getData().get(0);
    ProfileInfo last = response.getData().get(response.getData().size() - 1);
    assertTrue(first.getRating() >= last.getRating());
  }
}
