import {getRequest} from '../base';

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
export const getLanguages = (extras) => getRequest('contents/laguages', {}, extras);

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


