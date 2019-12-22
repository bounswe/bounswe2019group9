package app.mahmuthoca.controller;

import app.common.Response;
import app.mahmuthoca.service.AnnotationService;
import app.mahmuthoca.bean.CreateAnnotationRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annotations")
public class AnnotationController {
    AnnotationService annotationService;

    public AnnotationController(AnnotationService annotationService) {

        this.annotationService = annotationService;
    }

    @GetMapping("/searchInAnnotations")
    public Response<List<String>> searchInAnnotations(@RequestParam("targetId") String targetId) {
        return annotationService.getAnnotationsByTargetId(targetId);
    }


    @PostMapping
    public Response<String> createAnnotation(@Validated @RequestBody CreateAnnotationRequest request) {
        return annotationService.createAnnotation(request);
    }
}
