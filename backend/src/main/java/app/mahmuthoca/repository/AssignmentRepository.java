package app.mahmuthoca.repository;

import app.mahmuthoca.entity.Assignment;
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
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO mahmuthoca.assignments(question) VALUES(:question)", nativeQuery = true)
  void addAssignment(@Param("question") String question);

  @Query(value = "SELECT * FROM mahmuthoca.assignments WHERE id = :id", nativeQuery = true)
  Assignment getAssignmentById(@Param("id") Long id);
}
