package app.mahmuthoca.bean;

import app.mahmuthoca.entity.Assignment;
import app.mahmuthoca.entity.Essay;

/**
 * @author ahmet.gedemenli
 */

public class AssignmentEssay {

  private Essay essay;

  private Assignment assignment;

  public AssignmentEssay(Essay essay, Assignment assignment) {
    this.essay = essay;
    this.assignment = assignment;
  }

  public Essay getEssay() {
    return essay;
  }

  public void setEssay(Essay essay) {
    this.essay = essay;
  }

  public Assignment getAssignment() {
    return assignment;
  }

  public void setAssignment(Assignment assignment) {
    this.assignment = assignment;
  }

  @Override
  public String toString() {
    return "AssignmentEssay{" +
           "essay=" + essay +
           ", assignment=" + assignment +
           '}';
  }
}
