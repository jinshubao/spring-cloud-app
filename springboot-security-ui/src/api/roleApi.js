import axios from './../axios/axios-config';

let base = '/role';

export default {
  add: (params) => {
    return axios.post(`${base}/add`, params)
  },

  list: (params) => {
    return axios.get(`${base}/list`, {params: params})
  },

  all: () => {
    return axios.get(`${base}/all`)
  }
}
