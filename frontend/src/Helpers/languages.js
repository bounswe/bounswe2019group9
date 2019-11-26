import PropTypes from 'prop-types';

export const languages = [
  { languageId: 1, name: 'English' },
  { languageId: 2, name: 'Turkish' },
  { languageId: 3, name: 'Italian' }
];

export const nameToLanguage = (() => {
  const lanToIdMap = languages.reduce((acc, language) => ({ ...acc, [language.name]: language }), {});
  return (languageName) => lanToIdMap[languageName] || { languageId: 0, name: 'Unknown Language' };
})();

export const idToLanguage = (() => {
  const lanIdToNameMap = languages.reduce((acc, language) => ({ ...acc, [language.languageId]: language }), {});
  return (languageId) => lanIdToNameMap[languageId] || { languageId: 0, name: 'Unknown Language' };
})();

export const languageType = PropTypes.shape({
  languageId: PropTypes.number.isRequired,
  name: PropTypes.string.isRequired
});
