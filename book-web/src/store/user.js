import { IDENT_ENUM } from '../constant/auth'

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

const actions = {}

export { namespaced, state, mutations, actions }
