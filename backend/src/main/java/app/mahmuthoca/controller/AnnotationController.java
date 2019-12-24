package app.mahmuthoca.controller;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.CreateAnnotationRequest;
import app.mahmuthoca.bean.UpdateAnnotationRequest;
import app.mahmuthoca.service.AnnotationService;
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

    @GetMapping("/getById")
    public String getById(@RequestParam("annotationId") Long annotationId) {
        return annotationService.getById(annotationId);
    }

    @GetMapping("/deleteById")
    public Response<Integer> deleteById(@RequestParam("annotationId") Long annotationId) {
        annotationService.delete(annotationId);
        return HttpResponses.from(1);
    }

    @PostMapping("/update")
    public Response<String> updateAnnotation(@Validated @RequestBody UpdateAnnotationRequest request) {
        return annotationService.updateAnnotation(request);
    }
}
