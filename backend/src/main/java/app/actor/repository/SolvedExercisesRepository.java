package app.actor.repository;

import app.actor.entity.ExerciseSolvedInfo;
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
public interface SolvedExercisesRepository extends JpaRepository<ExerciseSolvedInfo, Long> {

  @Query(value = "SELECT * FROM solved_exercises WHERE user_id=:userId", nativeQuery = true)
  List<ExerciseSolvedInfo> getSolvedExercisesByUserId(@Param("userId") Long userId);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO solved_exercises(userd_id, exercise_id) VALUES(:userId, :exerciseId)", nativeQuery = true)
  void createExerciseSolvedInfo(@Param("userId") Long userId, @Param("exerciseId") Long exerciseId);
}
