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