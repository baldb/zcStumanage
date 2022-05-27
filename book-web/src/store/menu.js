import { asyncRouter, constantRouter } from '../router'
import { IDENT_ENUM } from '../constant/auth'

const namespaced = true

const hashpermissionRoute = (route, indentity) => {
  if (
    indentity === IDENT_ENUM[0].value ||
    !route.meta?.role ||
    route.meta.role?.includes(indentity)
  ) {
    return true
  }
  return false
}

// 过滤权限
const filterAsyncRouter = (router, indentity, basePath = '') => {
  const accessedRouters = router.filter((route) => {
    Object.assign(route, { path: basePath + route.path })

    // 判断权限是否存在
    if (hashpermissionRoute(route, indentity)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(
          route.children,
          indentity,
          route.path + '/'
        )
      }
      return true
    }
  })
  return accessedRouters
}

// 设置状态
const state = () => ({
  router: constantRouter,
  accessRouter: [],
  activeMenu: '',
  tagList: [],
})

const mutations = {
  SET_ROUTER(state, router) {
    // 设置路由
    state.router = constantRouter.concat(router)
    // 设置显示的路由
    state.accessRouter = router?.[0]?.children ?? []
  },

  // 顺便添加tag缓存
  SET_AVTIVE_MENU(state, route) {
    if (!state.tagList.find((i) => i.path === route.path)) {
      state.tagList.push(route)
    }
    state.activeMenu = route
  },

  // 移除tag
  REMOVE_TAG(state, path) {
    const idx = state.tagList.findIndex((i) => i.path === path)

    if (!~idx) return

    if (state.activeMenu.path === path) {
      // 如果删除的当前的就往后移动
      state.activeMenu = state.tagList[idx + 1] ?? state.tagList[0]
    }
    state.tagList.splice(idx, 1)
  },
}

// 提取身份权限路由
const actions = {
  getAllowRoute({ commit, rootGetters }) {
    return new Promise((resolve, reject) => {
      // 先赋值所有权限
      let allowRoute = asyncRouter
      // 设置权限
      allowRoute = filterAsyncRouter(allowRoute, rootGetters.indentity)
      // 刷新权限
      commit('SET_ROUTER', allowRoute)

      resolve(rootGetters.getRouter)
    })
  },
}

export { namespaced, state, mutations, actions }
