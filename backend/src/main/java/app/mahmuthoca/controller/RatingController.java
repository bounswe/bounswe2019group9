package app.mahmuthoca.controller;

import app.common.Response;
import app.mahmuthoca.bean.CreateRatingRequest;
import app.mahmuthoca.service.RatingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahmet.gedemenli
 */

@RequestMapping("/ratings")
@RestController
public class RatingController {

  private final RatingService ratingService;

  public RatingController(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @PostMapping
  public Response<CreateRatingRequest> addRating(@RequestBody CreateRatingRequest request) {
    return ratingService.addRating(request);
  }
}
