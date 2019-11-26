package app.cerebro.service;

import app.actor.entity.ExerciseSolvedInfo;
import app.actor.repository.SolvedExercisesRepository;
import app.actor.service.UserService;
import app.cerebro.bean.DataMuseResponse;
import app.cerebro.bean.SearchExerciseRequest;
import app.common.HttpResponses;
import app.common.Response;
import app.proseidon.entity.Exercise;
import app.proseidon.entity.Tag;
import app.proseidon.service.ContentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author ahmet.gedemenli
 */

@Service
public class SearchExerciseService {

  private final UserService userService;

  private final SolvedExercisesRepository solvedExercisesRepository;

  private final ContentService contentService;

  public SearchExerciseService(UserService userService,
                               SolvedExercisesRepository solvedExercisesRepository,
                               ContentService contentService) {
    this.userService = userService;
    this.solvedExercisesRepository = solvedExercisesRepository;
    this.contentService = contentService;
  }

  public Response<List<Exercise>> searchExercise(SearchExerciseRequest request) {
    List<Exercise> exercises = contentService.getAllExercises().getData();
    exercises = filterByLanguage(exercises, request.getLanguageId());
    exercises = filterByGrade(exercises, request.getGrade());
    exercises = filterByType(exercises, request.getTypeId());
    exercises = filterByTags(exercises, request.getTag());
    exercises = filterByUserId(exercises, request.getUserId());
    return HttpResponses.from(exercises);
  }

  private List<Exercise> filterByUserId(List<Exercise> exercises, Long userId) {
    if (isNull(userId) || isNull(userService.getUserById(userId).getData())) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      boolean flag = true;
      List<ExerciseSolvedInfo> solvedInfos = solvedExercisesRepository.getSolvedExercisesByUserId(userId);
      for (ExerciseSolvedInfo info : solvedInfos) {
        if (info.getExerciseId() == exercise.getId()) {
          flag = false;
          break;
        }
      }
      if (flag) {
        filtered.add(exercise);
      }
    }
    return filtered;
  }

  private List<Exercise> filterByType(List<Exercise> exercises, Integer typeId) {
    if (isNull(typeId) || typeId < 1) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      if (exercise.getTypeId() == typeId) {
        filtered.add(exercise);
      }
    }
    return filtered;
  }

  private List<Exercise> filterByGrade(List<Exercise> exercises, Integer grade) {
    if (isNull(grade) || grade < 1 || grade > 6) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      if (exercise.getGrade() == grade) {
        filtered.add(exercise);
      }
    }
    return filtered;
  }

  private List<Exercise> filterByLanguage(List<Exercise> exercises, Integer languageId) {
    if (isNull(languageId) || languageId < 1) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      if (exercise.getLanguageId() == languageId) {
        filtered.add(exercise);
      }
    }
    return filtered;
  }

  private List<Exercise> filterByTags(List<Exercise> exercises, String tag) {
    if (isNull(tag) || tag.equals("")) {
      return exercises;
    }
    ObjectMapper objectMapper = new ObjectMapper();
    List<DataMuseResponse> response;
    try {
      response = objectMapper.readValue(findSimilar(tag), new TypeReference<List<DataMuseResponse>>() {
      });
    } catch (IOException e) {
      return exercises;
    }
    List<Exercise> filtered = new ArrayList<>();
    for (Exercise exercise : exercises) {
      List<Tag> tags = exercise.getTags();
      if (isNull(tags)) {
        continue;
      }
      for (Tag exerciseTag : tags) {
        if (exerciseTag.getTagText().equals(tag)) {
          filtered.add(exercise);
          break;
        }
        Boolean flag = false;
        for (DataMuseResponse word : response) {
          if (exerciseTag.getTagText().equals(word.getWord())) {
            filtered.add(exercise);
            flag = true;
            break;
          }
        }
        if (flag) {
          break;
        }
      }
    }
    return filtered;
  }

  private String findSimilar(String word) {
    String s = word.replaceAll(" ", "+");
    return getJSON("http://api.datamuse.com/words?rd=" + s + "&max=1000");
  }

  private String getJSON(String url) {
    URL datamuse;
    URLConnection dc;
    StringBuilder s = null;
    try {
      datamuse = new URL(url);
      dc = datamuse.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(dc.getInputStream(), "UTF-8"));
      String inputLine;
      s = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        s.append(inputLine);
      }
      in.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return s != null ? s.toString() : null;
  }
}
