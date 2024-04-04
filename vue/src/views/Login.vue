<template>
  <div class="container">
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">欢迎登录德新民宿预订系统</div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <div style="display: flex; justify-content: center">
            <el-input prefix-icon="el-icon-key" style="width: 56%; margin-right: 10px"
                      v-model="form.code" placeholder="请输入验证码"></el-input>
            <img :src="codeImg" @click="getCode()" width="140px" height="33px" alt=""/>
          </div>
        </el-form-item>
        <el-form-item prop="role">
          <el-select v-model="form.role" placeholder="请选择角色" style="width: 100%;">
            <el-option label="管理员" value="ADMIN"></el-option>
            <el-option label="民宿" value="HOMESTAY"></el-option>
            <el-option label="用户" value="USER"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%; background-color: #4C97C5; border-color: #4C97C5; color: white" @click="login">登 录</el-button>
        </el-form-item>
        <div style="display: flex; align-items: center">
          <div style="flex: 1"></div>
          <div style="flex: 1; text-align: right">
            还没有账号？请 <a href="/register">注册</a>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {role: 'ADMIN'},
      codeImg:'',
      uuid:'',
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        verCode: [
          {required: true, message: '请输入验证码', trigger: 'blur'},
        ]
      }
    }
  },
  mounted() {
    this.getCode()
  },
  methods: {
    getCode() {
      // this.uuid = generateUUID()
      // this.codeImg = this.$baseUrl + '/validateCode?key=' + this.uuid
      this.uuid = generateUUID()
      this.codeImg = this.$baseUrl + '/getValidateCode?key=' + this.uuid
    },
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.form.key = this.uuid
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))  // 存储用户数据
              this.$message.success('登录成功')
              if (res.data.role === 'USER') {
                location.href = '/front/home'
              } else {
                location.href = '/home'
              }
              //this.$router.push('/')  // 跳转主页
            } else {
              this.getCode();
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
  }
}
//生成UUID,唯一编码
function generateUUID(){
  let d =new Date().getTime();
  if (window.performance && typeof window.performance.now === "function"){
    d += performance.now()//use high-precision timer if available
    let uuid ='xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,function (c) {
      let r = (d + Math.random() * 16) % 16 | 0;
      d = Math.floor(d / 16);
      return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("~@/assets/imgs/bg.jpg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}
a {
  color: #2a60c9;
}

/deep/ .el-input__inner:focus{
  border-color: #4c97c5;
}

.el-select-dropdown__item.selected {
  color: #4c97c5;
  font-weight: 700
}

/deep/ .el-select .el-input.is-focus .el-input__inner {
  border-color: #4c97c5;
  transition: all 0.3s;
}

/deep/ .el-select .el-input .el-input__inner {
  border-color: #e4e7ed;
}

/deep/ input:-webkit-autofill {
  background-color: #ffffff !important;
  transition: background-color 1000s ease-in-out 0.5s !important;
}


</style>