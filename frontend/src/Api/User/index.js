import {getRequest, postRequest} from '../base';

/**
 * @function getUserById
 * @param userId
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response:
 * {
 *  "email": "string",
 *  "firstName": "string",
 *  "id": 0,
 *  "lastName": "string",
 *  "password": "string"
 * }
 *
 */
export const getUserById = (userId, extras) => getRequest('users/get', { id: userId }, extras);

/**
 * @function getProfileInfoByUserId
 * @param userId
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response:
 * {
 *  "email": "string",
 *  "firstName": "string",
 *  "grades": [
 *    0
 *  ],
 *  "languages": [
 *    "string"
 *  ],
 *  "lastName": "string",
 *  "progressLevels": [
 *    0
 *  ],
 *  "userId": 0
 * }
 *
 */
export const getProfileInfoByUserId = (userId, extras) => getRequest('users/profile', { id: userId }, extras);

/**
 * @function login
 * @param data
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response:
 * {
 *  "data": {
 *    "email": "string",
 *    "firstName": "string",
 *    "id": 0,
 *    "lastName": "string",
 *    "password": "string"
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const login = (data, extras) => postRequest(`users/login`, data, extras);

/**
 * @function register
 * @param data ({ email, firstName, lastName, password })
 * @param extras object?
 * @return {Promise<AxiosResponse<T>>}
 * Success Response
 * {
 *  "data": {
 *    "email": "string",
 *    "firstName": "string",
 *    "id": 0,
 *    "lastName": "string",
 *    "password": "string"
 *  },
 *  "explanation": "string",
 *  "status": 0
 * }
 */
export const register = (data, extras) => postRequest(`users/register`, data, extras);
