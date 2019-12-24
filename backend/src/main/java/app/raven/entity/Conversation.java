package app.raven.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author ahmet.gedemenli
 */

@Entity
@Table(name = "conversations")
public class Conversation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "user_id_one")
  @NotNull
  private Long userIdOne;

  @Column(name = "user_id_two")
  @NotNull
  private Long userIdTwo;

  @Column(name = "last_updated_at")
  @NotNull
  private Date lastUpdatedAt;

  public Conversation() {
  }

  public Conversation(@NotNull Long userIdOne, @NotNull Long userIdTwo,
                      @NotNull Date lastUpdatedAt) {
    this.userIdOne = userIdOne;
    this.userIdTwo = userIdTwo;
    this.lastUpdatedAt = lastUpdatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserIdOne() {
    return userIdOne;
  }

  public void setUserIdOne(Long userIdOne) {
    this.userIdOne = userIdOne;
  }

  public Long getUserIdTwo() {
    return userIdTwo;
  }

  public void setUserIdTwo(Long userIdTwo) {
    this.userIdTwo = userIdTwo;
  }

  public Date getLastUpdatedAt() {
    return lastUpdatedAt;
  }

  public void setLastUpdatedAt(Date lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
  }

  @Override
  public String toString() {
    return "Conversation{" +
           "id=" + id +
           ", userIdOne=" + userIdOne +
           ", userIdTwo=" + userIdTwo +
           ", lastUpdatedAt=" + lastUpdatedAt +
           '}';
  }
}
