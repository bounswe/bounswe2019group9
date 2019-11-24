package app.raven.repository;

import app.raven.entity.Invitation;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author arda.budak
 */

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO invitations(source_id, receiver_id, created_at)" +
            " VALUES(:sourceId, :receiverId, :now)", nativeQuery = true)
    void createInvitation(@Param("sourceId") Long sourceId, @Param("receiverId") Long receiverId, @Param("now") Date now);


    @Query(value = "SELECT * FROM invitations WHERE receiver_id=:userId", nativeQuery = true)
    List<Invitation> getInvitationsByReceiverId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM invitations WHERE source_id=:userIdA AND receiver_id=:userIdB", nativeQuery = true)
    Invitation getInvitationFromAtoB(@Param("userIdA") Long userIdA, @Param("userIdB") Long userIdB);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM invitations WHERE source_id=:userIdA AND receiver_id=:userIdB", nativeQuery = true)
    void deleteInvitation(@Param("userIdA") Long userIdA, @Param("userIdB") Long userIdB);
}