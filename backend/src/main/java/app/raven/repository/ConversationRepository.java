package app.raven.repository;

import app.raven.entity.Conversation;
import java.util.Date;
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
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

  @Query(value = "SELECT * FROM raven.conversations "
                 + " WHERE (user_id_one=:userId1 AND user_id_two=:userId2)"
                 + " OR (user_id_two=:userId1 AND user_id_one=:userId2)", nativeQuery = true)
  Conversation getConversationById(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO raven.conversations(user_id_one, user_id_two, last_updated_at)"
                 + " VALUES(:userId1, :userId2, :now)", nativeQuery = true)
  void createConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2, @Param("now") Date now);

  @Modifying
  @Transactional
  @Query(value = "UPDATE raven.conversations SET last_updated_at=:now WHERE id=:id", nativeQuery = true)
  void updateConversationById(@Param("id") Long id, @Param("now") Date now);
}
