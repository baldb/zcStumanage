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
        target: 'http://192.168.137.116:8088',
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
