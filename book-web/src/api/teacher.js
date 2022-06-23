import axiosInstance from '@/utils/request'

// 获取老师列表
export const getTeacher = async ({ pn = 1, offset = 8 } = {}) =>
  await axiosInstance('/api/supers/teacher', 'get', { pn, offset })

export const editTeacher = async (playload = {}) =>
  await axiosInstance('/api/supers/teacher', 'put', playload)

export const addTeacher = async (playload = {}) =>
  await axiosInstance('/api/supers/teacher', 'post', playload)

export const deleteTeacher = async (playload = {}) =>
  await axiosInstance('/api/supers/teacher', 'delete', playload)
