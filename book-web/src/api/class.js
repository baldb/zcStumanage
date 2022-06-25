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

// 根据班级id查找班级学生
export const getcalssstu = async (playload = {}) =>
  await axiosInstance('/api/supers/getclassstu', 'get', playload)

// 添加学生到班级
export const addcalssstu = async (playload = {}) =>
  await axiosInstance('/api/supers/addclassstu', 'post', playload)

// 移除该班学生
export const delcalssstu = async (playload = {}) =>
  await axiosInstance('/api/supers/delclassstu', 'get', playload)

export const noclass = async (playload = {}) =>
  await axiosInstance('/api/supers/noclass', 'get', playload)
