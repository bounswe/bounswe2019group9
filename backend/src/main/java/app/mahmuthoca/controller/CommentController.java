package app.mahmuthoca.controller;

import app.common.Response;
import app.mahmuthoca.bean.CreateCommentRequest;
import app.mahmuthoca.bean.SourceAndComment;
import app.mahmuthoca.service.CommentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/getCommentsByReceiverId")
  public Response<List<SourceAndComment>> getCommentsByReceiverId(@RequestParam("userId") Long userId) {
    return commentService.getCommentsByReceiverId(userId);
  }

  @PostMapping
  public Response<CreateCommentRequest> createComment(@Validated @RequestBody CreateCommentRequest request) {
    return commentService.createComment(request);
  }
}
