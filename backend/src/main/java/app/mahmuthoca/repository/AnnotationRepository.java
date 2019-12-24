package app.mahmuthoca.repository;

import app.mahmuthoca.entity.AnnotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AnnotationRepository extends JpaRepository<AnnotationEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO annotations(annotation)" +
            " VALUES(:annotation)", nativeQuery = true)
    void createAnnotation(@Param("annotation") String annotation);

    @Query(value = "SELECT * FROM annotations WHERE annotation LIKE %" + ":targetId" + "%", nativeQuery = true)
    List<AnnotationEntity> getAnnotationsByTargetId(@Param("targetId") String targetId);

    @Query(value = "SELECT * FROM annotations where id=:id", nativeQuery = true)
    AnnotationEntity getById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM annotations WHERE id=:id", nativeQuery = true)
    void delete(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE annotations SET annotation=:annotation WHERE id=:id", nativeQuery = true)
    void update(@Param("id") Long id, @Param("annotation") String annotation);

}