package app.mahmuthoca.service;

import app.actor.repository.UserRepository;
import app.actor.service.UserService;
import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.entity.Comment;
import app.mahmuthoca.repository.CommentRepository;
import app.mahmuthoca.bean.CreateCommentRequest;
import app.mahmuthoca.bean.SourceAndComment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  private static final String SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE = "Sender and receiver can't be same the user";

  private final UserService userService;

  private final CommentRepository commentRepository;

  private final UserRepository userRepository;

  public CommentService(UserService userService, CommentRepository commentRepository, UserRepository userRepository) {
    this.userService = userService;
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
  }

  public Response<List<SourceAndComment>> getCommentsByReceiverId(Long userId) {
    if (!isIdValid(userId)) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }

    List<Comment> comments = commentRepository.getCommentsByReceiverId(userId);
    ArrayList<SourceAndComment> responseBody = new ArrayList<>();
    SourceAndComment sourceAndComment;

    for (Comment comment : comments) {
      try {
        sourceAndComment = new SourceAndComment(userRepository.getUserById(comment.getSourceId()).getFirstName(),
                                                userRepository.getUserById(comment.getSourceId()).getLastName(),
                                                comment);
      } catch (Exception e) {
        sourceAndComment = new SourceAndComment("", "", comment);
      }
      responseBody.add(sourceAndComment);
    }
    return HttpResponses.from(responseBody);
  }

  public Response<CreateCommentRequest> createComment(CreateCommentRequest request) {
    if (!isIdValid(request.getSourceId()) || !isIdValid(request.getReceiverId())) {
      return HttpResponses.badRequest(USER_NOT_FOUND_MESSAGE);
    }
    if (request.getReceiverId() == request.getSourceId()) {
      return HttpResponses.badRequest(SENDER_AND_RECEIVER_CANNOT_BE_SAME_MESSAGE);
    }

    Date now = new Date();

    commentRepository.createComment(request.getSourceId(), request.getReceiverId(), request.getContent(), now);

    return HttpResponses.from(request);
  }

  private boolean isIdValid(Long userId) {
    return userService.getUserById(userId).getStatus() == HttpResponses.SUCCESSFUL;
  }
}

