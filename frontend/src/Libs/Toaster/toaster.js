import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.min.css';
import axios from 'axios';
import {updateStore} from '../../Store';

let initialized = false;

if (!initialized) {
  initialized = true;

  toast.configure({
    hideProgressBar: true,
    newestOnTop: true,
    position: toast.POSITION.TOP_CENTER,
    autoClose: 3000
  });
  // bind toasts to axios defaults
  const isToastsEnabled = (config = {}) =>
    !(config.hasOwnProperty('toastsEnabled') && !config.toastsEnabled);
  axios.defaults.isToastsEnabled = true;
  const requestHandler = (config) => {
    if (isToastsEnabled(config)) {
      config.uploadToast = {id: null};
      if (config.headers["Content-Type"] === 'multipart/form-data') {
        const prevUploadProgress = config.onUploadProgress;
        config.onUploadProgress = (p) => {
          const progress = p.loaded / (p.total + 1);
          if (config.uploadToast.id === null) {
            config.uploadToast.id = toast.info('Upload in progress', {progress, hideProgressBar: false});
          } else {
            toast.update(config.uploadToast.id, {progress});
          }
          return prevUploadProgress && prevUploadProgress(p);
        };
      }
    }
    return config;
  };
  const errorHandler = (error) => {
    let errorMessage = null;
    console.log('error interceptor', error, error.response);
    if (error.response) {
      if (error.response.status === 401 || error.response.status === 403) {
        errorMessage = "Unauthorized, please login again. Your token may have been expired.";
        updateStore({
          token: '', userId: ''
        });
      } else if (typeof error.response.data === 'string') {
        errorMessage = error.response.data;
      } else {
        errorMessage = 'Oops something went wrong!';
      }
    } else if (error.request) {
      errorMessage = 'Oops something went wrong in the server!';
    } else {
      console.log('Error building axios request', error.message);
    }
    if (isToastsEnabled(error.config)) {
      // toast update/done checks performs noop/returns null if toastId is invalid, so no need to null check toastId
      toast.done(error.config.uploadToast.id);

      if (errorMessage) {
        toast.error(errorMessage);
      }
    }
    return Promise.reject({...error, message: errorMessage || error.message});
  };
  const responseHandler = (response) => {
    if (isToastsEnabled(response.config)) {
      toast.done(response.config.uploadToast.id);
      const {explanation, status} = response.data;
      if (status === 401 || status === 403) {
        toast.error("Unauthorized, please login again. Your token may have been expired.");
        updateStore({
          token: '', userId: ''
        });
      } else if (status && (status < 200 || status >= 300)) {
        const errorMessage = explanation || 'Oops something went wrong!';
        toast.error(errorMessage);
        console.log(`Custom error on ${response.config.url} ${status} ${explanation}`, response.data);
        return Promise.reject({ message: errorMessage });
      } else {
        let successMessage = null;
        if (response.config.hasOwnProperty('successMessage') && response.config.successMessage) {
          successMessage = response.config.successMessage;
        } else if (explanation) {
          successMessage = explanation;
        }
        if (successMessage) {
          toast.success(successMessage);
        }
      }
    }
    return response;
  };
  axios.interceptors.request.use(
    request => requestHandler(request)
  );
  axios.interceptors.response.use(
    response => responseHandler(response),
    error => errorHandler(error),
  );
}
