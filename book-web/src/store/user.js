import { IDENT_ENUM } from "../constant/auth";

const namespaced = true;

const state = () => ({
    username: "cxn",
    identity: IDENT_ENUM[1].value,
});

const mutations = {};

const actions = {};

const getters = {};

export { namespaced, state, mutations, actions, getters };