package app.mahmuthoca.service;

import app.mahmuthoca.bean.CreateEssayRequest;
import app.mahmuthoca.entity.Essay;
import app.mahmuthoca.repository.EssayRepository;
import org.springframework.stereotype.Service;

/**
 * @author ahmet.gedemenli
 */

@Service
public class EssayService {

  private final EssayRepository essayRepository;

  public EssayService(EssayRepository essayRepository) {
    this.essayRepository = essayRepository;
  }

  public void addEssay(CreateEssayRequest request) {
    essayRepository.addEssay(request.getAssignmentId(), request.getAuthorId(), request.getSource(),
                             request.getSourceType());
  }

  public Essay getEssayById(Long essayId) {
    return essayRepository.getEssayById(essayId);
  }
}
