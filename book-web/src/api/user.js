import axiosInstance from '@/utils/request'

// 登录接口
export const login = async (data) =>
  await axiosInstance('/api/logins/login', 'get', data)

// 获取学生列表
export const getStudent = async ({ pn = 1, offset = 9 } = {}) =>
  await axiosInstance('/api/supers/super', 'get', { pn, offset })

// 添加学生接口
export const addStudent = async (playload = {}) => {
  return await axiosInstance('/api/supers/super', 'post', playload)
}
// 修改学生接口
export const editStudent = async (playload = {}) => {
  return await axiosInstance('/api/supers/super', 'put', playload)
}
