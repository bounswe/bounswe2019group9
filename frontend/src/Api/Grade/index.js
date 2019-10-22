import {postRequest} from '../base';

/**
 * @function addGrade
 * @param data ({ grade, languageId, userId })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": {
 *    "grade": 0,
 *    "languageId": 0,
 *    "userId": 0
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const addGrade = (data, extras) => postRequest('grades/add', data, extras);
