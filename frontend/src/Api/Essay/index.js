import {getRequest} from "../base";

/**
 * @function getEssaysByUserId
 * @param data ({ id })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
  "data": [
    {
      "assignment": {
        "id": 0,
        "languageId": 0,
        "question": "string"
      },
      "essay": {
        "assignmentId": 0,
        "authorId": 0,
        "id": 0,
        "source": "string",
        "sourceType": 0
      }
    }
  ],
  "explanation": "string",
  "status": 0
}
 */
export const getEssaysByUserId = (data, extras) => getRequest('essays/user', data, extras);

/**
 * @function getEssayById
 * @param essayId
 * @param extras
 * @returns {Promise<AxiosResponse<T>>}
 *
 * Success Response
 * {
 *   "status": 200,
 *   "explanation": null,
 *   "data": {
 *     "id": 2,
 *     "assignmentId": 1,
 *     "authorId": 9,
 *     "sourceType": 1,
 *     "source": "kjdgkjnkjkgdkjangkjdrngkjdangkjadfn kjdfkjadgkjadhgkjdahfkjadhgkjadfh kjdfkjdhgkjdfhgkjfdhg"
 *   }
 * }
 */
export const getEssayById = (essayId, extras) => getRequest('essays', { id: essayId }, extras);