import Vue from 'vue'
import VueRouter from 'vue-router'

// 1.安装插件
Vue.use(VueRouter)

// 2.创建router
const routes = [
  {
    path: '',
    redirect: '/passed',
  },
  {
    path: '/passpot',
    component: () => import('@/layout/passport/PassPort'),
    children: [
      {
        path: '',
        redirect: 'login',
      },
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
    path: '/passed',
    component: () => import('@/layout/default/Main'),
  },
  {
    path: '*',
    component: () => import('@/views/404'),
  },
]
const router = new VueRouter({
  routes,
  // mode: 'hash'

  mode: 'history',
  // base:'/'
})

export default router
