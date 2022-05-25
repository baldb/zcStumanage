import Vue from "vue";
import VueRouter from "vue-router";
import { constantRouter } from "./router";
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

export default router;