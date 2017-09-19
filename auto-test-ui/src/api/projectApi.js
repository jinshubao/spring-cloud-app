import axios from 'axios';

let base = '/projects';

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
    modules: (params) => {
        return axios.get(`${base}/modules`, {params: params});
    },
    allProjectNames: (params) => {
        return axios.get(`${base}/names`, {params: params})
    },
    allProjects: (params) => {
        return axios.get(`${base}/all`, {params: params})
    }
}

