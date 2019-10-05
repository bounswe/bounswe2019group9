package app.actor;

/**
 * @author ahmet.gedemenli
 */

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
