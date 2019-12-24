import { getRequest, postRequest, putRequest } from "../base";

/**
 * @function createAnnotation
 * @param data
 * @param extra
 * @returns {Promise<AxiosResponse<T>>}
 *
 * params: {
 *   "annotation": "string" (do not send id here!)
 * }
 *
 * success response
 * {
 *   "status": 200,
 *   "explanation": null,
 *   "data": "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=21\", \"dom\": \"pom\" }"
 * }
 */
export const createAnnotation = (data, extras) => postRequest('annotations', data, extras);

/**
 * @function deleteAnnotationById
 * @param annotationId
 * @param extras
 * @returns {Promise<AxiosResponse<T>>}
 *
 *
 * success response
 * {
 *   "status": 200,
 * "explanation": null,
 * "data": 1
 * }
 */
export const deleteAnnotationById = (annotationId, extras) => getRequest('annotations/deleteById', { annotationId }, extras);

/**
 * @function searchAnnotations
 * @param targetId
 * @param extras
 * @returns {Promise<AxiosResponse<T>>}
 *
 * success response
 * {
 *   "status": 200,
 * "explanation": null,
 * "data": [
 *     "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=12\",'targetId': 'http://example.com/writing1#char=0,10', 'value' : 'Bad Mistake'}",
 *     "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=13\",'targetId': 'http://example.com/writing1#char=0,10', 'value' : 'Bad Mistake'}"
 * ]
 * }
 */
export const searchAnnotations = (targetId, extras) => getRequest('annotations/searchInAnnotations', { targetId }, extras);

/**
 * @function updateAnnotation
 * @param data
 * @param extras
 * @returns {Promise<AxiosResponse<T>>}
 *
 * params: {
 *   "id": 0,
 *   "annotation": "string" (do not send id here!)
 * }
 *
 * success response
 * {
 *  "status": 200,
 *  "explanation": null,
 *  "data": "{ \"id\": \"http://api.bounswe2019group9.tk/annotations?id=2\", \"dom\": \"pop\" }"
 * }
 */
export const updateAnnotation = (data, extras) => postRequest('annotations/update', data, extras);