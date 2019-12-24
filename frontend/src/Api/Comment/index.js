import {getRequest, postRequest} from '../base';

/**
 * @function commentAdd
 * @param data ({ content, receiverId, sourceId })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": {
 *   "content": "string",
 *   "receiverId": 0,
 *   "sourceId": 0,
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const commentAdd = (data, extras) => postRequest('comments', data, extras);

/**
 * @function commentsGet
 * @param data ({ userId })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": [{
      "comment": {
        "content": "string",
        "createdAt": "2019-12-24T00:32:24.496Z",
        "id": 0,
        "receiverId": 0,
        "sourceId": 0
      },
      "sourceFirstName": "string",
      "sourceLastName": "string"
    },],
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const commentsGet = (data, extras) => getRequest('comments/getCommentsByReceiverId', data, extras);