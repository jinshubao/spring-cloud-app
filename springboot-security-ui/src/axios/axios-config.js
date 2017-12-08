import axios from 'axios'
import {Message} from 'element-ui';

axios.defaults.timeout = 30000;
axios.defaults.baseURL = process.env.API_URL;
// 添加一个请求拦截器
axios.interceptors.request.use(
  (reqeust) => {
    console.log('reqeust.params', reqeust.params)
    return reqeust;
  },
  (error) => {
    console.log('reqeust.error', error)
    return Promise.reject(error);
  })

// 添加一个响应拦截器
axios.interceptors.response.use(
  (response) => {
    console.log('response', response)
    if (response.data.resCode === '0000') {
      return Promise.resolve(response.data)
    } else {
      Message.error(response.data.resDesc)
      return Promise.reject(response.data)
    }
  },
  (error) => {
    if (error.response) {
      switch (error.response.status) {
        case 400:
          break
        case 401:
          console.log('未登录');
          window.router.push('/login');
          break;
        case 403:
          console.log('没有权限');
          window.router.push('/error/403');
          break;
        case 500:
          console.log('系统错误');
          window.router.push('/error/500');
          break;
        default:
          console.log('未知错误:' + error.response.status);
          window.router.push('/error/500')
      }
      return Promise.reject(error.response.data)
    }
    return Promise.reject(error)
  })

export default axios
