package app.mahmuthoca.repository;

import app.mahmuthoca.entity.Essay;
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
public interface EssayRepository extends JpaRepository<Essay, Long> {

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO mahmuthoca.essays(assignment_id, author_id, source, source_type) VALUES(:assignmentId, "
                 + ":authorId, :source, :sourceType)", nativeQuery = true)
  void addEssay(@Param("assignmentId") Long assignmentId, @Param("authorId") Long authorId,
                @Param("source") String source, @Param("sourceType") Integer sourceType);

  @Query(value = "SELECT * FROM mahmuthoca.essays WHERE id = :essayId", nativeQuery = true)
  Essay getEssayById(@Param("essayId") Long essayId);
}
