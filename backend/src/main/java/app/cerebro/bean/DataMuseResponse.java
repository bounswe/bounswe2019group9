package app.cerebro.bean;

import java.util.List;

/**
 * @author ahmet.gedemenli
 */

public class DataMuseResponse {

  private String word;

  private Integer score;

  private List<String> tags;

  public DataMuseResponse() {
  }

  public DataMuseResponse(String word, Integer score, List<String> tags) {
    this.word = word;
    this.score = score;
    this.tags = tags;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "DataMuseResponse{" +
           "word='" + word + '\'' +
           ", score=" + score +
           ", tags=" + tags +
           '}';
  }
}
