<template>
  <div>
    <!-- 评论区 -->
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
      <div style="display: flex;" v-if="item.parentId === 0 && item.children && item.children.length > 0">
        <el-col :span="1.5">
          <div style="height: 50px; width: 50px;"></div>
        </el-col>
        <el-col :span="22">
          <!-- 递归回复的回复 -->
          <div v-if="item.children && item.children.length > 0">
            <!-- 根据分页渲染评论回复列表 -->
            <div v-for="reply in item.paginatedReplies" :key="reply.id">
              <CommentItem
                  :user="user"
                  :commentData="[reply]"
                  @openReplyDialog="openReplyDialog"
                  @del="del"
              ></CommentItem>
            </div>
          </div>
          <!-- 评论回复分页 -->
          <el-pagination
              v-if="item.children && item.children.length > 0"
              @current-change="handleReplyPageChange(item, $event)"
              :current-page="item.replyPageNum"
              :page-sizes="[5, 10, 20]"
              :page-size="item.replyPageSize"
              layout="total, prev, pager, next"
              :total="item.replyCount"
          ></el-pagination>
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
    comment: Object,
    currentPage: Number,
    pageSize: Number,
  },
  data() {
    return {
      replyPageSize: 4,
      replyPageNum: 1,
      paginatedReplies: []
    };
  },
  watch: {
    replyPageNum: {
      handler(newValue, oldValue) {
        this.calculatePaginatedReplies();
      },
      immediate: true // 立即执行一次
    },
    commentData: {
      handler(newValue, oldValue) {
        this.calculatePaginatedReplies();
      },
      deep: true,
      immediate: true // 立即执行一次
    }
  },

  created() {
    this.calculatePaginatedReplies()
  },

  methods: {

    // 根据评论的回复数量和分页信息计算分页回复
/*    calculatePaginatedReplies() {
      this.commentData.forEach(comment => {
        if (comment.children && comment.children.length > 0) {
          const startIndex = (comment.replyPageNum - 1) * comment.replyPageSize;
          const endIndex = startIndex + comment.replyPageSize;
          comment.paginatedReplies = comment.children.slice(startIndex, endIndex);
        }
      });
    },*/
/*    calculatePaginatedReplies() {
      this.paginatedReplies = [];

      this.commentData.forEach(comment => {
        console.log("Processing comment:", comment);
        // 处理有回复的评论
        if (comment.children && comment.children.length > 0) {
          console.log("comment has children:", comment.children);
          // 计算分页的起始索引和结束索引
          const startIndex = (comment.replyPageNum - 1) * comment.replyPageSize;
          const endIndex = startIndex + comment.replyPageSize;
          // 切割出当前评论需要显示的回复
          const slicedChildren = comment.children.slice(startIndex, endIndex);
          console.log("Sliced children:", slicedChildren); // 添加调试语句
          // 将切割出的回复添加到分页回复数组中
          this.paginatedReplies = this.paginatedReplies.concat(slicedChildren);
          console.log("Paginated replies:", this.paginatedReplies); // 添加调试语句
        }
      });
    },*/
    calculatePaginatedReplies() {
      this.paginatedReplies = [];

      const processComments = (comments) => {
        comments.forEach(comment => {
          // 处理有回复的评论
          if (comment.children && comment.children.length > 0) {
            // 计算分页的起始索引和结束索引
            const startIndex = (comment.replyPageNum - 1) * comment.replyPageSize;
            const endIndex = startIndex + comment.replyPageSize;
            // 切割出当前评论需要显示的回复
            const slicedChildren = comment.children.slice(startIndex, endIndex);
            // 将切割出的回复添加到分页回复数组中
            this.paginatedReplies = this.paginatedReplies.concat(slicedChildren);
            // 递归处理子回复
            slicedChildren.forEach(child => {
              if (child.children && child.children.length > 0) {
                processComments(child);
              }
            });
          }
        });
      };

      processComments(this.commentData);
    },

// 修改 handleReplyPageChange 方法
    handleReplyPageChange(comment, pageNum) {

        comment.replyPageNum = pageNum;
        this.$emit('loadReplies', comment.id,pageNum);

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
