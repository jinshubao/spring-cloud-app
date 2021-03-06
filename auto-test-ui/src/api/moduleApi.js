import axios from './../config/axios-config';

let base = '/modules';

export default {
    add: (params) => {
        return axios.post(`${base}`, params);
    },
    remove: (params) => {
        return axios.delete(`${base}/${params.id}`);
    },
    modify: (params) => {
        return axios.put(`${base}`, params);
    },
    load: (params) => {
        return axios.get(`${base}`, {params: params});
    },
    loadById: (params) => {
        return axios.get(`${base}/apis`, {params: params});
    },
    names: (params) => {
        return axios.get(`${base}/names`, {params: params});
    }
}