import axiosInstance from '@/utils/request'

// 登录接口
export const login = async (data) =>
  await axiosInstance('/api/logins/login', 'get', data)

// 获取学生基本信息
export const loginStu = async (data) =>
  await axiosInstance('/api/logins/stu', 'get', data)

// 获取老师基本信息
export const loginTeacher = async (data) =>
  await axiosInstance('/api/logins/teacher', 'get', data)

// 获取学生列表
export const getStudent = async ({ pn = 1, offset = 8 } = {}) =>
  await axiosInstance('/api/supers/super', 'get', { pn, offset })

// 添加学生接口
export const addStudent = async (playload = {}) => {
  return await axiosInstance('/api/supers/super', 'post', playload)
}
// 修改学生接口
export const editStudent = async (playload = {}) => {
  return await axiosInstance('/api/supers/super', 'put', playload)
}
//
export const deleteStudent = async (playload = {}) => {
  return await axiosInstance('/api/supers/super', 'delete', playload)
}
