package app.mahmuthoca.service;

import app.common.HttpResponses;
import app.common.Response;
import app.mahmuthoca.bean.CreateAnnotationRequest;
import app.mahmuthoca.entity.AnnotationEntity;
import app.mahmuthoca.repository.AnnotationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnotationService {

    AnnotationRepository annotationRepository;

    public AnnotationService(AnnotationRepository annotationRepository) {
        this.annotationRepository = annotationRepository;
    }

    public Response<String> createAnnotation(CreateAnnotationRequest request) {
        annotationRepository.createAnnotation(request.getAnnotation());

        return HttpResponses.from(request.getAnnotation());
    }


    public Response<List<String>> getAnnotationsByTargetId(String targetId) {
        List<AnnotationEntity> annotationEntities = annotationRepository.getAnnotationsByTargetId(targetId);

        ArrayList<String> responseBody = new ArrayList<>();

        for (AnnotationEntity annotationEntity : annotationEntities) {
            responseBody.add(getAnnotationWithId(annotationEntity));
        }

        return HttpResponses.from(responseBody);
    }

    public String getAnnotationWithId(AnnotationEntity annotationEntity) {
        String annotation = annotationEntity.getAnnotation();
        Long id = annotationEntity.getId();

        Integer index = annotation.indexOf("{");
        return annotation.substring(0, index + 1) + " 'id': 'http://api.bounswe2019group9.tk/annotations?id=" +
                id.toString() + "'," + annotation.substring(index + 1);
    }
}
