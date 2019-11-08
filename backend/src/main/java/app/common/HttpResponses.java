package app.common;

/**
 * @author ahmet.gedemenli
 */

public final class HttpResponses {

  public static <T> Response<T> from(T data) {
    return new Response<>(data);
  }

  public static <T> Response<T> badRequest(String message) {
    return new Response<>(400, message, null);
  }

  public static <T> Response<T> notFound(String message) {
    return new Response<>(404, message, null);
  }
}
