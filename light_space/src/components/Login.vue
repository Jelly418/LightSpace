<template>
  <div class="homepage-hero-module">
    <div class="video-container">
      <div :style="fixStyle" class="filter">
        <el-form   :model="LoginForm" status-icon  ref="LoginForm" class="login-container" label-position="left"
        label-width="0px"  :rules="rules">
        <h3 class="login_title">系统登录</h3>
        <el-form-item prop="username">
        <el-input type="text" v-model="LoginForm.username"
        auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
        <el-input type="password" v-model="LoginForm.password"
        auto-complete="off" placeholder="密码"></el-input>
          <div>
            <span style="float: left" @click="registered">注册用户</span>
            <span style="float: right" @click="findPassword">忘记密码？</span>
          </div>
        </el-form-item>
        <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none"   :plain="true" @click="login">登录</el-button>
        </el-form-item>
          <el-form-item>
            <div class="third-party-txt" >
              <span class="line"></span>
              <span class="txt">第三方登录</span>
              <span class="line"></span>
            </div>
            <div >
              <span style="display: inline-block;margin-right: 20px;">
                  <a class=" icon penguin_icon"></a>
                  <p>QQ登录</p>
              </span>
              <span style="display: inline-block;margin-right: 20px;">
                  <a class=" icon wxpenguin_icon"></a>
                  <p>微信登录</p>
              </span>
              <span style="display: inline-block;">
                  <a class="icon weibopenguin_icon"></a>
                  <p>微博登录</p>
              </span>
            </div>
          </el-form-item>
        </el-form>
      </div>
      <!--视频背景-->
      <video :style="fixStyle" autoplay loop muted class="fillWidth" v-on:canplay="canplay">
        <source src="../assets/login0.mp4" type="video/mp4"/>
        浏览器不支持 video 标签，建议升级浏览器。
        <source src="../assets/login0.mp4" type="video/webm"/>
        浏览器不支持 video 标签，建议升级浏览器。
      </video>
      <audio  autoplay loop>
        <source src="../assets/loginAudio.mp3" type="audio/mp3"/>
      </audio>
      <div class="poster hidden" v-if="!vedioCanPlay">
        <img :style="fixStyle" src="../assets/loginBackground.jpg" alt="">
      </div>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  name: 'Login',
  data () {
    let checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'))
      }
    }
    let checkPass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('密码不能为空'))
      }
    }
    return {
      rules: {
        username: [
          {validator: checkName, trigger: 'blur'}
        ],
        password: [
          {validator: checkPass, trigger: 'blur'}
        ]
      },
      vedioCanPlay: false,
      fixStyle: '',
      LoginForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    canplay () {
      this.vedioCanPlay = true
    },
    login () {
      const that = this
      this.axios
        .post('/login', {
          username: this.LoginForm.username,
          password: this.LoginForm.password
        })
        .then(successResponse => {
          if (successResponse.data === 'success') {
            this.$message({
              showClose: true,
              message: '登录成功',
              type: 'success'
            })
            // 登录成功，存储用户信息到cookie中
            Cookies.set('username', that.LoginForm.username)
            this.$router.replace({path: '/index'})
          } else {
            this.$message({
              showClose: true,
              message: '用户名或密码错误',
              type: 'error'
            })
          }
        })
        .catch(failResponse => {
        })
    },
    registered () {
      this.$router.replace({path: '/registration'})
    },
    findPassword () {
      this.$router.replace({path: '/findPassword0'})
    }
  },
  mounted: function () { // 屏幕自适应
    window.onresize = () => {
      const windowWidth = document.body.clientWidth
      const windowHeight = document.body.clientHeight
      const windowAspectRatio = windowHeight / windowWidth
      let videoWidth
      let videoHeight
      if (windowAspectRatio < 0.5625) {
        videoWidth = windowWidth
        videoHeight = videoWidth * 0.5625
        this.fixStyle = {
          height: windowWidth * 0.5625 + 'px',
          width: windowWidth + 'px',
          'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
          'margin-left': 'initial'
        }
      } else {
        videoHeight = windowHeight
        videoWidth = videoHeight / 0.5625
        this.fixStyle = {
          height: windowHeight + 'px',
          width: windowHeight / 0.5625 + 'px',
          'margin-left': (windowWidth - videoWidth) / 2 + 'px',
          'margin-bottom': 'initial'
        }
      }
    }
    window.onresize()
  }
}
</script>

<style >
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
  /*#poster {*/
    /*!*background: url('../assets/loginBackground.jpg') no-repeat center;*!*/
    /*height: 100%;*/
    /*width: 100%;*/
    /*background-size: cover;*/
    /*position: fixed;*/
  /*}*/
  /*body{*/
    /*!*background-color: transparent;*!*/
    /*margin: 0px;*/
  /*}*/

  .homepage-hero-module,
  .video-container {
    position: relative;
    height: 97.8vh;
    overflow: hidden;
  }

  .video-container .poster img{
    z-index: 0;
    position: absolute;
  }

  .video-container .filter {
    z-index: 1;
    position: absolute;
    background: rgba(0, 0, 0, 0.1);
    width: 100%;
  }

  .fillWidth {
    width: 100%;
  }

  .third-party-txt  {
    height: 60px;
    line-height: 60px;
    text-align: center;
  }
  .third-party-txt .line {
    display: inline-block;
    width: 155px;
    border-top: 1px solid #ccc ;
  }
  .third-party-txt .txt {
    color: #686868;
    vertical-align:-4px;
  }
  .icon {
    margin: 0 6px 0 0;
    display: inline-block;
    vertical-align: middle;
  }
  .penguin_icon{
    width: 56px;
    height: 56px;
    margin-right: 0;
    background: url(//image.wjx.com/images/register-login/qq.png) no-repeat center;
  }
  .wxpenguin_icon {
    width: 56px;
    height: 56px;
    background: url(//image.wjx.com/images/register-login/weixin-nor.png) no-repeat center;
  }
  .weibopenguin_icon{
    width: 56px;
    height: 56px;
    margin-right: 0;
    background: url('../assets/weiboLogo.png') no-repeat center;
  }
  p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-size: 12px;
    margin:0;
  }
</style>
