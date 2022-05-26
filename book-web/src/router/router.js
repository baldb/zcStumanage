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
      },
      {
        path: 'forgotpwd',
        component: () => import('@/layout/passport/components/Forgotpwd'),
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
    redirect: '/passed/home',
    component: () => import('@/layout/default/Main'),
    children: [
      {
        path: 'home',
        name: 'home',
        component: () => import('@/views/home/Home'),
        meta: { title: '首页', icon: 'el-icon-eleme' },
      },
      ,
      {
        path: 'profile',
        name: 'profile',
        meta: { title: '个人信息', hide: true },
      },
      {
        path: 'student',
        name: 'student',
        component: () => import('@/views/student/Student'),

        meta: {
          role: [IDENT_ENUM[2].value],
          title: '学生信息',
          icon: 'el-icon-user-solid',
        },
        children: [
          {
            path: 'classmate', // 同班同学
            meta: { title: '同班同学' },
            component: () => import('@/views/student/Classmate'),
          },
          {
            path: 'askleave', // 请假条
            component: () => import('@/views/student/Askleave'),
            meta: { title: '请假条' },
          },
        ],
      },
      {
        path: 'teacher',
        name: 'teacher',

        meta: {
          role: [IDENT_ENUM[1].value],
          icon: 'el-icon-remove',
          title: '老师信息',
        },
        children: [
          {
            path: 'studentsList', // 学生列表
            meta: { title: '学生列表' },
          },

          {
            path: 'askleaveinfo', // 请假列表
            meta: { title: '请假列表' },
          },
        ],
      },
      {
        path: 'manage',
        name: 'manage',
        meta: {
          role: [IDENT_ENUM[0].value],
          title: '管理员',
        },
      },
    ],
  },
]

export { constantRouter, asyncRouter }
