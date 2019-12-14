package app.mahmuthoca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO mahmuthoca.comments(source_id, receiver_id, content, created_at)" +
                 " VALUES(:sourceId, :receiverId, :content, :now)", nativeQuery = true)
  void createComment(@Param("sourceId") Long sourceId, @Param("receiverId") Long receiverId,
                     @Param("content") String content, @Param("now") Date now);

  @Query(value = "SELECT * FROM mahmuthoca.comments WHERE receiver_id=:userId", nativeQuery = true)
  List<Comment> getCommentsByReceiverId(@Param("userId") Long userId);
}
