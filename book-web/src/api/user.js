import axiosInstance from '@/utils/request'

// 登录接口
export const login = async (data) =>
  await axiosInstance('/api/logins/login', 'get', data)
