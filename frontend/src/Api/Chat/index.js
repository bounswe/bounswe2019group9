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
export const getMessagesByUserId = (data, extras) => getRequest('/messages', data, extras);

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
export const createMessage = (data, extras) => postRequest('/messages', data, extras);

/**
 * @function getConversationContent
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
export const getConversationContent = (data, extras) => getRequest('/messages/chat', data, extras);
