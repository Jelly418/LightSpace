<template>
  <div>
    <nav-menu></nav-menu>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <Side-Menu></Side-Menu>
    </el-aside>
    <el-main>
    <MyQuestion></MyQuestion>
    </el-main>
  </el-container>
  </div>
</template>

<script>
import SideMenu from './SideMenu'
import MyQuestion from './MyQuestion'
import NavMenu from '../common/NavMenu'
export default {
  name: 'AppLibrary',
  components: {NavMenu, MyQuestion, SideMenu}
}
</script>

<style scoped>
  .books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
