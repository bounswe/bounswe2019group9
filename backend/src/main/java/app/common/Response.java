package app.common;

/**
 * @author ahmet.gedemenli
 */

public class Response<T> {

  private final Integer status;

  private final String explanation;

  private final T data;

  Response(T data) {
    this(200, null, data);
  }

  public Response(Integer status, String explanation, T data) {
    this.status = status;
    this.explanation = explanation;
    this.data = data;
  }

  public static <T> Response<T> from(T data) {
    return new Response<>(data);
  }

  public Integer getStatus() {
    return status;
  }

  public String getExplanation() {
    return explanation;
  }

  public T getData() {
    return data;
  }

  @Override
  public String toString() {
    return "Response{" +
           "status=" + status +
           ", explanation='" + explanation + '\'' +
           ", data=" + data +
           '}';
  }
}
