import { IDENT_ENUM } from '../constant/auth'
import { login } from '../api'
import router, { resetRouter } from '../router'
import { setStorage, removeStorage, getStorage } from '../utils/storageUtil'
const namespaced = true

const state = () => ({
  username: '',
  identity: '',
})

const mutations = {
  setUserInfo(state, payload) {
    state.identity = IDENT_ENUM[payload.identity]?.value ?? null
    state.username = payload.userName ?? null
  },
  // 获取用户
}

const actions = {
  async login({ commit, dispatch }, userinfo) {
    const {
      res: { status, resultSet },
    } = await login(userinfo)

    if (status !== 200) return dispatch('logout')
    setStorage('userinfo', resultSet)
    commit('setUserInfo', resultSet)
  },

  // 退出登录重置路由
  async logout({ commit }) {
    removeStorage('userinfo')
    commit('setUserInfo', {})
    commit('menu/SET_ROUTER', null, { root: true })
    commit('menu/SET_ROUTERS', null, { root: true })
    commit('menu/SET_AVTIVE_MENU', null, { root: true })
    resetRouter()
  },

  // 获取用户
  async getUser({ commit }) {
    const userinfo = getStorage('userinfo')
    if (!userinfo) return null
    commit('setUserInfo', userinfo)
    return userinfo
  },
}

export { namespaced, state, mutations, actions }
