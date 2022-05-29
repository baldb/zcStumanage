import Vue from 'vue'
import VueRouter from 'vue-router'
import { constantRouter, asyncRouter } from './router'
import store from '../store'
import { isArrayEmpty } from '../utils/validate'
import { removeStorage } from '../utils/storageUtil'

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

  router.matcher = newRouter.matcher
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
  setTitle(to.meta.title)
  // 获取用户
  store.dispatch('user/getUser')
  // 获取权限
  let identity = store.getters.indentity

  if (to.fullPath.startsWith('/passpot')) {
    if (!!identity) await store.dispatch('user/logout')
    next()
    return
  }

  // 判断用户身份
  if (!identity) return next('/')

  // 判断有没有权限
  if (isArrayEmpty(store.getters.getAccessRouter)) {
    const allowRoute = await store.dispatch('menu/getAllowRoute')

    router.addRoutes(allowRoute)

    // 拦截跳转
    return next({ ...to, replace: true })
  } else {
    next()
  }
})

export { constantRouter, asyncRouter, resetRouter }

export default router
