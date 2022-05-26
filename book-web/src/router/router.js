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
        children: [
            { path: "", redirect: "home" },
            { path: "home", component: () =>
                    import ("@/views/home/Home") }, ,
            {
                path: "profile",
            },
        ],
    },
    {
        path: "*",
        component: () =>
            import ("@/views/404"),
    },
];

/**
 *  学生页  ->个人信息 -> 请假页 -> 显示同班同学
 * 教师页 -> 显示学生列表 -> 显示请假列表 -> 显示请假详情
 * 管理员页 -> 添加学生 ->删除学生 -> 创建班级 ->添加辅导员 ->添加老师 ->删除老师
 */

const asyncRouter = [{
        path: "student",
        name: "student",
        meta: {
            role: [IDENT_ENUM[0].value, IDENT_ENUM[2].value],
        },
        children: [{
                path: "classmate", // 同班同学
                meta: {
                    role: [IDENT_ENUM[0].value, IDENT_ENUM[2].value],
                },
            },
            {
                path: "askleave", // 请假条
                meta: {
                    role: [IDENT_ENUM[0].value, IDENT_ENUM[2].value],
                },
            },
        ],
    },
    {
        path: "teacher",
        name: "teacher",
        meta: {
            role: [IDENT_ENUM[0].value, IDENT_ENUM[1].value],
        },
        children: [{
                path: "studentsLift", // 学生列表
                meta: {
                    role: [IDENT_ENUM[0].value, IDENT_ENUM[1].value],
                },
            },

            {
                path: "askleaveinfo", // 请假列表
                meta: {
                    role: [IDENT_ENUM[0].value, IDENT_ENUM[1].value],
                },
            },
        ],
    },
    {
        path: "manage",
        name: "manage",
        meta: {
            role: [IDENT_ENUM[0].value],
        },
    },
];

export { constantRouter, asyncRouter };