<template>
  <div class="main-content">
    <div class="d-info">
      <div style="font-size: 16px;font-weight: 700;color:#46CC04; padding: 15px 20px;border-bottom: 1px solid #eee">详情信息</div>
      <div style="display: flex">
        <div class="info">
          <el-col class="info-img" :span="15" style="height: 430px;">
            <img :src="typeData.img" alt="" style="width: 100%;height: 100%; border-radius: 10px"  >
          </el-col>
          <el-col :span="9.6" style="margin-left: 10px">
            <div style="font-size: 16px;font-weight: bold;margin: 0 15px">基本信息</div>
            <div class="info-container">
              <div style="display: flex;font-size: 14px;margin: 8px 15px ;">
                <span style="flex:1;margin:0 2px 0 0">
                  <i class="el-icon-circle-check"></i>类型：{{typeData.name}}
                </span>
                <span style="flex:1;margin: 0 2px">
                  <i class="el-icon-circle-check"></i>间数：{{typeData.num}}
                </span>
                <span style="flex:1;margin: 0 2px">
                  <i class="el-icon-circle-check"></i>民宿：{{typeData.homestayName}}
                </span>
              </div>

              <div style="font-size: 16px; font-weight: bold; margin: 8px 15px">洗浴用品</div>
              <div style="font-size: 14px; margin: 8px 15px;">
                <div style="margin-top: 5px;display: flex; ">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙刷
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 牙膏
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 沐浴露
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 洗发水
            </span>
                </div>
                <div style="margin-top: 5px;display: flex; ">
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 护发素
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 香皂
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 被子
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 剃须刀
            </span>
                </div>
              </div>

              <div style="font-size: 16px; font-weight: bold; margin: 8px 15px">卫浴设施</div>
              <div style="font-size: 14px; margin: 8px 15px;">
                <div style="margin-top: 5px;display: flex; ">
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 私人浴室
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 私人卫生间
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 吹风机
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 淋浴
            </span>
                </div>
                <div style="margin-top: 5px;display: flex; ">
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 毛巾
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 浴巾
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 拖鞋
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 24H热水
            </span>
                </div>
              </div>

              <div style="font-size: 16px; font-weight: bold; margin: 8px 15px">客房设施</div>
              <div style="font-size: 14px; margin: 8px 15px;">
                <div style="margin-top: 5px;display: flex; ">
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 空调
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 手动窗帘
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 鸭绒被
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 毛毯
            </span>
                </div>
              </div>

              <div style="font-size: 16px; font-weight: bold; margin: 8px 15px">媒体科技</div>
              <div style="font-size: 14px; margin: 8px 15px;">
                <div style="margin-top: 5px;display: flex; ">
            <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 电视机
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 有线频道
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 智能门锁
            </span>
                  <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 电影服务
            </span>
                </div>
                <div style="margin-top: 5px;display: flex; ">
              <span style="flex: 1">
              <i class="el-icon-circle-check"></i> 无线WIFI
            </span>
                </div>
              </div>
              <div style="margin-left: 15px; margin-top: 15px; color: crimson; font-size: 16px">价格：￥ {{typeData.price}} / 晚</div>
              <div style="margin-left: 15px; margin-top: 15px">
                <el-button type="warning" @click="addCollect">加入收藏</el-button>
                <el-button type="success" @click="initDialog">立即预订</el-button>
              </div>
            </div>


          </el-col>

        </div>
      </div>

    </div>
    <!-- 评论信息区域 -->
    <div class="c-info">
      <div style="font-size: 16px;font-weight: 700;color: #409EFF;padding: 6px 0 10px 16px;margin-top:15px;
      border-bottom-left-radius:0.35em;border-bottom: 0.16em solid;border-left: 0.16em solid">用户评论</div>
      <div class="c-info-content">
        <!--评论输入框-->
        <div style="padding-bottom:15px;border-bottom: 1px solid #eee">
          <!-- 评论统计信息 -->
          <div style="font-size: 15px; font-weight: bold; margin:10px 10px">评论<span style="color: #6E77F2; margin-left: 15px">{{commentCount}} 条</span></div>
          <div style="margin: 5px auto; display: flex;align-items: center;">
            <el-col :span="1.5" style="display: flex; margin-right: 5px">
              <img :src="user.avatar" style="height: 50px; width: 50px; border-radius: 50%" alt="">
            </el-col>
            <!-- 表情选择 -->
            <el-col :span="0.5" style="margin-right: 5px">
                <el-popover placement="bottom" trigger="click" width="300">
                  <div class="emoji-box">
                <!-- 表情图标 -->
                <span v-for="(item, index) in emojis" :key="index"
                      style="margin-right: 5px; font-size: 20px; cursor: pointer" v-html="item"
                      @click="clickEmoji(item)">
                </span>
                  </div>
                  <i slot="reference" class="fa fa-smile-o" style="font-size: 22px; color: #909399;margin-top: 5px"></i>
                </el-popover>
            </el-col>
            <!-- 评论输入框 -->
            <el-col :span="22" style="flex-grow: 1;">
              <el-input type="textarea" placeholder="尽情留下你的足迹吧😉" ref="commentInput" v-model="commentForm.content" style="width: 100%;"></el-input>
            </el-col>
          </div>
          <div style="text-align: right;margin-top: 10px"><el-button type="info" @click="Comment">评论</el-button></div>
        </div>
        <!-- 评论展示 -->
        <CommentItem
            :user="user"
            :commentData="commentData"
            :replyData="replyData"
            :replyPageSize="replyPageSize"
            :showAllReplies="showAllReplies"
            @openReplyDialog="openReplyDialog"
            @del="del"
            @toggleReplies="toggleReplies"
        ></CommentItem>
      </div>
    </div>

    <el-dialog title="请选择时间" :visible.sync="fromVisible" :width="dialogWidth" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px">
        <el-form-item prop="inTime" label="入住时间">
          <el-date-picker v-model="inTime" type="date" placeholder="选择日期" value-format="yyyy-MM-dd" style="width: 100%"></el-date-picker>
        </el-form-item>
        <el-form-item prop="outTime" label="离开时间">
          <el-date-picker v-model="outTime" type="date" placeholder="选择日期" value-format="yyyy-MM-dd" style="width: 100%"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
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

    <!-- 回复对话框 -->
    <el-dialog title="回复评价"
               :visible.sync="showReplyDialog"
               :width="dialogWidth"
               :close-on-click-modal="false"
               destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" v-model="replyForm">
        <el-form-item  prop="replyContent" label="回复内容" >
          <div style="display: flex;align-items: center;">
            <el-col :span="0.5" style="flex: 1">
              <el-popover placement="bottom" trigger="click" width="300">
                <div class="emoji-box">
                <span v-for="(item, index) in emojis" :key="index"
                      style="font-size: 20px; cursor: pointer" v-html="item"
                      @click="clickEmoji(item)">
                </span>
                </div>
                <i slot="reference" class="fa fa-smile-o" style="font-size: 22px; color: #909399;margin-top: 5px"></i>
              </el-popover>
            </el-col>
            <el-col :span="22">
            <el-input type="textarea" style="overflow-y: auto;" ref="replyInput" v-model="replyForm.content"></el-input>
          </el-col>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showReplyDialog = false">取 消</el-button>
        <el-button type="primary" @click="replyComment(replyForm.parentId,replyForm.content)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import CommentItem from '@/components/CommentItem.vue';

