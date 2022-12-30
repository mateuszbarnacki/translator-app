import axios from 'axios';
import {initMocks} from '../__mocks__'


// TODO change to match backend url
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers['Content-Type'] = 'application/json';

const axiosMockInstance = axios.create();
const axiosLiveInstance = axios.create();

// TODO adjust mocking path to real backend
export const axiosMockAdapterInstance = initMocks(axiosMockInstance)
export default process.env.REACT_APP_DEV ? axiosMockInstance : axiosLiveInstance;