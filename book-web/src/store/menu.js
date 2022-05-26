import { asyncRouter, constantRouter } from '../router'

const namespaced = true

const hashpermissionRoute = (route, indentity) => {
  if (!route.meta?.role || route.meta.role?.includes(indentity)) {
    return true
  }
  return false
}

const filterAsyncRouter = (router, indentity) => {
  const accessedRouters = router.filter((route) => {
    // 判断权限是否存在
    if (hashpermissionRoute(route, indentity)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, indentity)
      }
      return true
    }
  })
  return accessedRouters
}

const state = () => ({
  router: constantRouter,
})

const mutations = {
  //
  SET_ROUTER(state, router) {
    // 设置路由
    state.router = constantRouter.concat(router)
  },
}

// 提取身份权限路由
const actions = {
  getAllowRoute({ commit, rootGetters }) {
    return new Promise((resolve, reject) => {
      // 先赋值所有权限
      let allowRoute = asyncRouter
      // 判断是不是超级管理员
      if (!rootGetters.isAdmin) {
        allowRoute = filterAsyncRouter(allowRoute, rootGetters.indentity)
      }
      // 刷新权限
      commit('SET_ROUTER', allowRoute)

      resolve(rootGetters.getRouter)
    })
  },
}

export { namespaced, state, mutations, actions }
