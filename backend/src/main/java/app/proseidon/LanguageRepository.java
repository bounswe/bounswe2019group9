package app.proseidon;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ahmet.gedemenli
 */

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

  @Query(value = "SELECT name FROM proseidon.languages", nativeQuery = true)
  List<String> getAllLanguages();

  @Query(value = "SELECT id FROM proseidon.languages WHERE name=:language", nativeQuery = true)
  Integer getLanguageIdByName(@Param("language") String name);
}
