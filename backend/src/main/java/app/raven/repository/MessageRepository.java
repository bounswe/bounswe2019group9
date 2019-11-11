package app.raven.repository;

import app.raven.entity.Message;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO raven.messages(source_id, receiver_id, content, created_at)" +
                 " VALUES(:sourceId, :receiverId, :content, :now)", nativeQuery = true)
  void createMessage(@Param("sourceId") Long sourceId, @Param("receiverId") Long receiverId,
                     @Param("content") String content, @Param("now") Date now);

  @Query(value = "SELECT * FROM raven.messages WHERE source_id=:userId OR receiver_id=:userId", nativeQuery = true)
  List<Message> getMessagesByUserId(@Param("userId") Long userId);

  @Query(value = "SELECT * FROM raven.messages WHERE (source_id=:userId1 OR source_id=:userId2) AND (receiver_id=:userId1 OR receiver_id=:userId2)", nativeQuery = true)
  List<Message> getChatContent(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
}
