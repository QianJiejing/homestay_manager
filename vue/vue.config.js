const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  devServer: {
    port: 8080,
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
    proxy: {
      '/ws': {  // 匹配需要代理的 WebSocket 路径
          // WebSocket 服务器地址
          target: 'http://localhost:8080',
          ws: false,
          // 设置 changeOrigin 为 true，解决跨域问题
          changeOrigin: true,
          pathRewrite: {
              '^/ws': ''
          }
      },
    },

  },
  chainWebpack: config =>{
    config.plugin('html')
        .tap(args => {
          args[0].title = "德馨民宿预订系统";
          return args;
        })
  }
})
