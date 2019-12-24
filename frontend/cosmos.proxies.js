import createFetchProxy from 'react-cosmos-fetch-proxy';
import createXhrProxy from 'react-cosmos-xhr-proxy';

export default [
    createFetchProxy(),
    createXhrProxy()
];