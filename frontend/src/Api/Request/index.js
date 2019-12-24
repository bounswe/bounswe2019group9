import {getRequest, postRequest} from "../base";

/**
 * @function addRequest
 * @param data ({ essayId, receiverId, sourceId })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
  "data": {
    "essayId": 0,
    "id": 0,
    "receiverId": 0,
    "sourceId": 0
  },
  "explanation": "string",
  "status": 0
  "explanation": "string",
  "status": 0
}
 */
export const addRequest = (data, extras) => postRequest('requests', data, extras);


/**
 * @function getRequestsByUserId
 * @param userId
 * @param extras
 * @returns {Promise<AxiosResponse<T>>}
 *
 * success response
 * {
 *  "status": 200,
 *  "explanation": null,
 *  "data": [
 *    {
 *      "essayId": 19,
 *      "username": "John Johnwell",
 *      "question": "Describe your childhood home."
 *    }
 *  ]
 *}
 */
export const getRequestsByUserId = (userId, extras) => getRequest('requests', { userId }, extras);