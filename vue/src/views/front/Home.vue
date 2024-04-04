<template>
  <div class="container">
    <div class="head-title">旅行订民宿，就用德馨.</div>
    <div>
      <img src="@/assets/imgs/carousel.jpg" style="width:100% ; height: 240px; border-bottom-left-radius: 40% ; border-bottom-right-radius: 40%" alt="">
    </div>
    <div class="background">
      <!-- 左侧背景 -->
      <div class="left-bg"></div>
      <!-- 中间内容区域 -->
      <div class="main-content">

        <div style="height: 100px;width: 76%;margin:10px auto;text-align: center">
          <div class="box-title">民宿服务升级</div>
          <div style="display: flex">
            <div style="flex: 1">
              <img src="@/assets/imgs/icon-1.png" style="width: 50px;height: 50px" alt="">
              <div class="d-content">知名民宿，客房丰富，多样选择</div>
            </div>

            <div style="flex: 1">
              <img src="@/assets/imgs/icon-2.png" style="width: 50px;height: 50px" alt="">
              <div class="d-content">专业预订，干净舒适，安心入住</div>
            </div>

            <div style="flex: 1">
              <img src="@/assets/imgs/icon-3.png" style="width: 50px;height: 50px" alt="">
              <div class="d-content">安全预订，贴心服务，舒适体验</div>
            </div>

          </div>
        </div>

        <div style="width: 78%;margin:80px auto auto auto;;text-align: left">
          <div class="box-title">平台优质民宿</div>
          <div style="margin-top: 20px">
            <el-row :gutter="20">
              <el-col :span="6" style="margin-top: 10px" v-for="(item, index) in homestayData" :key="'homestay_' + item.id + '_' + index" v-if="item.status === '审核通过'">
              <img :src="item.avatar" style="width: 100%;height: 150px ;border-radius: 10px" alt="" @click="navToHomestayDetail(item.id)">
                <div style="display: flex;align-items: baseline;font-size: 16px;font-weight: bold;margin-top: 10px;margin-left: 10px;color: #1a6840FF">
                  <i style="margin-right: 2px" class="fas fa-home-user"></i>{{item.name}}</div>
                <div style="margin-top: 5px;margin-left: 10px;">
                  <span style="font-size: 16px;font-weight: bold;margin-top: 10px;color: crimson;flex-grow: 1;">￥{{item.price}}</span> 起
                  <span style="font-size: 14px;color:#1a6840FF ;margin-left: 20px;flex-grow: 1;"><i class="el-icon-chat-line-square"> {{item.comNum}}点评</i></span>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
        <!-- 猜你喜欢 -->
        <div style="width: 78%;margin:40px auto;text-align: left">
          <div class="box-title">平台个性推荐</div>
          <div style="margin-top: 20px">
            <el-row :gutter="20">
              <el-col :span="6" style="margin-top: 10px" v-for="(item, index) in recommendData" :key="'recommend_' + item.id + '_' + index">
              <img :src="item.img" style="width: 100%;height: 150px ;border-radius: 10px" alt="" @click="navToTypeDetail(item.id)">
                <div style="display: flex;font-size: 16px;font-weight: bold;margin-top: 10px;margin-left: 10px;color: #1a6840FF">
                  <span style="flex-grow: 1;">{{item.name}}</span>
                  <span style="flex-grow: 1;"><i style="margin-right: 2px" class="fas fa-home-user"></i>{{item.homestayName}}</span>
                </div>

                <div style="display: flex;align-items: baseline;margin-left: 10px;">
                  <span style=";flex-grow: 1;font-size: 16px;font-weight: bold;margin-top: 5px;color: crimson">￥{{item.price}}</span>
                  <span style=";flex-grow: 1;font-size: 14px;color:#1a6840FF"><i class="el-icon-s-home"> 剩余 {{item.num}} 间</i></span>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
      <!-- 右侧背景 -->
      <div class="right-bg"></div>
    </div>
    <!-- 侧边栏 -->
    <div class="sidebar" v-show="showSidebar">
      <el-button type="text" @click="showMessage" class="sidebar-btn">
        <i class="el-icon-message"></i> 消息
      </el-button>
      <el-button type="text" @click="contactAdmin" class="sidebar-btn">
        <i class="el-icon-service"></i> 联系管理员
      </el-button>
      <el-button type="text" @click="backToTop" v-show="showBackToTop" class="sidebar-btn">
        <i class="el-icon-upload2"></i> 回顶部
      </el-button>
    </div>

  </div>

