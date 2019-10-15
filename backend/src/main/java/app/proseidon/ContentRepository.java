package app.proseidon;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author ahmet.gedemenli
 */

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

  @Query(value = "SELECT * FROM proseidon.exercises", nativeQuery = true)
  List<Exercise> getAllExercises();
}
