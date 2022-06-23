import { IDENT_ENUM } from '../constant/auth'
import { login, loginStu, loginTeacher } from '../api'
import router, { resetRouter } from '../router'
import { setStorage, removeStorage, getStorage } from '../utils/storageUtil'
const namespaced = true

const state = () => ({
  username: '',
  identity: '',
  userinfo: null,
})

const mutations = {
  setUserInfo(state, payload) {
    state.identity = IDENT_ENUM[payload.identity]?.value ?? null
    state.username = payload.userName ?? null
  },
  // 设置学生信息
  setUIN(state, payload) {
    state.userinfo = payload
  },
}

const actions = {
  async login({ commit, dispatch }, userinfo) {
    const { status, resultSet } = await login(userinfo)
    if (status !== 200) return dispatch('logout')
    setStorage('userinfo', resultSet)
    commit('setUserInfo', resultSet)
    dispatch('getUserInfo')
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

  // 获取用户详细信息
  async getUserInfo({ commit, rootGetters }) {
    let resultSet
    if (rootGetters.indentity === 'student') {
      ;({ resultSet } = await loginStu({ stuId: rootGetters.username }))
    } else if (rootGetters.indentity === 'teacher') {
      ;({ resultSet } = await loginTeacher({ teacherId: rootGetters.username }))
    }
    if (resultSet) {
      commit('setUIN', resultSet)
    }
  },

  // 获取用户
  async getUser({ commit, dispatch }) {
    const userinfo = getStorage('userinfo')
    if (!userinfo) return null
    commit('setUserInfo', userinfo)
    dispatch('getUserInfo')
    return userinfo
  },
}

export { namespaced, state, mutations, actions }
