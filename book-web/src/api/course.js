import axiosInstance from '@/utils/request'

// 获取老师列表
export const getCourse = async ({ pn = 1, offset = 8 } = {}) =>
  await axiosInstance('/api/supers/course', 'get', { pn, offset })

export const editCourse = async (playload = {}) =>
  await axiosInstance('/api/supers/course', 'put', playload)

export const addCourse = async (playload = {}) =>
  await axiosInstance('/api/supers/course', 'post', playload)

export const deleteCourse = async (playload = {}) =>
  await axiosInstance('/api/supers/course', 'delete', playload)
