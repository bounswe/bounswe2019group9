package app.mahmuthoca.service;

import app.mahmuthoca.bean.CreateReviewRequestRequest;
import app.mahmuthoca.entity.Request;
import app.mahmuthoca.repository.RequestRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class RequestService {

  private final RequestRepository requestRepository;

  public RequestService(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  public void addRequest(CreateReviewRequestRequest request) {
    requestRepository.addRequest(request.getSourceId(), request.getReceiverId(), request.getEssayId());
  }

  public List<Request> getRequestsByReceiverId(Long id) {
    return requestRepository.getRequestsByReceiverId(id);
  }
}
