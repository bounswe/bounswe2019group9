const delay = (ms) =>
  new Promise((resolve, reject) =>
    setTimeout(() => {
      resolve();
    }, ms));

export default delay;