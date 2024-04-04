<template>
  <div class="main-content">
    <div style="width: 60%; margin: 50px auto; text-align: left">
      <div style="margin-top: 30px">
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in homestayData" :key="item.id">
            <img :src="item.avatar" style="width: 100%; height: 175px; border-radius: 10px" alt="" @click="navToDetail(item.id)">
            <div style="font-size: 16px; font-weight: bold; margin-top: 10px; color: #1a6840FF">{{item.name}}</div>
            <div style="margin-top: 10px">
              <span style="font-weight: bold; font-size: 16px; color: crimson">￥{{item.price}}</span> 起
              <span style="font-size: 14px; color: #1a6840FF; margin-left: 20px"><i class="el-icon-chat-line-square"></i> {{item.comNum}}点评</span>
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
    let name = this.$route.query.name
    return {
      name: name,
      homestayData: [],
    }
  },
  mounted() {
    this.loadHomestays()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadHomestays() {
      this.$request.get('/homestay/selectByName?name=' + this.name).then(res => {
        if (res.code === '200') {
          this.homestayData = res.data
          if (this.homestayData.length > 0) {
            this.$message.success('查询成功');
          }else {
            this.$message.warning('没有找到相关民宿');
          }

        } else {
          this.$message.error(res.msg)
        }
      })
    },
    navToDetail(id) {
      location.href = '/front/homestay?id=' + id
    }
  }
}
</script>