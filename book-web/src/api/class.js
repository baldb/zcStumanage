import axiosInstance from '@/utils/request'

// 获取老师列表
export const getClass = async ({ pn = 1, offset = 8 } = {}) =>
  await axiosInstance('/api/supers/class', 'get', { pn, offset })

export const editClass = async (playload = {}) =>
  await axiosInstance('/api/supers/class', 'put', playload)

export const addClass = async (playload = {}) =>
  await axiosInstance('/api/supers/class', 'post', playload)

export const deleteClass = async (playload = {}) =>
  await axiosInstance('/api/supers/class', 'delete', playload)
