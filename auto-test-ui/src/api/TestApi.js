import axios from './../config/axios-config';

let base = '/test';

export default {

    executeTestCase: (params) => {
        return axios.get(`${base}/case/${params.id}/execute`);
    },
    executeTestUnit: (params) => {
        return axios.get(`${base}/unit/${params.id}/execute`);
    }
}