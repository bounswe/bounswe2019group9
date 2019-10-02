import React from 'react';
import PropTypes from 'prop-types';

const subscriptions = new Set();

const localStorageKey = 'bounswe_store';

let store = localStorage.getItem(localStorageKey);
store = store ? JSON.parse(store) : {
  token: ''
};

export const storeType = {
  token: PropTypes.string.isRequired
};

const subscribeStore = (subscription) => {
  subscriptions.add(subscription);
};

const unsubscribeStore = (subscription) => {
  subscriptions.delete(subscription);
};

export const updateStore = (nextStore) => {
  store = {
    ...store,
    ...nextStore
  };
  localStorage.setItem(localStorageKey, JSON.stringify(store));
  subscriptions.forEach((subscription) => subscription(store));
};

export const connect = (ChildComponent) => {
  return function StoreInjector(props) {
    const [currentStore, setCurrentStore] = React.useState(store);

    React.useEffect(() => {
      function handleStoreChange(nextStore) {
        setCurrentStore(nextStore);
      }
      subscribeStore(handleStoreChange);

      return function cleanup() {
        unsubscribeStore(handleStoreChange);
      };
    }, []);

    return <ChildComponent store={currentStore} {...props} updateStore={updateStore} />;
  }
};

export default store;
