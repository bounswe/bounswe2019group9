import {getRequest, postRequest} from "../base";

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
export const addWriting = (data, extras) => postRequest('essays', data, extras);


export const getAssignmentsById = (languageId, extras) => getRequest('assignments/language', { id: languageId }, extras);

export const uploadEssayPhoto = (data, extras) => postRequest('files/images/essay', data, extras);