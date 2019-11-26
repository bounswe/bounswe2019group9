import PropTypes from 'prop-types';

export const exerciseTypes = [
  { typeId: 1, route: 'listening', name: 'Listening', icon: 'customer-service' },
  { typeId: 2, route: 'reading', name: 'Reading', icon: 'read' },
  { typeId: 4, route: 'grammar', name: 'Grammar', icon: 'file-text' },
  { typeId: 3, route: 'vocabulary', name: 'Vocabulary', icon: 'book' },
];

export const typeIdToType = (() => {
  const typeMap = exerciseTypes.reduce((acc, exerciseType) =>
    ({ ...acc, [exerciseType.typeId]: exerciseType }), {});
  return (typeId) => typeMap[typeId] || { typeId: 0, route: '', name: 'Unknown' };
})();

export const routeToType = (() => {
  const typeMap = exerciseTypes.reduce((acc, exerciseType) =>
    ({ ...acc, [exerciseType.route]: exerciseType }), {});
  return (typeId) => typeMap[typeId] || { typeId: 0, route: '', name: 'Unknown' };
})();

export const exerciseTypeType = PropTypes.shape({
  typeId: PropTypes.number.isRequired,
  route: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired
});

export const exerciseContentType = PropTypes.shape({
  id: PropTypes.number.isRequired,
  languageId: PropTypes.number.isRequired,
  grade: PropTypes.number.isRequired,
  typeId: PropTypes.number.isRequired,
  imageUrl: PropTypes.string,
  soundUrl: PropTypes.string,
  questionBody: PropTypes.string.isRequired,
  optionA: PropTypes.string.isRequired,
  optionB: PropTypes.string.isRequired,
  optionC: PropTypes.string.isRequired,
  optionD: PropTypes.string.isRequired,
  correctAnswer: PropTypes.number.isRequired,
  tags: PropTypes.arrayOf(PropTypes.shape({
    id: PropTypes.number.isRequired,
    exerciseId: PropTypes.number.isRequired,
    tagText: PropTypes.string.isRequired
  })).isRequired
});
