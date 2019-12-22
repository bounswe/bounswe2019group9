package app.mahmuthoca.bean;

import javax.validation.constraints.NotNull;

public class CreateAnnotationRequest {

    @NotNull
    private String annotation;

    public CreateAnnotationRequest() {
    }

    public CreateAnnotationRequest(@NotNull String annotation) {
        this.annotation = annotation;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "CreateAnnotationRequest{" +
                "annotation='" + annotation + '\'' +
                '}';
    }
}
