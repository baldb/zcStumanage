import axios from 'axios'
import { Notification } from 'element-ui'
// import store from '@/store'

const service = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API,
  timeout: 2000,
})

service.interceptors.request.use(
  (config) => {
    // console.log('config: ', config)
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
    const res = response.data

    return res
  },
  (error) => {
    if (!error.response.data) {
      Notification.error({
        message: error,
      })
      return Promise.reject(error)
    }
    const res = error.response.data

    Notification.error({
      title: res.msg,
      message: res.status,
    })
    return Promise.reject(errorMessage)
  }
)

// 创建实例
const axiosInstance = (url, method, data) =>
  service(
    Object.assign(
      { url, method },
      method === 'get' || method === 'delete' ? { params: data } : { data }
    )
  )

export default axiosInstance
