import axios from './../config/axios-config';

let base = '/cases';

export default {
    load: (params) => {
        return axios.get(`${base}/list`, {params: params});
    },

    loadById: (params) => {
        return axios.get(`${base}/apis`, {params: params});
    },
    execute: (params) =>{

        return axios.get(`${base}//test/case/{id}/execute`, {params: params});
    }
}