export default {
  components: {
    CommentItem
  },
  data() {
    let typeId = this.$route.query.id
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      typeData: {},
      typeId: typeId,
      form: {},
      inTime: null,
      outTime: null,
      fromVisible: false,
      commentData: [],
      showBackToTop: false,
      commentCount: 0,
      commentForm : {
        content: '',
      },
      replyForm : {},
      showReplyDialog : false,
      showAllReplies: {},
      replyData:[],
      replyCount:0,
      replyPageSize: 4,
      replyPageNum:1,
      emojis: [],
    }
  },
  computed: {
    dialogWidth() {
      const screenWidth = window.innerWidth;
      if (screenWidth <= 450) {
        return '60%'; // 屏幕宽度小于等于450px时
      } else if (screenWidth <= 1250) {
        return '60%'; // 屏幕宽度小于等于1250px时
      } else {
        return '40%'; // 屏幕宽度大于1250px时
      }
    },
  },
  created() {

  },
  mounted() {
    this.emojis = this.$emojis.split(',')
    this.loadComments();
    this.fetchCommentCount();
    this.loadType();
    window.addEventListener('scroll', this.handleScroll); // 监听滚动事件
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll); // 在组件销毁前移除事件监听器
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    fetchCommentCount(){
      this.$request.get('type/getCommentCount/' + this.typeId).then(res =>　{
        if (res.code === '200'){
          this.commentCount = res.data
        }
        else {
          this.$message.error(res.msg)
        }
      })
    },
    Comment(){
      if (!this.commentForm.content){
        this.$message.warning('请输入评价内容')
        return
      }
      let data = {
        content: this.commentForm.content,
        typeId: this.typeId,
        homestayId: this.typeData.homestayId,
        userId: this.user.id,
        role: this.user.role,
        parentId: 0
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('评价成功')
          this.commentForm = {}
          this.loadComments();
          this.fetchCommentCount();
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    //点击表情图标后
    clickEmoji(emoji) {
      if (this.showReplyDialog) {
        // 如果是回复对话框打开，则插入到回复输入框中
        this.replyForm.content += emoji;
        //移动光标到内容后
        this.$nextTick(() => {
          this.$refs.replyInput.focus();
        });
      } else {
        // 否则插入到评论输入框中
        this.commentForm.content += emoji;
        //移动光标到内容后
        this.$nextTick(() => {
          this.$refs.commentInput.focus();
        });
      }
    },
    handleScroll() {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
      this.showBackToTop = scrollTop > 240; // 当滚动高度超过240时显示回到顶部按钮
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
    replyComment(parentId, replyContent){
      if (!replyContent){
        this.$message.warning('请输入回复内容')
        return
      }
      let data = {
        content: replyContent,
        typeId: this.typeId,
        homestayId: this.typeData.homestayId,
        userId: this.user.id,
        role: this.user.role,
        parentId: parentId
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('回复成功')
          this.loadComments()
          this.fetchCommentCount();
          this.showReplyDialog = false
          this.replyForm = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    openReplyDialog(parentId) {
      // 初始化 replyForm 对象
      this.replyForm = {
        parentId: parentId,
        content: '', // 确保回复内容为空
      };
      this.showReplyDialog = true; // 显示对话框
    },
    loadComments() {
      // 发起请求加载评论数据
      this.$request.get('comment/selectCommentByTypeId?id=' + this.typeId).then(res => {
        if (res.code === '200') {
          this.commentData = res.data;
          // 加载每个评论的回复数量
          this.loadCommentReplyCounts(this.commentData);
          // 初始化每个评论的回复分页数据
          this.commentData.forEach(comment => {
            this.$set(comment, 'replyPageNum', 1);
          });
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    loadCommentReplyCounts(comments) {
      comments.forEach(comment => {
        // 如果评论有回复
        if (comment.children && comment.children.length > 0) {
          // 计算回复数量
          comment.replyCount = this.countReplies(comment.children);
        } else {
          // 如果没有回复，则回复数量为0
          comment.replyCount = 0;
        }
      });
    },
    countReplies(comments) {
      let count = comments.length;
      comments.forEach(comment => {
        if (comment.children && comment.children.length > 0) {
          count += this.countReplies(comment.children);
        }
      });
      return count;
    },
    loadReplies(commentId, pageNum) {
      // 发起请求加载评论的回复数据
      this.$request.get('comment/selectReplies', {
        params: {
          pageNum: pageNum,
          pageSize: this.replyPageSize,
          commentId: commentId,
        }
      }).then(res => {
        if (res.code === '200') {
          this.replyData = res.data?.list;
          const comment = this.findCommentById(this.commentData, commentId);
          if (comment) {
            this.$set(comment, 'children', res.data); // 替换为新的回复数据
          }
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    toggleReplies(commentId, pageNum = 1) {
      // 切换评论的回复展开/收起状态
      if (!this.showAllReplies.hasOwnProperty(commentId)) {
        this.$set(this.showAllReplies, commentId, false);
      }
      this.$set(this.showAllReplies, commentId, !this.showAllReplies[commentId]);

      if (this.showAllReplies[commentId]) {
        this.loadReplies(commentId, pageNum);
      } else {
        // 当收起回复时,重新计算回复数量
        const comment = this.findCommentById(this.commentData, commentId);
        comment.replyCount = this.countReplies(comment.children);
      }
    },
    findCommentById(comments, commentId) {
      // 根据评论ID查找对应的评论
      for (let i = 0; i < comments.length; i++) {
        if (comments[i].id === commentId) {
          //console.log(comments[i])
          return comments[i];
        }
        if (comments[i].children && comments[i].children.length > 0) {
          const foundComment = this.findCommentById(comments[i].children, commentId);
          if (foundComment) {
            return foundComment;
          }
        }
      }
      return null;
    },

    loadType() {
      this.$request.get('type/selectById?id=' + this.typeId).then(res => {
        if (res.code === '200'){
          this.typeData = res.data
          //console.log(this.typeData)
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    addCollect(){
      let data = {userId:this.user.id,typeId:this.typeId}
      this.$request.post('collect/add',data).then(res => {
        if (res.code === '200'){
          this.$message.success("收藏成功")
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    initDialog() {
      if(this.typeData.num === 0){
        this.$message.warning('该类房型没有空闲房间，请选择其他客房')
        return
      }
      this.fromVisible = true
    },
    save() {
      let data = {
        userId: this.user.id,
        typeId: this.typeId,
        homestayId: this.typeData.homestayId,
        inTime: this.inTime,
        outTime: this.outTime
      }
      this.$request.post('/orders/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('预订成功')
          this.fromVisible = false
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/comment/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.loadComments();
            this.fetchCommentCount();
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },

  },
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
.sidebar-btn:hover{
  color: #409EFF;
}

.emoji-box {
  height: 200px;
  overflow-y: scroll;
  line-height: 30px;
}
 .emoji-box::-webkit-scrollbar{
  width: 4px;
}

 .emoji-box::-webkit-scrollbar-thumb{
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.1);
}

.emoji-box::-webkit-scrollbar-track{
  border-radius: 0;
  background: rgba(0, 0, 0, 0.1);
}
/*输入文本域获得焦点时*/
/deep/ .el-textarea__inner:focus{
  border-color: #6E77F2;
}
/*当前页数颜色*/
/deep/ .el-pager li.active {
  color: #6E77F2;
  cursor: default;
}
/*换页键的颜色*/
/deep/.el-pagination button:hover {
  color: #6E77F2
}
/*鼠标悬浮时，页数的颜色*/
/deep/ .el-pager li:hover {
  color: #6E77F2
}

.main-content{
  display: flex;
  flex-direction: column;
}

.main-content .d-info{
  width: 70%;
  margin: 10px auto;
}

.main-content .c-info{
  width: 82%;
  margin: 10px auto 100px auto ;
}

.main-content .c-info .c-info-content{
  width: 90%;
  margin: 15px 15px 5px auto;
}

.info-container {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.info{
  flex-grow: 1;
  display: flex;
  align-items: flex-start;
  margin-top: 15px;
  padding: 0 50px
}
.info-img{
  width: 60%;
  margin-right: 10px
}

@media screen and (max-width: 1060px) {
  .main-content .d-info{
    width: 70%;
  }

  .main-content .c-info{
    width: 70%;
  }
  .main-content .c-info .c-info-content{
    width: 100%;
    margin: auto;
  }

  .info-container > div {
    width: 33.3%; /* 在屏幕宽度小于450px时，每行显示两个信息 */
  }

  .info {
    flex-direction: column; /* 小于1060px时，改为垂直排列 */
  }
  .info > el-col {
    width: 100%;
  }
  .info-img{
    width: 90%;
    height: 70%;
    margin:0 0 20px 0; /* 在小屏幕下取消图片和介绍之间的间距 */ /* 调整图片和介绍之间的间距 */
  }
}

@media screen and (max-width: 450px) {
  .main-content .d-info{
    width: 85%;
  }

  .main-content .c-info{
    width: 85%;
  }
  .main-content .c-info .c-info-content{
    width: 100%;
    margin: auto;
  }
  .info-container > div {
    width: 45%; /* 在屏幕宽度小于450px时，每行显示两个信息 */
  }
  .info-img{
    width: 80%;
    height: 60%;
    margin:0 0 20px 0; /* 在小屏幕下取消图片和介绍之间的间距 */ /* 调整图片和介绍之间的间距 */
  }
  .info > el-col {
    width: 100%;
  }

}

</style>