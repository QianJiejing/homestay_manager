<template>
  <div style="display: flex;flex-direction: column;">
    <!-- 评论区 -->
    <!-- 循环渲染每条评论 -->
    <div v-for="item in commentData" :key="item.id" :style="getItemStyle(item)">
      <div style="display: flex;">
        <el-col :span="1.5" style="display: flex;margin-right: 5px">
          <img :src="item.avatar" style="height: 50px; width: 50px; border-radius: 50%" alt="">
        </el-col>
        <el-col :span="22" style="flex-grow: 1;">
          <!-- 显示评论内容 -->
          <div style="margin:3px 0;font-size: 12px;font-weight:500;color: #909399">
            <i v-if="item.role === 'HOMESTAY'" style="margin-right: 3px" class="fas fa-home-user"></i>{{ item.userName }}</div>
          <div style="margin:3px 0;font-size: 14px;font-weight:550;color: #161823">{{item.content}}</div>
          <div style="margin:3px 0;font-size: 12px;font-weight:400;color: #909399">{{ item.time }}
            <span class="hover-effect" style="margin-left: 20px" @click="openReplyDialog(item.id)"><i class="el-icon-s-comment" style="margin-right: 2px"></i>回复</span>
            <span class="hover-effect" style="margin-left: 20px" @click="del(item.id)"
                  v-if="item.userId === user.id && item.role === 'USER'"><i class="el-icon-delete-solid" style="margin-right: 2px"></i>删除</span>
          </div>
        </el-col>
      </div>
      <!-- 评论回复 -->
      <!-- 判断是否存在回复并循环渲染 -->
      <div style="display: flex;" v-if=" item.children && item.children.length > 0">
        <!--空白盒子，用于占位对齐-->
        <el-col :span="1.5">
          <div style="height: 50px; width: 50px;" v-if="item.parentId === 0"></div>
        </el-col>
        <el-col :span="22">
          <!-- 循环渲染回复 -->
          <div v-for="(reply, replyIndex) in visibleReplies(item)" :key="reply.id" :style="getItemStyle(reply)" >
            <div style="display: flex;" v-if=" (showAllReplies[item.id] || replyIndex < 2) && item.replyCount > 0">
              <el-col :span="1.5" style="display: flex;margin-right: 5px">
                <img :src="reply.avatar" style="height: 50px; width: 50px; border-radius: 50%" alt="">
              </el-col>
              <el-col :span="22" style="flex-grow: 1;">
                <!-- 显示评论内容 -->
                <div style="margin:3px 0;font-size: 12px;font-weight:500;color: #909399">
                  <i v-if="reply.role === 'HOMESTAY'" style="margin-right: 3px" class="fas fa-home-user"></i>{{ reply.userName }}</div>
                <div style="margin:3px 0;font-size: 14px;font-weight:550;color: #161823">{{reply.content}}</div>
                <div style="margin:3px 0;font-size: 12px;font-weight:400;color: #909399">{{ reply.time }}
                  <span class="hover-effect" style="margin-left: 20px" @click="openReplyDialog(reply.id)"><i class="el-icon-s-comment" style="margin-right: 2px"></i>回复</span>
                  <span class="hover-effect" style="margin-left: 20px" @click="del(reply.id)"
                        v-if="reply.userId === user.id && reply.role === 'USER'"><i class="el-icon-delete-solid" style="margin-right: 2px"></i>删除</span>
                </div>
              </el-col>
            </div>
          </div>
          <!-- 分页组件 -->
          <el-pagination
              v-if="showAllReplies[item.id] && item.replyCount > replyPageSize"
              :current-page.sync="item.replyPageNum"
              :page-size="replyPageSize"
              layout="total,prev, pager, next"
              :total="item.replyCount"
              @current-change="pageNum => handlePageChange(item, pageNum)"
          >
          </el-pagination>
          <!-- 展开/收起回复 -->
          <div class="hover-effect" @click="toggleReplies(item.id)" style="margin-top: 5px;">
            <span v-if="!showAllReplies[item.id] && item.replyCount > 2">
              共{{ item.replyCount }}条回复, 点击查看
            </span>
            <span v-else-if="showAllReplies[item.id]">
              收起回复
            </span>
          </div>
        </el-col>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentItem",

  props: {
    commentData: {
      type: Array,
      required: true
    },
    user: {
      type: Object,
      required: true
    },
    showAllReplies: {
      type: Object,
      required: true
    },
/*    replyData: {
      type: Array,
      required: true
    },*/
    replyPageSize: {
      type: Number,
      required: true
    },
  },
  created() {
  },

  methods: {
    // 返回显示的回复列表
    visibleReplies(item) {
      if (this.showAllReplies[item.id]) {
        return item.children;
      } else {
        return item.children?.slice(0, 2) || [];
      }
    },
    // 处理页码变化
    handlePageChange(item, pageNum) {
      item.replyPageNum = pageNum;
      this.$emit('loadReplies', item.id, pageNum);
    },
    // 切换回复的展开与收起状态
    toggleReplies(commentId) {
      this.$emit('toggleReplies', commentId);
    },

    openReplyDialog(commentId) {
      this.$emit('openReplyDialog', commentId);
    },

    del(commentId) {
      this.$emit('del', commentId);
    },

    getItemStyle(item) {
      // 如果父节点为0，则设置特定样式
      if (item.parentId === 0) {
        return {
          'margin-top': '15px',
          'padding-bottom': '15px',
          'border-bottom': '1px solid #eee'
        };
      } else {
        return {
          'margin-top': '5px'
        };
      }
    }
  },
};

</script>

<style scoped>

/* 悬停时的样式 */
.hover-effect:hover {
  color: #6E77F2; /* 改变文字颜色 */
  cursor: pointer; /* 鼠标指针变为手型 */
  text-decoration: underline; /* 添加下划线 */
}
</style>
