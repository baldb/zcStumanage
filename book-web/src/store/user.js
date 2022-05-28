import { IDENT_ENUM } from '../constant/auth'
import { login } from '../api'
const namespaced = true

const state = () => ({
  username: 'cxn',
  identity: '',
})

const mutations = {
  setIdentity(state, payload) {
    state.identity = payload
  },
}

const actions = {
  async login({ commit }, userinfo) {
    console.log('userinfo: ', userinfo)
    const currentUser = await login(userinfo)
    console.log(currentUser)
  },
}

export { namespaced, state, mutations, actions }
