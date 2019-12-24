import {postRequest} from "../base";

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