import Vue from "vue";
import VueRouter from "vue-router";
import { constantRouter, asyncRouter } from "./router";
import store from "../store";

// 1.安装插件
Vue.use(VueRouter);

// 2.创建router
const createRouter = () =>
    new VueRouter({
        routes: constantRouter,
        // mode: 'hash'

        mode: "history",
        // base:'/'
    });

// 创建路由
const router = createRouter();

router.beforeEach(async(to, form, next) => {
    // 跳转路由判断
    // 如果是/ 就让他通过

    if (to.path === "/" || to.fullPath.startsWith("/passpot")) return next();
    //TODO 获取用户 ---待做
    const indentity = store.getters.indentity;

    // 判断用户身份
    // if (indentity) return next();

    // 获取权限路由
    const allowRoute = await store.dispatch("menu/getAllowRoute", indentity);

    //重新渲染路由
    // router.addRoute("passed", allowRoute);

    // 拦截跳转
    next();
});

export { constantRouter, asyncRouter };

export default router;