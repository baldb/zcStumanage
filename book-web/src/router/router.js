import { IDENT_ENUM } from "../constant/auth";

const constantRouter = [{
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
            {
                path: "home",
                component: () =>
                    import ("@/views/home/Home"),
                meta: {
                    role: [IDENT_ENUM[2].value],
                },
            },
        ],
    },
    {
        path: "*",
        component: () =>
            import ("@/views/404"),
    },
];

const asyncRouter = [{}];

export { constantRouter };