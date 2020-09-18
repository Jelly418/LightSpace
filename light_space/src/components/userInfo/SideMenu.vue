<template>
  <el-menu router
    class="categories"
    default-active="0"
    @select="handleSelect"
    active-text-color="red"
  style="margin-top: 20px">
    <el-menu-item  v-for = " (item,index) in categoryList" :index="item.name" :key="index">
      <i class="el-icon-s-promotion"></i>
      <span slot="title">{{item.categoryItem}}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'SideMenu',
  data () {
    return {
      categoryList: [
        {name: '/', categoryItem: '个人中心'},
        {name: '/myQuestion', categoryItem: '我的问题'},
        {name: '/', categoryItem: '消息中心'}
      ]
    }
  }
}
</script>

<style scoped>
  .categories {
    position: fixed;
    margin-left: 50%;
    left: -600px;
    top: 100px;
    width: 150px;
  }
</style>
