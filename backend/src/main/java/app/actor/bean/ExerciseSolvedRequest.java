package app.actor.bean;

/**
 * @author ahmet.gedemenli
 */

public class ExerciseSolvedRequest {

  private Long userId;

  private Long exerciseId;

  public ExerciseSolvedRequest(Long userId, Long exerciseId) {
    this.userId = userId;
    this.exerciseId = exerciseId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(Long exerciseId) {
    this.exerciseId = exerciseId;
  }

  @Override
  public String toString() {
    return "ExerciseSolvedRequest{" +
           "userId=" + userId +
           ", exerciseId=" + exerciseId +
           '}';
  }
}
