import Vue from 'vue'
import Vuex from 'vuex'
import * as menuModule from './menu'
import * as userModule from './user'

Vue.use(Vuex)

const store = new Vuex.Store({
  getters: {
    indentity: (state) => state.user.identity,
    isAdmin: (state) => state.user.identity === 'admin',
    getRouter: (state) => state.menu.router,
  },
  modules: {
    menu: menuModule,
    user: userModule,
  },
})

export default store
