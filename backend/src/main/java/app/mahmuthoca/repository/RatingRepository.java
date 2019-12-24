package app.mahmuthoca.repository;

import app.mahmuthoca.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author ahmet.gedemenli
 */

public interface RatingRepository extends JpaRepository<Rating, Integer> {

  @Query(value = "SELECT * FROM mahmuthoca.ratings WHERE receiver_id = :receiverId", nativeQuery = true)
  List<Rating> getRatingsByReceiverId(@Param("receiverId") Long receiverId);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO mahmuthoca.ratings(sender_id, receiver_id, rating) "
          + "VALUES(:senderId, :receiverId, :rating)", nativeQuery = true)
  void addRating(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId, @Param("rating") Integer rating);

  @Query(value = "SELECT * FROM mahmuthoca.ratings "
          + " WHERE (sender_id=:senderId AND receiver_id=:receiverId)", nativeQuery = true)
  Rating getRatingBetween(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

  @Modifying
  @Transactional
  @Query(value = "UPDATE mahmuthoca.ratings SET rating=:rating"
          + " WHERE (sender_id=:senderId AND receiver_id=:receiverId)", nativeQuery = true)
  void updateRating(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId,
                    @Param("rating") Integer rating);

}

