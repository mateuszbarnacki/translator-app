import axios from 'axios';
import {initMocks} from '../__mocks__'


axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers['Content-Type'] = 'application/json';

const axiosMockInstance = axios.create();
const axiosLiveInstance = axios.create();

export const axiosMockAdapterInstance = initMocks(axiosMockInstance)
export default process.env.REACT_APP_DEV ? axiosMockInstance : axiosLiveInstance;