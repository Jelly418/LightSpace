<template>
    <div class="main">
      <div class="canvas" @click="getWords">
      <vue-particles
        color="#000"
        :particleOpacity="0.5"
        :particlesNumber="100"
        shapeType="circle"
        :particleSize="5"
        linesColor="#000"
        :linesWidth="2"
        :lineLinked="true"
        :lineOpacity="0.4"
        :linesDistance="150"
        :moveSpeed="8"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="true"
        clickMode="push"
      >
      </vue-particles>
      </div>
      <div class="text">
        <span style="font-size: 2rem">{{text}}</span>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Appindex',
  data () {
    return {
      text: ''
    }
  },
  mounted: function () {
    this.getWords()
  },
  methods: {
    getWords () {
      this.axios
        .get(
          'https://v1.alapi.cn/api/soul')
        .then(successResponse => {
          this.text = successResponse.data.data.title
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>
.main{
  height:643px;
  position: relative;
  overflow: hidden;
  /*background: url("../../assets/loginBackground4.jpg") no-repeat ;*/
}
.text{
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate( -50%,-50%);
  text-align: center;
}
#particles-js{
  /*background: url("../../assets/loginBackground4.jpg") no-repeat ;*/
  background-size:100% 100%;
  opacity: 0.4;
}
  .canvas{
    position: absolute;
    width: 1500px;
    height:1200px;
    overflow: auto;
  }
</style>
