import Vue from "vue";
import VueRouter from "vue-router";

// 1.安装插件
Vue.use(VueRouter);

// 2.创建router
const routes = [{
        path: "",
        redirect: "/passpot",
    },
    {
        // 登录
        path: "/passpot",
        component: () =>
            import ("@/layout/passport/PassPort"),
        children: [{
                path: "",
                redirect: "login",
            },
            {
                path: "login",
                component: () =>
                    import ("@/layout/passport/components/Login"),
            },
            {
                path: "forgotpwd",
                component: () =>
                    import ("@/layout/passport/components/Forgotpwd"),
            },
        ],
    },
    {
        // 登录成功
        path: "/passed",
        component: () =>
            import ("@/layout/default/Main"),
        children: [{
                path: "",
                redirect: "home",
            },
            { path: "home", component: () =>
                    import ("@/views/home/Home") },
        ],
    },
    {
        path: "*",
        component: () =>
            import ("@/views/404"),
    },
];
const router = new VueRouter({
    routes,
    // mode: 'hash'

    mode: "history",
    // base:'/'
});

export default router;