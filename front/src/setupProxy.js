const { createProxyMiddleware } = require('http-proxy-middleware');

// 나중에 베포때는 target이랑 router url변경 해야할거같음
module.exports = app => {
  app.use(
    createProxyMiddleware(['/api', '/ws-stomp'], {
      target: 'http://localhost:8080',
      changeOrigin: true,
      ws: true,
      router: {
        '/ws-stomp': 'ws://localhost:8080',
      },
    })
  );
};
