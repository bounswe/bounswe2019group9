package app.mahmuthoca.service;

import app.mahmuthoca.bean.CreateEssayRequest;
import app.mahmuthoca.entity.Essay;
import app.mahmuthoca.repository.EssayRepository;
import java.util.List;
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
    List<Essay> essays = getEssaysByUserId(request.getAuthorId());
    for (Essay essay : essays) {
      if (essay.getAuthorId() == request.getAuthorId()) {
        essayRepository.deleteEssayById(essay.getId());
      }
    }
    essayRepository.addEssay(request.getAssignmentId(), request.getAuthorId(), request.getSource(),
                             request.getSourceType());
  }

  public Essay getEssayById(Long essayId) {
    return essayRepository.getEssayById(essayId);
  }

  public List<Essay> getEssaysByUserId(Long id) {
    return essayRepository.getEssaysByUserId(id);
  }
}
