import axios from 'axios';

let base = '/users';

export const login = params => {
    return axios.post(`${base}/login`, params).then(res => res.data);
};
