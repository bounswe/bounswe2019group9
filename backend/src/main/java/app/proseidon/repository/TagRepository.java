package app.proseidon.repository;

import app.proseidon.entity.Tag;
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
public interface TagRepository extends JpaRepository<Tag, Integer> {

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO proseidon.tags(exercise_id, tag_text) VALUES(:exerciseId, :tagText)", nativeQuery = true)
  void createTag(@Param("exerciseId") Long exerciseId, @Param("tagText") String tagText);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM proseidon.tags WHERE exercise_id=:exerciseId", nativeQuery = true)
  void deleteTagsByExerciseId(@Param("exerciseId") Long exerciseId);

  @Query(value = "SELECT * FROM proseidon.tags WHERE exercise_id=:exerciseId", nativeQuery = true)
  List<Tag> getTagsByExerciseId(@Param("exerciseId") Long exerciseId);

  @Query(value = "SELECT * FROM proseidon.tags WHERE exercise_id=:exerciseId AND tag_text=:tagText", nativeQuery = true)
  Tag getTagByExerciseAndText(@Param("exerciseId") Long exerciseId, @Param("tagText") String tagText);
}
