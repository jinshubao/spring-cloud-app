import axios from './../axios/axios-config';

let base = '/user';

export default {
  add: (params) => {
    return axios.post(`${base}/add`, params)
  },

  list: (params) => {
    return axios.get(`${base}/list`, {params: params})
  },

  updateRole:(userId, roles)=>{
    return axios.post(`${base}/${userId}/roles`, roles)
  }
}
