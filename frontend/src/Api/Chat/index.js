import {getRequest, postRequest} from '../base';

/**
 * @function getMessagesByUserId
 * @param data ({ userId })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * [
 *  {
 *   "content": "string",
 *   "createdAt": "2019-11-26T02:10:48.077Z",
 *   "id": 0,
 *   "receiverId": 0,
 *   "sourceId": 0
 *  }
 * ]
 */
export const getMessagesByUserId = (userId, extras) => getRequest('messages', { userId }, extras);

/**
 * @function createMessage
 * @param data ({ content, receiverId, sourceId })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * [
 *  {
 *   "content": "string",
 *   "createdAt": "2019-11-26T02:12:55.702Z",
 *   "id": 0,
 *   "receiverId": 0,
 *   "sourceId": 0
 *  }
 * ]
 */
export const createMessage = (data, extras) => postRequest('messages', data, extras);

/**
 * @function getChatBetween
 * @param data ({ userId1, userId2 })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * [
 *  {
 *   "content": "string",
 *   "createdAt": "2019-11-26T02:12:55.702Z",
 *   "id": 0,
 *   "receiverId": 0,
 *   "sourceId": 0
 *  }
 * ]
 */
export const getChatBetween = (data, extras) => getRequest('messages/chat', data, extras);

/**
 * @function getConversationsOf
 * @param data ({ userId })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * [
 *   {
 *     "email": "string",
 *     "firstName": "string",
 *     "grades": [
 *       0
 *     ],
 *     "languages": [
 *       "string"
 *     ],
 *     "lastName": "string",
 *     "progressLevels": [
 *       0
 *     ],
 *     "rating": 0,
 *     "userId": 0
 *   }
 * ],
 */
export const getConversationsOf = (userId, extras) => getRequest('conversations', {id: userId}, extras);
