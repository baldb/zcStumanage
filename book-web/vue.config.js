module.exports = {
    configureWebpack: {
        resolve: {
            alias: {
                assets: "@/assets",
            },
        },
    },
    devServer: {
        proxy: {
            "/api": {
                target: "http://192.168.137.222:8880",
                // ws: true,
                changeOrigin: true,
                pathRewrite: {
                    "^/api": "",
                },
            },
        },
        port: 3000,
    },
};