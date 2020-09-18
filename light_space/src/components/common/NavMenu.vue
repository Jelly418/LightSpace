<template>
  <div class="nav">
  <el-menu
    :default-active="'/index'"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red"
    style="min-width: 1300px">
    <el-menu-item v-for="(item,index) in navList" :key="index" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
    <img src="../../assets/loginBackground5.jpg" style="height: 70px;width: 200px;margin-right: 250px">
    <span style="position: absolute;padding-top: 20px;right: 50%;font-size: 26px;font-weight: bold">Light Space </span>
    <el-menu-item v-show="username == null" style = "float:right;margin-right: 30px;" @click="$router.push({path: '/login'})">登录/注册</el-menu-item>
    <el-submenu index="1" style="float: right;margin-right:50px" v-show="username != null">
      <template slot="title">{{username}}</template>
      <el-menu-item index="1-1">个人中心</el-menu-item>
      <el-menu-item index="userInfoIndex">我的问题</el-menu-item>
      <el-menu-item index="1-3">消息中心</el-menu-item>
      <el-menu-item index="login" @click="loginOut">退出登录</el-menu-item>
    </el-submenu>
  </el-menu>
    <music-player></music-player>
  </div>
</template>

<script>
import MusicPlayer from './MusicPlayer'
import Cookies from 'js-cookie'
export default {
  name: 'NavMenu',
  components: {
    MusicPlayer
  },
  data () {
    return {
      navList: [
        {name: '/index', navItem: '私人空间'},
        {name: '/video', navItem: '视频'},
        {name: '/upToYou', navItem: '随便'},
        {name: '/yesNoIndex', navItem: 'Yes & No'},
        {name: '/noteIndex', navItem: '心情便签'},
        {name: '/recommend', navItem: '推荐'},
        {name: '/question', navItem: '问答'}
      ],
      username: Cookies.get('username')
    }
  },
  methods: {
    loginOut () {
      Cookies.set('username', '')
    }
  }
}
</script>

<style scoped>
  /*.nav{*/
    /*opacity: 0.4;*/
  /*}*/
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }
</style>
