<template>
  <el-menu router
    class="categories"
    default-active="0"
    @select="handleSelect"
    active-text-color="red">
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
        {name: '/', categoryItem: '书籍'},
        {name: '/', categoryItem: '音乐'},
        {name: '/', categoryItem: '电影'},
        {name: '/', categoryItem: '读物'}
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
