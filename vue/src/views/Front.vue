<template>
  <div>
    <div class="front-notice" @click="showNotice">
      <i class="el-icon-bell" style="margin-right: 2px"></i>查看公告：{{ top }}</div>
    <!--头部-->
    <div class="front-header">
      <div class="front-header-left">
        <img src="@/assets/imgs/logo-r.png" alt="">
        <div class="title" style="color: #1a6840">德馨民宿预订系统</div>
      </div>
      <div class="front-header-center">
        <div class="front-header-nav">
          <el-menu :default-active="$route.path" mode="horizontal" router>
            <el-menu-item index="/front/home" >首页</el-menu-item>
            <el-menu-item index="/front/person" class="show-element">个人中心</el-menu-item>
            <el-menu-item index="/front/collect" class="show-element">我的收藏</el-menu-item>
            <el-menu-item index="/front/orders" class="show-element">我的订单</el-menu-item>
            <el-menu-item index="/front/history" class="show-element">历史入住</el-menu-item>
            <el-submenu index="more" class="hide-element">
              <template slot="title">
                <span v-if="!isMobile">更多</span>
                <i v-if="isMobile" class="el-icon-s-unfold"></i>
              </template>
              <el-menu-item index="/front/home">
                <i class="el-icon-s-home"></i>
                <span>首页</span>
              </el-menu-item>
              <el-menu-item index="/front/person">
                <i class="el-icon-user"></i>
                <span>个人中心</span>
              </el-menu-item>
              <el-menu-item index="/front/collect">
                <i class="el-icon-star-off"></i>
                <span>我的收藏</span>
              </el-menu-item>
              <el-menu-item index="/front/orders">
                <i class="el-icon-s-order"></i>
                <span>我的订单</span>
              </el-menu-item>
              <el-menu-item index="/front/history">
                <i class="el-icon-s-check"></i>
                <span>历史入住</span>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </div>
      </div>
      <div class="front-header-search">
        <el-input   placeholder="请输入民宿名称" v-model="name"></el-input>
        <el-button v-if="!isMobile" type="primary" @click="navToSearch">搜索</el-button>
        <i v-if="isMobile" class="el-icon-search"  @click="navToSearch"></i>
      </div>
      <div class="front-header-right">
        <div v-if="!user.username">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown>
            <div class="front-header-dropdown">
              <img :src="user.avatar || this.$baseUrl + '/files/1611365333853-default.png'" alt="未设置">
              <div class="username-container">
                <span>{{ user.name ||  '默认用户' }}</span>
                <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              </div>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div style="text-decoration: none" @click="logout">退出</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <!--主体-->
    <div class="main-body">
      <router-view ref="child" @update:user="updateUser" />
    </div>
    <el-drawer
        title="官网公告"
        :visible.sync="displayNotice"
        direction="rtl"
        size="50%">
<!--      <el-table :data="notices">
        <el-table-column property="time" label="时间" width="95"></el-table-column>
        <el-table-column property="title" label="标题" show-overflow-tooltip width="125"></el-table-column>
        <el-table-column property="content" label="内容"></el-table-column>
      </el-table>-->
      <div class="official-notice">
      <el-timeline  reverse slot="reference">
        <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time" placement="top">
          <el-card>
            <h4 style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
              <el-popover
                  placement="left"
                  width="200"
                  trigger="hover"
                  :content="item.title">
                <span slot="reference">{{ item.title }}</span>
              </el-popover>
            </h4>
            <p style="margin-top: 15px">
              {{item.content}}
            </p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      </div>
    </el-drawer>
  </div>

</template>

<script>

export default {
  name: "FrontLayout",

  data () {
    return {
      isMobile: false,
      isPad:false,
      top: '',
      displayNotice : false,
      notices: [],
      name:null,
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
    }
  },

  mounted() {
    this.loadNotice()
    window.addEventListener('resize', this.checkScreen)
    this.checkScreen()

  },
  methods: {
    checkScreen() {
      this.isMobile = window.innerWidth < 1060
      this.idPad = window.innerWidth < 1250
    },
    showNotice(){
      this.displayNotice = true
    },
    loadNotice() {
      this.$request.get('/notice/selectAll').then(res => {
        this.notices = res.data
        let i = 0
        if (this.notices && this.notices.length) {
          this.top = this.notices[0].title
          setInterval(() => {
            this.top = this.notices[i].title
            i++
            if (i === this.notices.length) {
              i = 0
            }
          }, 2500)
        }
      })
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    // 退出登录
    logout() {
      localStorage.removeItem("xm-user");
      this.$router.push("/login");
    },
    navToSearch() {
      location.href = '/front/search?name=' + this.name
    }
  }

}
</script>

<style scoped>
  @import "~@/assets/css/front.css";
</style>