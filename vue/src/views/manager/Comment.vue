<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入要查询的内容" style="width: 200px" v-model="content"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd" v-if="user.role === 'HOMESTAY'">房型评论</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe  @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="content" label="评价内容" show-overflow-tooltip></el-table-column>
        <el-table-column prop="time" label="评价时间" show-overflow-tooltip></el-table-column>
        <el-table-column prop="userName" label="评价人">
          <template slot-scope="scope">
            <span v-if="scope.row.role !== 'HOMESTAY'">{{ scope.row.userName }}</span>
            <span v-else>{{ scope.row.homestayName }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="homestayName" label="所属民宿"></el-table-column>
        <el-table-column prop="typeName" label="所属客房"></el-table-column>

        <el-table-column label="操作" width="180" align-item="center">
          <template v-slot="scope">
            <div style="width: 88%;text-align: right">
              <el-button v-if="user.role === 'HOMESTAY'&& scope.row.role !== 'HOMESTAY'" plain type="primary" size="mini" @click=replyComment(scope.row)>回复</el-button>
              <el-button plain type="danger" size="mini" @click=del(scope.row.id)>删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
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
    <!-- 回复对话框 -->
    <el-dialog title="回复评价" :visible.sync="showReplyDialog" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" v-model="replyForm">
        <el-form-item prop="replyContent" label="回复内容">
          <el-input type="textarea" style="overflow-y: auto;" v-model="replyForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showReplyDialog = false">取 消</el-button>
        <el-button type="primary" @click="saveReply(replyForm.parentId,replyForm.content)">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 房型评论对话框 -->
    <el-dialog title="新增房型评论" :visible.sync="showRoomCommentDialog" width="30%">
      <!-- 房型评论表单内容 -->
      <el-form label-width="100px" style="padding-right: 50px" v-model="commentForm">
        <el-form-item prop="commentContent" label="房型评价">
          <el-input type="textarea" v-model="commentForm.content" autosize></el-input>
        </el-form-item>
        <el-form-item prop="typeId" label="客房分类">
          <el-select v-model="commentForm.typeId" placeholder="请选择分类" style="width: 100%">
            <el-option v-for="item in typeData" :label="item.name" :value="item.id" :key="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showRoomCommentDialog = false">取消</el-button>
        <el-button type="primary" @click="saveRoomComment">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Comment",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      content: null,
      typeData:[],
      replyForm : {},
      commentForm : {},
      showReplyDialog : false,
      showRoomCommentDialog:false,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: [],
    }
  },
  created() {
    this.load(1)
    this.loadTypes()
  },
  methods: {
    saveRoomComment(){
      if (!this.commentForm.content){
        this.$message.warning('请输入评价内容')
        return
      }
      let data = {
        content: this.commentForm.content,
        typeId: this.commentForm.typeId,
        homestayId: this.user.id,
        userId: 0,
        role: this.user.role,
        parentId: 0
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('评价成功')
          this.commentForm = {}
          this.showRoomCommentDialog = false
          this.load(1)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    saveReply(parentId, replyContent){
      if (!replyContent){
        this.$message.warning('请输入回复内容')
        return
      }
      let data = {
        content: replyContent,
        typeId: this.replyForm.typeId,
        homestayId: this.replyForm.homestayId,
        userId: this.replyForm.userId,
        role: this.user.role,
        parentId: parentId
      }
      console.log(data);
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('回复成功')
          this.form = {}
          this.showReplyDialog = false
          this.load(1)
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    replyComment(row) {
      // 初始化 replyForm 对象
      this.replyForm = {
        parentId: row.id,
        content: '', // 确保回复内容为空
        typeId: row.typeId,
        homestayId: row.homestayId,
        userId: row.userId
      };
      //console.log(this.replyForm)
      // 打开对话框
      this.showReplyDialog = true;
    },

    handleAdd(){
      //this.commentForm = {}  // 新增数据的时候清空数据
      this.showRoomCommentDialog = true   // 打开弹窗
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/comment/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },

    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/comment/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/comment/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          content: this.content,
        }
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data?.list
          this.total = res.data?.total
          //console.log(this.tableData)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadTypes() {
      // 查询当前民宿所有的房间类型
      this.$request.get('/type/selectByHomestayId?id=' + this.user.id).then(res => {
        if (res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    reset() {
      this.content = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>