package app.mahmuthoca.controller;

import app.common.Response;
import app.mahmuthoca.bean.CreateRatingRequest;
import app.mahmuthoca.bean.UpdateRatingRequest;
import app.mahmuthoca.service.RatingService;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("/update")
  public Response<Integer> updateRating(@RequestBody UpdateRatingRequest request) {
    return ratingService.updateRating(request);
  }

  @GetMapping("/fromAtoB")
  public Response<Integer> getRatingBetween(@RequestParam("senderId") Long senderId,
                                            @RequestParam("receiverId") Long receiverId) {
    return ratingService.getRatingBetween(senderId, receiverId);
  }


}
