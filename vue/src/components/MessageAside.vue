<template>
  <!-- 侧边栏 -->
  <div class="sidebar" v-show="showSidebar">
    <el-button type="text" @click="showMessage" class="sidebar-btn">
      <i class="el-icon-message"></i> 消息
    </el-button>
    <el-button type="text" @click="contactAdmin" class="sidebar-btn" v-show="showContactAdmin">
      <i class="el-icon-service"></i> 联系管理员
    </el-button>
    <el-button type="text" @click="contactHomestay" class="sidebar-btn" v-show="showContactHomestay">
      <i class="el-icon-service"></i> 联系我们
    </el-button>
    <el-button type="text" @click="backToTop" v-show="showBackToTop" class="sidebar-btn">
      <i class="el-icon-upload2"></i> 回顶部
    </el-button>
  </div>
</template>

<script>
export default {
    name: "MessageAside",
    props: {
      showContactHomestay: {
        type: Boolean,
        default: false
      },
      showContactAdmin: {
        type: Boolean,
        default: false
      },
      showBackToTop: {
        type: Boolean,
        default: false
      },
      showSidebar: {
        type: Boolean,
        default: false
      },
    },
  methods: {
    showMessage() {
      this.$emit('showMessage');
    },
    contactHomestay() {
      this.$emit('contactHomestay');
    },
    contactAdmin() {
      this.$emit('contactAdmin');
    },
    backToTop() {
      this.$emit('backToTop');
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
</style>