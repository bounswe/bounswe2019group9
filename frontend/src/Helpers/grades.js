export const grades = [
  { num_grade: 1, str_grade: 'A1 - Beginner' },
  { num_grade: 2, str_grade: 'A2 - Elementary' },
  { num_grade: 3, str_grade: 'B1 - Intermediate' },
  { num_grade: 4, str_grade: 'B2 - Upper Intermediate' },
  { num_grade: 5, str_grade: 'C1 - Advanced' },
  { num_grade: 6, str_grade: 'C2 - Proficient' },
];

export const numGradeToGrade = (() => {
  const numToGradeMap = grades.reduce((acc, grade) => ({...acc, [grade.num_grade]: grade}), {});
  return (num_grade) => numToGradeMap[num_grade] || {num_grade: 0, str_grade: 'Unknown Grade'};
})();

export const numGradeToStrGrade = (num_grade) => numGradeToGrade(num_grade).str_grade;

export const calculateGrade = (correct, total) => {
  let percent = 0
  let str_grade = "Unknown Grade";
  let num_grade = -1
  if (total <= 0 || correct < 0 || correct > total) {
    return { num_grade, str_grade };
  } else {
    percent = correct / total
  }

  if (percent > 0.9) {
    num_grade = 6
    str_grade = "C2"
  } else if (percent > 0.85) {
    num_grade = 5
    str_grade = "C1"
  } else if (percent > 0.75) {
    num_grade = 4
    str_grade = "B2"
  } else if (percent > 0.60) {
    num_grade = 3
    str_grade = "B1"
  } else if (percent > 0.40) {
    num_grade = 2
    str_grade = "A2"
  } else {
    num_grade = 1
    str_grade = "A1"
  }

  return { num_grade, str_grade }
}
