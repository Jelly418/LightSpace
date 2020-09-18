<template>
  <div class="homepage-hero-module">
      <div class="filter">
        <el-form  :model="userInformation" status-icon  ref="userInformation" class="login-container" label-position="left"
                 label-width="0px" :rules="rules">
          <h3 class="login_title">注册</h3>
          <el-form-item prop="username">
            <el-input type="text" v-model="userInformation.username"
                      auto-complete="off" placeholder="用户名" ></el-input>
          </el-form-item>
          <el-form-item prop="password">
          <el-input type="password" v-model="userInformation.password"
                    auto-complete="off" placeholder="密码" ></el-input>
        </el-form-item>
          <el-form-item prop="checkPass" >
            <el-input type="password" v-model="userInformation.checkPass"
                      auto-complete="off" placeholder="确认密码"  ></el-input>
          </el-form-item>
          <el-form-item prop="phone">
            <el-input type="text" v-model.number="userInformation.phone"
                      auto-complete="off" placeholder="可通过手机号登录"></el-input>
          </el-form-item>
          <el-form-item style="width: 100%">
            <el-button :plain="true"  style="width: 100%;background: #505458;border: none" type="primary" @click="createUser">创建用户</el-button>
          </el-form-item>
        </el-form>
      </div>
  </div>
</template>

<script>
export default {
  name: 'Registration',
  data () {
    let checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'))
      }
    }
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.toString().length < 6) {
        callback(new Error('密码长度小于6'))
      } else if (this.userInformation.checkPass !== '') {
        this.$refs.userInformation.validateField('checkPass')
      }
      callback()
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.userInformation.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var checkPhone = (rule, value, callback) => {
      const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
      if (!value) {
        return callback(new Error('电话号码不能为空'))
      }
      setTimeout(() => {
        // Number.isInteger是es6验证数字是否为整数的方法,实际输入的数字总是识别成字符串
        // 所以在前面加了一个+实现隐式转换

        if (!Number.isInteger(+value)) {
          callback(new Error('请输入数字值'))
        } else {
          if (phoneReg.test(value)) {
            callback()
          } else {
            callback(new Error('电话号码格式不正确'))
          }
        }
      }, 100)
    }
    return {
      userInformation: {
        username: '',
        password: '',
        checkPass: '',
        phone: ''
      },
      rules: {
        username: [
          { validator: checkName, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        phone: [
          { validator: checkPhone, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    createUser () {
      // console.log(1111111)
      // this.$refs.userInformation.validate((valid) => {
      //   if (valid) {
      console.log('success')
      this.axios
        .post('/insertUser', {
          username: this.userInformation.username,
          password: this.userInformation.password,
          phone: this.userInformation.phone
        })
        .then(successResponse => {
          if (successResponse.data === 'nameError') {
            this.$message.error('昵称已被占用')
          } else {
            if (successResponse.data === 'phoneError') {
              this.$message.error('手机号已注册过')
            } else {
              if (successResponse.data === 'success') {
                this.$message({
                  message: '注册成功',
                  type: 'success'
                })
                this.$router.replace({path: '/login'})
              }
            }
          }
        })
        .catch(failResponse => {
        })
        // } else {
        //   console.log('error submit!!')
        //   this.$router.replace({path: '/registration'})
        // }
      // })
    }
  }
}
</script>

<style scoped>
  .homepage-hero-module{
    background: url("../../assets/registration0.jpg")  no-repeat center ;
    position: relative;
    height: 97.8vh;
    overflow: hidden;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 400px;
    padding: 35px 35px 15px 35px;
    background: rgba(0, 0, 0, 0.01);
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #505458;
  }
  .video-container .poster img{
    z-index: 0;
    position: absolute;
  }
</style>
