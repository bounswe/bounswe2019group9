import {getRequest} from '../base';

/**
 * @function getGreeting
 * @param data ({ name })
 * @param extras
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
 *  "id": 3,
 *  "content": "Hello, group9!"
 * }
 */
export const getGreeting = (data, extras) => getRequest('greeting', data, extras);
