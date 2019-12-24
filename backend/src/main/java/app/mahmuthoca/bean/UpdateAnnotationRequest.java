package app.mahmuthoca.bean;

import javax.validation.constraints.NotNull;

public class UpdateAnnotationRequest {
    @NotNull
    private Long id;

    @NotNull
    private String annotation;

    public UpdateAnnotationRequest() {
    }

    public UpdateAnnotationRequest(@NotNull Long id, @NotNull String annotation) {
        this.id = id;
        this.annotation = annotation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "UpdateAnnotationRequest{" +
                "id=" + id +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
