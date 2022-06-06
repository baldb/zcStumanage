import axiosInstance from '@/utils/request'

// 获取老师列表
export const getTeacher = async ({ pn = 1, offset = 8 } = {}) =>
  await axiosInstance('/api/supers/teacher', 'get', { pn, offset })
