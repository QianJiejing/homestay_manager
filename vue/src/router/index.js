import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/front/home',  // 重定向到主页
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' , requiresAuth: true }, component: () => import('../views/manager/Admin') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '管理员个人信息' , requiresAuth: true }, component: () => import('../views/manager/AdminPerson') },
      { path: 'homestayPerson', name: 'HomestayPerson', meta: { name: '民宿个人信息' , requiresAuth: true }, component: () => import('../views/manager/HomestayPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' , requiresAuth: true }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告信息' , requiresAuth: true }, component: () => import('../views/manager/Notice') },
      { path: 'homestay', name: 'Homestay', meta: { name: '民宿信息' , requiresAuth: true }, component: () => import('../views/manager/Homestay') },
      { path: 'user', name: 'User', meta: { name: '用户信息' , requiresAuth: true  }, component: () => import('../views/manager/User') },
      { path: 'type', name: 'Type', meta: { name: '分类信息' , requiresAuth: true }, component: () => import('../views/manager/Type') },
      { path: 'room', name: 'Room', meta: { name: '客房信息' , requiresAuth: true }, component: () => import('../views/manager/Room') },
      { path: 'orders', name: 'Orders', meta: { name: '订单信息' , requiresAuth: true }, component: () => import('../views/manager/Orders') },
      { path: 'checkin', name: 'Checkin', meta: { name: '入住登记' , requiresAuth: true }, component: () => import('../views/manager/Checkin') },
      { path: 'comment', name: 'Comment', meta: { name: '评论管理' , requiresAuth: true }, component: () => import('../views/manager/Comment') },
      { path: 'imSingle', name: 'ImSingle', meta: { name: '我的消息' , requiresAuth: true }, component: () => import('../views/manager/ImSingle') },
    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      { path: 'home', name: 'FrontHome', meta: { name: '系统首页' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'Person', meta: { name: '个人信息' , requiresAuth: true }, component: () => import('../views/front/Person') },
      { path: 'homestay', name: 'FrontHomestay', meta: { name: '民宿信息' }, component: () => import('../views/front/Homestay') },
      { path: 'detail', name: 'Detail', meta: { name: '客房信息' }, component: () => import('../views/front/Detail') },
      { path: 'collect', name: 'Collect', meta: { name: '收藏信息' , requiresAuth: true }, component: () => import('../views/front/Collect') },
      { path: 'orders', name: 'FrontOrders', meta: { name: '订单信息' , requiresAuth: true }, component: () => import('../views/front/Orders') },
      { path: 'history', name: 'History', meta: { name: '历史入住' , requiresAuth: true }, component: () => import('../views/front/History') },
      { path: 'search', name: 'Search', meta: { name: '搜素信息' }, component: () => import('../views/front/Search') },
      { path: '/front/imSingle/:messageType', /*添加带参数的路径*/name: 'FrontImSingle', meta: { name: '消息' , requiresAuth: true }, component: () => import('../views/front/ImSingle'),},
    ]
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router
// 注：不需要前台的项目，可以注释掉该路由守卫
// 路由守卫
/*router.beforeEach((to, from, next) => {
  let user;
  try {
    // 使用 try/catch 块来处理可能出现的异常
    user = JSON.parse(localStorage.getItem("xm-user") || '{}');
  } catch (e) {
    // 如果解析失败，设置 user 为一个空对象
    user = {};
  }
  let alreadyRedirected = false; // 用于跟踪是否已经重定向过

  // 用户未登录且访问需要认证的路由
  if (to.meta.requiresAuth && !user) {
    // 直接重定向到登录页面
    if (!alreadyRedirected) {
      next('/login');
      alreadyRedirected = true;
    }
  } else if (to.matched.length === 0) {
    // 如果没有匹配的路由，则认为是404
    if (!alreadyRedirected) {
      next({ name: 'NotFound', params: { pathMatch: to.path } });
      alreadyRedirected = true;
    }
  } else {
    // 检查用户角色
    if (user && user.role) {
      // 根据用户角色限制访问
      if (user.role === 'USER' && to.path.startsWith('/front')) {
        // USER 用户尝试访问后台路径，重定向到前台主页
        if (!alreadyRedirected) {
          next('/front/home');
          alreadyRedirected = true;
        }
      } else if (user.role === 'ADMIN' || user.role === 'HOMESTAY' && to.path.startsWith('/front')) {
        // ADMIN 或 HOMESTAY 用户尝试访问前台路径，重定向到后台主页
        if (!alreadyRedirected) {
          next('/home');
          alreadyRedirected = true;
        }
      } else {
        // 用户已登录，且路由不需要认证，或者用户有权限访问，直接放行
        next();
      }
    } else {
      // 用户已登录或者路由不需要认证，直接放行
      next();
    }
  }
});*/












// if (to.path === '/') {
//   if (user.role) {
//     if (user.role === 'USER') {
//       next('/front/home')
//     } else {
//       next('/home')
//     }
//   } else {
//     next('/login')
//   }
// } else {
//   next()
// }