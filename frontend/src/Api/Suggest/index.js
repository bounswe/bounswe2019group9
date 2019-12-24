import { postRequest} from '../base';

/**
 * @function addSuggestion
 * @param data ({ question, choice1, choice2, choice3, choice4 })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * {
 *  "data": {
 *    "question": "",
 *    "choice1": "",
 *    "choice2": "",
 *    "choice3": "",
 *    "choice4": ""
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const addSuggestion = (data, extras) => postRequest('suggestion/add', data, extras);
