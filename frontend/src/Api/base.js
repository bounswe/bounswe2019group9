import axios from 'axios';

import {API_URL} from '../Constants';

export const getRequest = (apiUrl, params = {}, extras = {}) =>
  axios.get(`${API_URL}${apiUrl}`, {
    ...extras,
    params,
  });

export const postRequest = (apiUrl, data = {}, extras = {}) =>
  axios.post(`${API_URL}${apiUrl}`, data, {
    ...extras,
  });

export const postFormDataRequest = (apiUrl, data = {}, extras = {}) => {
  const formData = new FormData();
  Object.keys(data).forEach((key) => formData.append(key, data[key]));

  return axios.post(`${API_URL}${apiUrl}`, formData, {
    ...extras,
  })
};

export const putRequest = (apiUrl, data = {}, extras = {}) =>
  axios.put(`${API_URL}${apiUrl}`, data, {
    ...extras,
  });

export const deleteRequest = (apiUrl, params = {}, extras = {}) =>
  axios.delete(`${API_URL}${apiUrl}`, {
    ...extras,
    params,
  });
