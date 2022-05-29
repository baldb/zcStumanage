import { IDENT_ENUM } from '../constant/auth'

const constantRouter = [
  {
    path: '/',
    redirect: '/passpot',
  },
  {
    // 登录
    path: '/passpot',
    redirect: '/passpot/login',
    component: () => import('@/layout/passport/PassPort'),
    children: [
      {
        path: 'login',
        component: () => import('@/layout/passport/components/Login'),
        meta: { title: '登录' },
      },
      {
        path: 'forgotpwd',
        component: () => import('@/layout/passport/components/Forgotpwd'),
        meta: { title: '忘记密码' },
      },
    ],
  },

  {
    path: '*',
    component: () => import('@/views/404'),
  },
]

/**
 *  学生页  ->个人信息 -> 请假页 -> 显示同班同学
 * 教师页 -> 显示学生列表 -> 显示请假列表 -> 显示请假详情
 * 管理员页 -> 添加学生 ->删除学生 -> 创建班级 ->添加辅导员 ->添加老师 ->删除老师
 */

const asyncRouter = [
  {
    // 登录成功
    path: '/passed',
    name: 'passed',

    component: () => import('@/layout/default/Main'),
    redirect: '/passed/home',
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/home/Home'),
        meta: { title: '首页', icon: 'el-icon-eleme' },
      },

      {
        path: 'profile',
        name: 'profile',
        component: () => import('@/views/profile/Profile'),
        meta: { title: '个人信息', hide: true },
      },
      {
        path: 'student',
        name: 'student',
        redirect: '/passed/student/classmate', // 定义一个核心容器用来做缓存
        meta: {
          role: [IDENT_ENUM[2].value],
          title: '学生信息',
          icon: 'el-icon-user-solid',
        },
        children: [
          {
            path: 'classmate', // 同班同学
            name: 'classmate',
            meta: { title: '同班同学' },
            component: () => import('@/views/student/Classmate'),
          },
          {
            path: 'askleave', // 请假条
            name: 'askleave',
            component: () => import('@/views/student/Askleave'),
            meta: { title: '请假条', showBadge: true },
          },
        ],
      },
      {
        path: 'teacher',
        name: 'teacher',
        redirect: '/passed/teacher/studentsList',
        meta: {
          role: [IDENT_ENUM[1].value],
          icon: 'el-icon-remove',
          title: '老师信息',
        },
        children: [
          {
            path: 'studentsList', // 学生列表
            name: 'studentsList',
            component: () => import('@/views/teacher/akleaveinfo'),
            meta: { title: '学生列表' },
          },

          {
            path: 'askleaveinfo', // 请假列表
            name: 'askleaveinfo',
            component: () => import('@/views/teacher/StudentsList'),
            meta: { title: '请假列表', showBadge: true },
          },
        ],
      },
      {
        path: 'manage',
        name: 'manage',

        meta: {
          role: [IDENT_ENUM[0].value],
          title: '管理员',
          icon: 'el-icon-remove',
        },
      },
    ],
  },
]

export { constantRouter, asyncRouter }
