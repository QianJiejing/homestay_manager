<template>
  <div class="main-content">
    <div style="width: 85%;margin:20px auto;text-align: left">
      <div style="margin-top: 20px">
        <el-row :gutter="20">
          <el-col :span="6" style="margin-top: 10px" v-for="item in typeData" :key="item.id">
            <img :src="item.img" style="width: 100%;height: 220px ;border-radius: 10px" alt="" @click="navToDetail(item.id)">
            <div style="display: flex;align-items: baseline;font-size: 16px;font-width: bold;margin:10px 0 0 10px;color: #1a6840FF">
              <span style=";flex-grow: 1;">{{item.name}}</span>
              <span style="flex-grow: 1;"><i style="margin-right: 2px" class="fas fa-home-user"></i> {{item.homestayName}}</span>
            </div>

            <div style="display: flex;align-items: baseline">
              <el-col :span="18" style="padding:0 0;display: flex;align-items: baseline">
                  <span style="flex-grow: 1;font-size: 16px;font-weight: bold;margin:10px 0 0 5px;color: crimson">￥{{item.price}}</span>
                  <span style="flex-grow: 1.5;font-size: 14px;color:#1a6840FF"><i class="el-icon-s-home"></i> 剩余 {{item.num}} 间</span>
              </el-col>
              <el-col :span="7" style="display: flex;text-align: right; margin-right: 6px">
                  <el-button type="warning" style="" @click="cancelCollect(item.id)">取消收藏</el-button>
              </el-col>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      typeData: [],

    }
  },
  mounted() {
    this.loadCollect()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadCollect() {
      this.$request.get('/collect/selectOwn?id=' + this.user.id).then(res => {
        if (res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    cancelCollect(typeId) {
      this.$request.delete('/collect/deleteByTypeId?id=' + typeId).then(res => {
        if (res.code === '200') {
          this.$message.success('取消成功')
          this.loadCollect()
        } else {
          this.$message.error(res.msg)
        }
      })
    },


    navToDetail(id) {
      location.href = '/front/detail?id=' + id
    }
  },

}

</script>
<style scoped>
  @media screen and (max-width: 1060px){
    .el-col {
      width: 50%; /* 将卡片宽度调整为50% */
    }
  }
  @media screen and (max-width: 450px){
    .el-col {
      width: 100%; /* 将卡片宽度调整为100% */
    }
  }
</style>