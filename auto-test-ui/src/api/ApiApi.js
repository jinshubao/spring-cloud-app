import axios from './../config/axios-config';

let base = '/apis';

export default {
    load: (params) => {
        return axios.get(`${base}/list`, {params: params});
    },
    loadById: (params) => {
        return axios.get(`${base}/apis`, {params: params});
    },
    add: (params) => {
        return axios.post(`${base}`, params);
    },
    remove: (params) => {
        return axios.delete(`${base}/${params.id}`);
    },
    modify: (params) => {
        return axios.put(`${base}`, params);
    },

    apiNames:(params)=>{
        return axios.get(`${base}/names`, {params: params});
    }
}