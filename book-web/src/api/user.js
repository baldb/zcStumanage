import axiosInstance from '@/utils/request'

// 登录接口
export const login = async (data) =>
  await axiosInstance('/api/logins/login', 'get', data)

// 获取学生列表
export const getStudent = async ({ pn = 1, offset = 10 }) =>
  await axiosInstance('/api/stus/stu', 'get', { pn, offset })
