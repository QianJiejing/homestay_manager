import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
};

/**
用户user.role={
  // 管理员
  ADMIN,
  // 民宿
  HOMESTAY,
  // 用户
  USER,
}
*/
const routes = [
  {
    path: '/manager',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/manager/home',  // 重定向到首页
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' , }, component: () => import('../views/manager/Admin') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '管理员个人信息' , }, component: () => import('../views/manager/AdminPerson') },
      { path: 'homestayPerson', name: 'HomestayPerson', meta: { name: '民宿个人信息' , }, component: () => import('../views/manager/HomestayPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' , }, component: () => import('../views/manager/Password') },
      { path: 'notice', name: 'Notice', meta: { name: '公告信息' , }, component: () => import('../views/manager/Notice') },
      { path: 'homestay', name: 'Homestay', meta: { name: '民宿信息' , }, component: () => import('../views/manager/Homestay') },
      { path: 'user', name: 'User', meta: { name: '用户信息' ,  }, component: () => import('../views/manager/User') },
      { path: 'type', name: 'Type', meta: { name: '分类信息' , }, component: () => import('../views/manager/Type') },
      { path: 'room', name: 'Room', meta: { name: '客房信息' , }, component: () => import('../views/manager/Room') },
      { path: 'orders', name: 'Orders', meta: { name: '订单信息' , }, component: () => import('../views/manager/Orders') },
      { path: 'checkin', name: 'Checkin', meta: { name: '入住登记' , }, component: () => import('../views/manager/Checkin') },
      { path: 'comment', name: 'Comment', meta: { name: '评论管理' , }, component: () => import('../views/manager/Comment') },
      { path: 'imSingle', name: 'ImSingle', meta: { name: '我的消息' , }, component: () => import('../views/manager/ImSingle') },
    ]
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    redirect: '/front/home',  // 重定向到主页
    children: [
      { path: 'home', name: 'FrontHome', meta: { name: '系统首页' }, component: () => import('../views/front/Home') },
      { path: 'person', name: 'Person', meta: { name: '个人信息' , }, component: () => import('../views/front/Person') },
      { path: 'homestay', name: 'FrontHomestay', meta: { name: '民宿信息' }, component: () => import('../views/front/Homestay') },
      { path: 'detail', name: 'Detail', meta: { name: '客房信息' }, component: () => import('../views/front/Detail') },
      { path: 'collect', name: 'Collect', meta: { name: '收藏信息' , }, component: () => import('../views/front/Collect') },
      { path: 'orders', name: 'FrontOrders', meta: { name: '订单信息' , }, component: () => import('../views/front/Orders') },
      { path: 'history', name: 'History', meta: { name: '历史入住' , }, component: () => import('../views/front/History') },
      { path: 'search', name: 'Search', meta: { name: '搜素信息' }, component: () => import('../views/front/Search') },
      { path: '/front/imSingle/:messageType', /*添加带参数的路径*/name: 'FrontImSingle', meta: { name: '消息' , }, component: () => import('../views/front/ImSingle'),},
    ]
  },
  {
    path: '/',
    name: 'Root'
    /*redirect: '/front/home' */  // 将根路径重定向到前台首页
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '/403', name: 'NotAuth', meta: { name: '无权限访问' }, component: () => import('../views/403.vue') },
  { path: '*', redirect: '/404' }, // 将所有未匹配的路由重定向到404页面
  { path: '/404', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 注：不需要前台的项目，可以注释掉该路由守卫
// 路由守卫
router.beforeEach((to, from, next) => {
  const user = JSON.parse(localStorage.getItem('xm-user') || '{}');
  if (!user.token) {//如果用户未登录（游客访问）
    if (to.path === ('/')){
      // 访问的是根路径，则重定向到前台首页
      next('/front/home')
    }else if (to.path.startsWith('/manager')) {
      // 访问的是后台管理路由，则重定向到登录页面
      next('/login');
    }else {// 访问的不是后台管理路由且不是根路径，则认为是游客，可以放行访问公共页面
      next();
    }
  }
    else if (user.token){//如果用户已登录（用户访问）
    if (to.path === ('/')){// 访问的是根路径
     if ( user.role === 'ADMIN' || user.role === 'HOMESTAY' ){
       // 如果用户角色是管理员或民宿，则重定向到后台首页
       next('/manager/home');
     } else if (user.role === 'USER'){
       // 如果用户角色是用户，则重定向到前台首页
       next('/front/home')
     }
    }else if (to.path.startsWith('/manager') && user.role !== 'ADMIN' && user.role !== 'HOMESTAY' ){
      // 如果用户未登录且访问的是前台路由，且角色不是管理员和民宿
      next('/403');
    } else if (to.path === '/manager/homestayPerson' && user.role === 'HOMESTAY') {
      // 如果用户是民宿，但想要访问 '/manager/homestay'，放行
      next();
    } else if ((to.path.startsWith('/manager/notice')
              || to.path.startsWith('/manager/admin')
              || to.path.startsWith('/manager/homestay')
              || to.path.startsWith('/manager/user'))
          && user.role === 'HOMESTAY'){
        // 如果用户是民宿，拦截这些页面
        next('/403');
    }else if (to.path.startsWith('/front') && user.role !== 'USER'){
      // 如果用户未登录且访问的是前台路由，且角色不是用户
      next('/403');
    }else {
      next(); // 角色对应，且不是访问根目录，则继续正常跳转
    }
  }
    else {
      next(); // 其他情况，则继续正常跳转
    }
});

export default router