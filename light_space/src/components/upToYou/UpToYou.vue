<template>
  <div id="app">
    <!-- 计数器功能区域 -->
    <el-alert v-show="isBigShow"
      title="您觉得这数是不是有点大了？"
      type="info"
      close-text="退下吧">
    </el-alert>
    <el-alert v-show="isSmallShow"
      title="这么小的数您也喜欢？"
      type="info"
      close-text="退下吧">
    </el-alert>
    <div class="input-num ">
      <button  @click="sub">
        -
      </button>
      <span>{{ num }}</span>
      <button @click="add" >
        +
      </button>
    </div>
      <el-button  type="info" plain @click="go">Let's Go!!!</el-button></div>
</template>

<script>
export default {
  name: 'upToYou',
  data () {
    return {
      num: 1,
      isBigShow: false,
      isSmallShow: false,
      urlList: [
        {name: 'Mikutap', 'url': 'https://aidn.jp/mikutap/'},
        {name: 'happy happy hardcore', 'url': 'https://happyhappyhardcore.com/'},
        {name: 'staggering beauty', 'url': 'http://www.staggeringbeauty.com/'},
        {name: 'chrismckenzie', 'url': 'https://chrismckenzie.com/'},
        {name: 'ouaismaisbon', 'url': 'http://www.ouaismaisbon.ch/'},
        {name: 'papertoilet', 'url': 'http://www.papertoilet.com/'},
        {name: 'cat-bounce', 'url': 'https://cat-bounce.com/'},
        {name: 'neal', 'url': 'https://neal.fun/'},
        {name: 'weavesilk', 'url': 'http://weavesilk.com/'},
        {name: 'spielzeugz', 'url': 'http://www.spielzeugz.de/html5/liquid-particles-3D/'}
      ]
    }
  },
  methods: {
    add () {
      if (this.num < 22) {
        this.num++
      } else {
        this.isBigShow = true
      }
    },
    sub () {
      if (this.num > -22) {
        this.num--
      } else {
        this.isSmallShow = true
      }
    },
    go () {
      const number = Math.round(Math.random() * 10)
      window.location.href = this.urlList[number].url
    }
  }
}
</script>

<style scoped>
  body{
    background-color: #f5f5f5;
  }
  #app {
    width: 480px;
    height: 80px;
    margin: 200px auto;
    /*background: url("../../assets/loginBackground4.jpg") no-repeat ;*/
  }
  .input-num {
    margin-top:20px;
    height: 100%;
    display: flex;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 4px 4px 4px #adadad;
    border: 1px solid #c7c7c7;
    background-color: #c7c7c7;
    margin-bottom: 20px;
  }
  .input-num button {
    width: 150px;
    height: 100%;
    font-size: 40px;
    color: #ad2a27;
    cursor: pointer;
    border: none;
    outline: none;
    background-color:rgba(0, 0, 0, 0);
  }
  .input-num span {
    height: 100%;
    font-size: 40px;
    flex: 1;
    text-align: center;
    line-height: 80px;
    font-family:auto;
    background-color: white;
  }
  img{
    float: right;
    margin-top: 50px;
  }
  #demo-canvas{
    display: inline-block;
  }
</style>
