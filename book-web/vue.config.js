module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        assets: '@/assets',
      },
    },
  },
  devServer: {
    proxy: {
      '/api': {
        target: process.env.VUE_APP_BASURL,
        // ws: false,
        changeOrigin: true,
        pathRewrite: {
          '^/api': '',
        },
      },
    },
    port: 3000,
  },
}
