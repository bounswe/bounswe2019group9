package app.mahmuthoca.repository;

import app.mahmuthoca.entity.Request;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ahmet.gedemenli
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO mahmuthoca.requests(source_id, receiver_id, essay_id) VALUES(:sourceId, :receiverId, "
                 + ":essayId)", nativeQuery = true)
  void addRequest(@Param("sourceId") Long sourceId, @Param("receiverId") Long receiverId,
                  @Param("essayId") Long essayId);

  @Query(value = "SELECT * FROM mahmuthoca.requests WHERE receiver_id = :receiverId", nativeQuery = true)
  List<Request> getRequestsByReceiverId(@Param("receiverId") Long receiverId);
}
