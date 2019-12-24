package app.actor.repository;

import app.actor.entity.User;
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
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT * FROM users where id=:id", nativeQuery = true)
  User getUserById(@Param("id") Long id);

  @Query(value = "SELECT * FROM users WHERE email=:email", nativeQuery = true)
  User getUserByEmail(@Param("email") String email);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO users(email, password, first_name, last_name) values(:email, :password, :firstName, :lastName)", nativeQuery = true)
  void addUser(@Param("email") String email, @Param("password") String password, @Param("firstName") String firstName,
           @Param("lastName") String lastName);

  @Query(value = "SELECT * FROM users WHERE LOWER(first_name) LIKE %"+":firstName"+"% AND LOWER(last_name) LIKE %"+":lastName"+"%", nativeQuery = true)
  List<User> getUsersByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
