<template>
  <div>
    <el-card style="width: 50%">
      <el-form :model="user" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <div style="margin: 15px; text-align: center">
          <el-upload
              class="avatar-uploader"
              :action="this.$baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
          >
            <img v-if="user.avatar" :src="user.avatar" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="用户名" disabled></el-input>
        </el-form-item>
        <el-form-item label="民宿名称" prop="name">
          <el-input v-model="user.name" placeholder="民宿名称"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="user.phone" placeholder="电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="user.price" placeholder="价格"></el-input>
        </el-form-item>
        <el-form-item label="官网" prop="url">
          <el-input v-model="user.url" placeholder="官网"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="description">
          <el-input type="textarea" v-model="user.description" placeholder="介绍"></el-input>
        </el-form-item>
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "HomestayPerson",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        name: [
          {required: true, message: '请输入民宿名称', trigger: 'blur'},
        ],
        phone: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          { pattern:/^(13[0-9]|14[01456879]|15[0-3,5-9]|16[2567]|17[0-8]|18[0-9]|19[0-3,5-9])\d{8}$/,
            message:'请输入合法手机号码',trigger:'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          { pattern:/^[\w]+@[\w]+(\.[\w]+)+$/, message:'请输入合法邮箱地址',trigger:'blur'}
        ],
        price: [
          {required: true, message: '请输入价格', trigger: 'blur'},
        ],
        description: [
          {required: true, message: '请输入介绍', trigger: 'blur'},
        ],
      }
    }
  },
  created() {

  },
  methods: {
    update() {
      // 保存当前的用户信息到数据库
      this.$request.put('/homestay/update', this.user).then(res => {
        debugger
        if (res.code === '200') {
          // 成功更新
          this.$message.success('保存成功')

          // 更新浏览器缓存里的用户信息
          localStorage.setItem('xm-user', JSON.stringify(this.user))

          // 触发父级的数据更新
          this.$emit('update:user')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      this.$set(this.user, 'avatar', response.data)
    },
  }
}
</script>

<style scoped>
/deep/.el-form-item__label {
  font-weight: bold;
}
/deep/.el-upload {
  border-radius: 50%;
}
/deep/.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}
/deep/.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
</style>