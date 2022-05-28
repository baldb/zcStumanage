import Vue from 'vue'
import VueRouter from 'vue-router'
import { constantRouter, asyncRouter } from './router'
import store from '../store'

// 1.安装插件
Vue.use(VueRouter)

// 2.创建router
const createRouter = () =>
  new VueRouter({
    routes: constantRouter,
    mode: 'history',
    // base:'/'
  })

// 重置路由
const resetRouter = () => {
  const newRouter = createRouter()

  router.matcher = newRouter.matcher // reset router
}

const setTitle = (title) => {
  if (title) {
    document.title = title
  } else {
    document.title = '管理系统'
  }
}

// 创建路由
const router = createRouter()

router.beforeEach(async (to, form, next) => {
  // 跳转路由判断
  // 如果是/ 就让他通过

  setTitle(to.meta.title)

  if (to.fullPath.startsWith('/passpot')) return next()
  //TODO 获取用户 ---待做
  const indentity = store.getters.indentity

  // 判断用户身份
  if (indentity) {
    next()
  } else {
    store.commit('user/setIdentity', 'student')
    // 获取权限路由
    const allowRoute = await store.dispatch('menu/getAllowRoute')

    router.addRoutes(allowRoute)
    // 拦截跳转
    next({ ...to, replace: true })
  }
})

export { constantRouter, asyncRouter }

export default router
