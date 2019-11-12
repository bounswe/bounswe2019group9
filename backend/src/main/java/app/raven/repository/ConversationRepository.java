package app.raven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ahmet.gedemenli
 */

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
