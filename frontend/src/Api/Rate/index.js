import {getRequest, postRequest} from "../base";

/**
 * @function addRating
 * @param data ({ rating, receiverId, senderId })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
  "data": {
    "rating": 0,
    "receiverId": 0,
    "senderId": 0
  },
  "explanation": "string",
  "status": 0
}
 */
export const addRating = (data, extras) => postRequest('ratings', data, extras);

/**
 * @function getRatingBetween
 * @param data ({ receiverId, senderId })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {{
  "data": 0,
  "explanation": "string",
  "status": 0
}
  "explanation": "string",
  "status": 0
}
 */
export const getRatingBetween = (data, extras) => getRequest('ratings/fromAtoB', data, extras);

/**
 * @function updateRating
 * @param data ({ receiverId, senderId, rating })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
  "data": 0,
  "explanation": "string",
  "status": 0
}
 */
export const updateRating = (data, extras) => postRequest('ratings/update', data, extras);
