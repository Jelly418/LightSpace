<template>
  <div class="homepage-hero-module">
    <div class="filter">
      <el-form class="login-container" label-position="left"
               label-width="0px">
        <h3 class="login_title">找回密码</h3>
        <el-form-item>
          <el-input type="text" v-model="phone"
                    auto-complete="off" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <!--<el-form-item>-->
          <!--<el-input type="text" v-model="captcha"-->
                    <!--auto-complete="off" placeholder="验证码"></el-input>-->
        <!--</el-form-item>-->
        <el-form-item style="width: 100%">
          <el-button type="primary" style="width: 100%;background: #505458;border: none"  :plain="true" @click="findPhone" >下一步</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FindPassword0',
  data () {
    return {
      phone: ''
      // captcha: ''
    }
  },
  methods: {
    findPhone () {
      this.axios
        .post('/findPhone', {
          phone: this.phone
          // captcha: this.captcha
        })
        .then(successResponse => {
          if (successResponse.data === 'success') {
            this.$router.push({
              name: 'findPassword1',
              params: { phone: this.phone }
            })
          } else {
            this.$message({
              showClose: true,
              message: '该手机号未注册',
              type: 'error'
            })
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>
  .homepage-hero-module{
    background: url("../../assets/findPassword0.jpg")  no-repeat center ;
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
  .filter{
    margin-top: 200px;
  }
</style>