</template>

<script>

export default {

  data() {
    return {
      showBackToTop: false,
      showSidebar: false, // 控制侧边栏显示与隐藏
      homestayData:[],
      recommendData:[],
    }
  },
  mounted() {
    this.loadHomestays();
    this.loadRecommend();
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    showMessage() {
      // 跳转到 imSingle 页面，并传递消息类型参数为 'all'
      this.$router.push({ name: 'FrontImSingle', params: { messageType: 'all' } });
    },
    contactAdmin() {
      // 跳转到 imSingle 页面，并传递消息类型参数为 'admin'
      this.$router.push({ name: 'FrontImSingle', params: { messageType: 'admin' } });
    },
    backToTop() {
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    handleScroll() {
      // 检测滚动位置，显示或隐藏“回顶部”按钮
      this.showBackToTop = window.scrollY > 240;
      // 滚动到一定程度显示侧边栏
      this.showSidebar = window.scrollY > 50;
    },
    loadHomestays(){
      this.$request.get('/homestay/selectByName?name=null').then(res => {
        if (res.code === '200'){
          this.homestayData = res.data
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    loadRecommend(){
      this.$request.get('/type/recommend').then(res => {
        if (res.code === '200'){
          this.recommendData = res.data
        }else {
          this.$message.error(res.msg)
        }

      })
    },
    navToHomestayDetail(id) {
      location.href = '/front/homestay?id=' + id
    },
    navToTypeDetail(id) {
      location.href = '/front/detail?id=' + id
    }
  }
}
</script>

<style scoped>
.container {
  position: relative;
}
.container .head-title {
  position: absolute;
  top: 7%; /* 垂直 */
  left: 26%; /* 水平居中 */
  /* 使用transform来将标题水平垂直居中 */
  transform: translate(-50%, -50%);
  font-size: 40px;
  font-weight: bold;
  font-style: italic;
  color: whitesmoke;
}
.container .d-content{
  font-size: 16px;
  margin-top: 10px;
  color: #1a6840FF
}
.background {
  padding-bottom: 15px;
  background: linear-gradient(to right, #e4e0df 0%, #e4e0df 10%, #ffffff 10%, #ffffff 90%, #e4e0df 90%, #e4e0df 100%);
}
.left-bg, .right-bg {
  position: absolute;
  top: 0;
  width: 10%;
  height: 100%;
  background-color: #e4e0df;
  z-index: -1;
}
.left-bg {
  left: 0;
}
.right-bg {
  right: 0;
}
.main-content {
  width: 85%;
}
.main-content .box-title{
  text-align: center;
  font-size: 25px;
  font-weight: bold;
  margin:20px auto;
}
.sidebar {
  position: fixed;
  top: 50%;
  right: 5px;
  transform: translateY(-50%);
  width: 110px;
  padding: 5px 0;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 999;
}
.sidebar-btn {
  display: block;
  text-align: left;
  margin: 0 10px;
  font-size: 14px; /* 设置字体大小 */
  padding: 8px 0; /* 调整按钮的内边距 */
}
.sidebar-btn:hover{
  color: #409EFF;
}
@media screen and (max-width: 1060px) {
  .container .head-title {
    top: 5%; /* 垂直 */
    left: 50%; /* 水平居中 */
    font-size: 32px; /* 默认字体大小 */
  }
  .container .d-content{
    font-size: 14px;
  }
  .main-content {
    width: 85%; /* 调整内容区域宽度 */
  }
  .main-content .box-title{
    font-size: 22px;
  }
  .left-bg,
  .right-bg {
    width: 10%; /* 调整背景宽度 */
  }
  .sidebar {
    right: 15px; /* 调整侧边栏位置 */
  }
  .el-col {
    width: 50%; /* 将卡片宽度调整为50% */
  }
}

@media screen and (max-width: 450px) {
  .container .head-title {
    top: 3%; /* 垂直 */
    left: 55%; /* 水平居中 */
    font-size: 25px; /* 默认字体大小 */
  }
  .container .d-content{
    font-size: 10px;
  }
  .main-content {
    width: 85%; /* 调整内容区域宽度 */
  }
  .main-content .box-title{
    font-size: 18px;
  }
  .el-col {
    width: 100%; /* 将卡片宽度调整为100% */
  }
}
</style>
