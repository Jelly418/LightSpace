<template >
  <!--图片展示盒子-->
<div class="showPlace">
  <!--图片展示 5个一行 自动换行-->
  <!--单个图片-->
  <div class="single"  v-for="(item,index) in list" :key="index">
    <a :href="item.video">
     <img :src="item.thumbnail" style="width:206px;height:116px" />
      <p>{{item.text}}</p>
    </a>
  </div>
</div>
</template>

<script>
export default {
  name: 'Video',
  data () {
    return {
      list: []
    }
  },
  mounted: function () {
    this.laugh()
  },
  methods: {
    laugh () {
      this.axios
        .get(
          'https://api.apiopen.top/getJoke?page=1&count=16&type=video')
        .then(successResponse => {
          // console.log(successResponse)
          this.list = successResponse.data.result
          console.log(this.list)
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>
  .showPlace{
    width:1100px;
    margin:0 auto;
  }
  .single{
    width:206px;
    height:116px;
    display:inline-block;
    margin-top: 30px;
    margin-right: 20px;
  }
  p{
    width: 206px;   /*一定要设置宽度，或者元素内含的百分比*/
    overflow:hidden; /*溢出的部分隐藏*/
    white-space: nowrap; /*文本不换行*/
    text-overflow:ellipsis;/*ellipsis:文本溢出显示省略号（...）；*/
  }
</style>
