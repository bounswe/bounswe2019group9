package app.actor.repository;

import app.Response;
import app.actor.entity.Grade;
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
public interface GradeRepository extends JpaRepository<Grade, Long> {

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO grades(user_id, language_id, grade) values(:userId, :languageId, :grade)", nativeQuery = true)
  void addGrade(@Param("userId") Long userId, @Param("languageId") Integer languageId, @Param("grade") Integer grade);

  @Query(value = "SELECT * FROM grades WHERE user_id=:userId AND language_id=:languageId", nativeQuery = true)
  Grade getGradeByUserIdAndLanguageId(@Param("userId") Long userId, @Param("languageId") Integer languageId);
}
