export const numGradeToStrGrade = (num_grade) => {
  switch (num_grade) {
    case 1:
      return 'A1';
    case 2:
      return 'A2';
    case 3:
      return 'B1';
    case 4:
      return 'B2';
    case 5:
      return 'C1';
    case 6:
      return 'C2';
    default:
      return 'UnknownGrade'
  }
};

export const calculateGrade = (correct, total) => {
  let percent = 0
  let str_grade = "UnknownGrade";
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
