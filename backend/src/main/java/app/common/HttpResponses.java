package app.common;

/**
 * @author ahmet.gedemenli
 */

public final class HttpResponses {

  public static final Integer SUCCESSFUL = 200;

  public static final Integer BAD_REQUEST = 400;

  public static final Integer NOT_FOUND = 404;

  public static <T> Response<T> from(T data) {
    return new Response<>(data);
  }

  public static <T> Response<T> badRequest(String message) {
    return new Response<>(BAD_REQUEST, message, null);
  }

  public static <T> Response<T> notFound(String message) {
    return new Response<>(NOT_FOUND, message, null);
  }
}
