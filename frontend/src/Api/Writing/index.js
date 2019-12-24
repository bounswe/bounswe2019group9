import {postRequest} from "../base";

//TODO backend call

/**
 * @function addWriting
 * @param data ({ languageId, userId, subject, text })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": {
 *    "languageId": 0,
 *    "subject": "",
 *    "text": ""
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const addWriting = (data, extras) => postRequest('writings/add', data, extras);