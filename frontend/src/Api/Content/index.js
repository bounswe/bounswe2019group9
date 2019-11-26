import {getRequest, postRequest} from '../base';

/**
 * @function getLanguages
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
 *  "status": 200,
 *  "explanation": null,
 *  "data": [
 *    "English",
 *    "Turkish",
 *    "Italian"
 *  ]
 * }
 */
export const getLanguages = (extras) => getRequest('contents/languages', {}, extras);

/**
 * @function getProfExam
 * @param language
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
 *    "status": 200,
 *    "explanation": null,
 *    "data": [
 *        {
 *            "languageId": 1,
 *            "typeId": 1,
 *            "imageUrl": null,
 *            "soundUrl": null,
 *            "question": "what is your name?",
 *            "optionA": "my name is..",
 *            "optionB": "your name is..",
 *            "optionC": "his name is...",
 *            "optionD": "her name is...",
 *            "correctAnswer": 1
 *        },
 *        {
 *          ...
 *        },
 *
 *        ...
 *    ]
 * }
 */
export const getProfExam = (language, extras) => getRequest(`contents/prof`, {language}, extras);

/**
 * @function getExerciseById
 * @param exerciseId
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
 *  "data": {
 *    "correctAnswer": 0,
 *    "grade": 0,
 *    "id": 0,
 *    "imageUrl": "string",
 *    "languageId": 0,
 *    "optionA": "string",
 *    "optionB": "string",
 *    "optionC": "string",
 *    "optionD": "string",
 *    "questionBody": "string",
 *    "soundUrl": "string",
 *    "tags": [
 *      {
 *        "exerciseId": 0,
 *        "id": 0,
 *        "tagText": "string"
 *      }
 *    ],
 *    "typeId": 0
 *  },
 *  "explanation": "string",
 *  "status": 0
 *}
 */
export const getExerciseById = (exerciseId, extras) => getRequest('contents', { id: exerciseId }, extras);


/**
 * @function searchExercises
 * @param params
 * @param extra
 * @return {Promise<AxiosResponse<T>>}
 *
 * params: {
 *   "grade": 0,
 *   "languageId": 0,
 *   "tag": "string",
 *   "typeId": 0
 * }
 * success response
 * {
 *   "data": [
 *     {
 *       "correctAnswer": 0,
 *       "grade": 0,
 *       "id": 0,
 *       "imageUrl": "string",
 *       "languageId": 0,
 *       "optionA": "string",
 *       "optionB": "string",
 *       "optionC": "string",
 *       "optionD": "string",
 *       "questionBody": "string",
 *       "soundUrl": "string",
 *       "tags": [
 *         {
 *           "exerciseId": 0,
 *           "id": 0,
 *           "tagText": "string"
 *         }
 *       ],
 *       "typeId": 0
 *     }
 *   ],
 *   "explanation": "string",
 *   "status": 0
 * }
 */
export const searchExercises = (data, extras) => postRequest('search/exercises', data, extras);
