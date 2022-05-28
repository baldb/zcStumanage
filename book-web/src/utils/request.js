import axios from 'axios'
import { Notification } from 'element-ui'
// import store from '@/store'

const service = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API,
  // timeout: 5000,
})

service.interceptors.request.use(
  (config) => {
    console.log('config: ', config)
    // 目前没有token
    // if (store.getters.token) {
    //   config.headers['X-Token'] = getToken()
    // }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  (response) => {
    console.log('response: ', response)
    const res = response.data

    return res
  },
  (error) => {
    const errorMessage = error.response.data
    console.log('errorMessage: ', errorMessage)
    Notification.error({
      title: '请求错误！！！',
      message: errorMessage,
    })
    return Promise.reject(errorMessage)
  }
)

// 创建实例
const axiosInstance = (url, method, data) =>
  service(
    Object.assign({ url, method }, method === 'get' ? { params: data } : data)
  )

export default axiosInstance
