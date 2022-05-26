import Vue from "vue";
import Vuex from "vuex";
import * as menuModule from "./menu";
import * as userModule from "./user";

Vue.use(Vuex);

const store = new Vuex.Store({
    getters: {
        indentity: state => state.user.identity,
    },
    modules: {
        menu: menuModule,
        user: userModule,
    },
});

export default store;