import {postRequest} from "../base";

/**
 * @function getRecommendations
 * @param data ({ grade, languageId })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
  "data": [
    {
      "email": "string",
      "firstName": "string",
      "grades": [
        0
      ],
      "languages": [
        "string"
      ],
      "lastName": "string",
      "progressLevels": [
        0
      ],
      "rating": 0,
      "userId": 0
    }
  ],
  "explanation": "string",
  "status": 0
}
 */
export const getRecommendations = (data, extras) => postRequest('recommendations', data, extras);
