import { getRequest, postRequest } from '../base';

/**
 * @function createInvitation
 * @param data ({ receiverId, sourceId })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": {
 *   "createdAt": "2019-11-26T00:20:43.284Z",
 *   "id": 0,
 *   "receiverId": 0,
 *   "sourceId": 0
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const createInvitation = (data, extras) => postRequest('invitations/add', data, extras);

/**
 * @function getInvitationState
 * @param data ({ userId1, userId2 })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": {
 *   "pendingRequestFromOneToTwo": true,
 *   "pendingRequestFromTwoToOne": true,
 *   "startedConversation": true,
 *   "userId1": 0,
 *   "userId2": 0
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const getInvitationState = (data, extras) => getRequest('invitations/state', data, extras);

/**
 * @function answerToInvitation
 * @param data ({ approved, receiverId, sourceId })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": {
 *   "id": 0,
 *   "lastUpdatedAt": "2019-11-26T01:01:41.278Z",
 *   "userIdOne": 0,
 *   "userIdTwo": 0
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const answerToInvitation = (data, extras) => postRequest('invitations/answer', data, extras);
