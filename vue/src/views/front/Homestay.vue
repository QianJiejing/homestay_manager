<template>
  <div class="main-content">
    <div style="width: 60%; margin: 20px auto; display: flex">
      <div style="flex: 1">
        <img :src="homestayData.avatar" alt="" style="width: 100%; height: 250px; border-radius: 10px">
      </div>
      <div style="flex: 1; margin-left: 30px">
        <div style="font-size: 18px; font-weight: bold; color: #1a6840FF">{{homestayData.name}}</div>
        <div style="color: #1a6840FF; margin-top: 10px">民宿官网：<a :href="homestayData.url" target="_blank">{{homestayData.url}}</a></div>
        <div style="color: #1a6840FF; margin-top: 10px">民宿电话：{{homestayData.phone}}</div>
        <div style="color: #1a6840FF; margin-top: 10px">民宿邮箱：{{homestayData.email}}</div>
        <div style="color: #1a6840FF; margin-top: 10px">客房价格：<span style="color:crimson">￥{{homestayData.price}}</span> 起</div>
        <div style="color: #1a6840FF; margin-top: 10px; overflow: hidden; text-overflow: ellipsis; display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 5;">民宿介绍：{{homestayData.description}}</div>
      </div>
      <div style="flex: 1"></div>
    </div>
    <div style="width: 65%; margin: 0 auto">
      <el-row :gutter="20">
        <el-col :span="6" v-for="item in typeData" :key="item.id">
          <img :src="item.img" style="width: 100%; height: 180px; border-radius: 10px" alt="" @click="navToDetail(item)">
          <div style="font-size: 16px; margin-top: 10px; color: #1a6840FF">
            <span style="font-weight: bold;">{{item.name}}</span>
            <span style="font-weight: bold; font-size: 16px; color: crimson; margin-left: 10px">￥{{item.price}}</span>
            <span style="font-size: 14px; color: #1a6840FF; margin-left: 20px"><i class="el-icon-s-home"></i> 剩余 {{item.num}} 间</span>
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 侧边栏 -->
    <div class="sidebar">
      <el-button type="text" @click="showMessage" class="sidebar-btn">
        <i class="el-icon-message"></i> 消息
      </el-button>
      <el-button type="text" @click="contactHomestay" class="sidebar-btn">
        <i class="el-icon-service"></i> 联系我们
      </el-button>
      <el-button type="text" @click="backToTop" class="sidebar-btn" v-show="showBackToTop">
        <i class="el-icon-upload2"></i> 回顶部
      </el-button>
    </div>

  </div>
</template>

<script>

export default {

  data() {
    let homestayId = this.$route.query.id
    return {
      homestayData: {},
      typeData: [],
      homestayId: homestayId,
      showBackToTop: false,
    }
  },
  mounted() {
    this.loadHomestays()
    this.loadTypes()
    window.addEventListener('scroll', this.handleScroll); // 监听滚动事件
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll); // 在组件销毁前移除事件监听器
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    handleScroll() {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
      this.showBackToTop = scrollTop > 200; // 当滚动高度超过200时显示回到顶部按钮
    },

    showMessage() {
      this.$router.push({ name: 'FrontImSingle', params: { messageType: 'all' } });
    },
    contactHomestay() {
      this.$router.push({ name: 'FrontImSingle', params: { messageType: 'homestay' } });
    },
    backToTop() {
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    loadHomestays() {
      this.$request.get('/homestay/selectById?id=' + this.homestayId).then(res => {
        if (res.code === '200') {
          this.homestayData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadTypes() {
      this.$request.get('/type/selectByHomestayId?id=' + this.homestayId).then(res => {
        if (res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    navToDetail(item) {
      // if(item.num === 0){
      //   this.$message.warning('该类房型没有空闲房间，请选择其他客房')
      //   return
      // }
      location.href = '/front/detail?id=' + item.id
    }
  }
}

</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 50%;
  right: 5px;
  transform: translateY(-50%);
  width: 100px;
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
  font-size: 14px;
  padding: 8px 0;
}
</style>