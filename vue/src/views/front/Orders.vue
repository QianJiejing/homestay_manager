<template>
  <div class="main-content">
    <div class="table" style="width: 82%; margin: 20px auto">
      <el-table :data="ordersData" stripe>
        <el-table-column label="图片">
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 80px; height: 60px; border-radius: 5px" v-if="scope.row.typeImg"
                        :src="scope.row.typeImg" :preview-src-list="[scope.row.typeImg]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="orderId" label="订单编号" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="userName" label="预订人" show-overflow-tooltip></el-table-column>
        <el-table-column prop="homestayName" label="民宿"></el-table-column>
        <el-table-column prop="typeName" label="客房"></el-table-column>
        <el-table-column prop="time" label="预订时间" width="90"></el-table-column>
        <el-table-column prop="price" label="价格"></el-table-column>
        <el-table-column prop="inTime" label="入住时间" width="90"></el-table-column>
        <el-table-column prop="outTime" label="离开时间" width="90"></el-table-column>
        <el-table-column prop="days" label="入住天数"></el-table-column>
        <el-table-column prop="status" label="订单状态"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <div style="width: 88%;text-align: right">
              <el-button plain type="primary" size="mini" @click="comment(scope.row)" v-if="scope.row.status ==='已退房' ">评价</el-button>
              <el-button plain type="danger" size="mini" @click=del(scope.row.id)>删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination" style="margin-top: 20px">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>
    <el-dialog title="请输入评价" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" v-model="form">
        <el-form-item prop="content" label="评价内容">
          <el-input type="textarea" style="overflow-y: auto;" v-model="form.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ordersData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      fromVisible : false,
      form : {},
    }
  },
  mounted() {
    this.loadOrders(1)
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {

    save(){
      if (!this.comment){
        this.$message.warning('请输入评价内容')
        return
      }
      let data = {
        content: this.form.content,
        typeId: this.form.typeId,
        homestayId: this.form.homestayId,
        userId: this.user.id,
        role: this.user.role,
        parentId: 0
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('评价成功')
          this.form = {}
          this.fromVisible = false
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/orders/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.loadOrders(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    comment(row){
      this.fromVisible = true
      this.form = row
    },

    loadOrders(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/orders/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userId: this.user.id,
        }
      }).then(res => {
        if (res.code === '200') {
          this.ordersData = res.data?.list
          this.total = res.data?.total
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleCurrentChange(pageNum) {
      this.loadOrders(pageNum)
    },
  }
}
</script>