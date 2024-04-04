<template>
  <div class="manager-container">
    <!--  头部  -->
    <div class="manager-header">
      <div class="manager-header-left">
        <img src="@/assets/imgs/logo-r.png"  alt=""/>
        <div class="title">民宿后台管理</div>
      </div>

      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/manager/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">{{ this.$route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="manager-header-right">
        <el-dropdown placement="bottom">
          <div class="avatar">
            <img :src="user.avatar || this.$baseUrl + '/files/1611365333853-default.png' || ''" alt="未设置"/>
            <div>{{ user.name ||  '默认用户' }}</div>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/manager/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!--  主体  -->
    <div class="manager-main">
      <!--  侧边栏  -->
      <div class="manager-main-left">
        <el-menu :default-openeds="['info', 'chat' ,'user']" router style="border: none" :default-active="$route.path">
          <el-menu-item index="/manager/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
          <el-submenu index="chat" v-if="user.role === 'ADMIN'|| homestayData.status === '审核通过'">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span>聊天室</span>
            </template>
              <el-menu-item index="/manager/imSingle">
                <i class="el-icon-chat-round"></i>
                <span slot="title">我的消息</span>
              </el-menu-item>
          </el-submenu>
          <el-submenu index="info">
            <template slot="title">
              <i class="el-icon-menu"></i><span>信息管理</span>
            </template>
            <el-menu-item v-if="user.role === 'ADMIN'" index="/manager/notice">公告信息</el-menu-item>
            <el-menu-item index="/manager/type">房间分类</el-menu-item>
            <el-menu-item index="/manager/room">客房信息</el-menu-item>
            <el-menu-item index="/manager/orders">订单信息</el-menu-item>
            <el-menu-item index="/manager/checkin">入住登记</el-menu-item>
            <el-menu-item index="/manager/comment">评论管理</el-menu-item>

          </el-submenu>

          <el-submenu index="user" v-if="user.role === 'ADMIN'">
            <template slot="title">
              <i class="el-icon-menu"></i><span>用户管理</span>
            </template>
            <el-menu-item index="/manager/admin">管理员信息</el-menu-item>
            <el-menu-item index="/manager/homestay">民宿信息</el-menu-item>
            <el-menu-item index="/manager/user">用户信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!--  数据表格  -->
      <div class="manager-main-right">
        <router-view @update:user="updateUser" />
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "ManagerLayout",
  data() {
    return {
      homestayData: [],
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },

  created() {
    if (!this.user.id) {
      //this.$router.push('/login')
      this.$router.push('/front/home')
    }
    this.loadHomestayStatus();
  },
  methods: {
    loadHomestayStatus(){
      this.$request.get('/homestay/selectById?id=' + this.user.id).then(res => {
        if (res.code === '200'){
          this.homestayData = res.data
          console.log(this.homestayData)
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/manager/adminPerson')
      }
      if (this.user.role === 'HOMESTAY') {
        this.$router.push('/manager/homestayPerson')
      }
    },
    logout() {
      localStorage.removeItem('xm-user')
      this.$router.push('/login')
    },

  }
}
</script>

<style scoped>
@import '~@/assets/css/manager.css';
</style>