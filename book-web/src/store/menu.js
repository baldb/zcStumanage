import { asyncRouter, constantRouter } from "../router";

const namespaced = true;

const hashpermissionRoute = (route, indentity) => {
    if (route.meta && route.meta.role && route.meta.role.includes(indentity)) {
        return true;
    }
    return false;
};

const filterAsyncRouter = (router, indentity) => {
    const accessedRouters = router.filter(route => {
        // 判断权限是否存在
        if (hashpermissionRoute(route, indentity)) {
            if (route.children && route.children.length) {
                route.children = filterAsyncRouter(route.children, indentity);
            }
            return true;
        }
    });
    return accessedRouters;
};

const state = () => ({
    router: constantRouter,
});

const mutations = {
    //
    SET_ROUTER(state, router) {
        // 设置路由
        state.router = router;
    },
};

// 提取身份权限路由
const actions = {
    getAllowRoute({ commit }, indentity) {
        console.log(1);
        return new Promise((resolve, reject) => {
            const allowRoute = filterAsyncRouter(asyncRouter, indentity);

            commit("SET_ROUTER", allowRoute);
            resolve(allowRoute);
        });
    },
};

export { namespaced, state, mutations, actions };