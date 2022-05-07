module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://192.168.137.222:8080',
        // ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': '',
        },
      },
    },
    port: 3000,
  },
}
