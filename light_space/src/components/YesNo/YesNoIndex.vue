<template>
      <el-container>
        <el-aside width="750px">
          <yes></yes>
        </el-aside>
        <el-main>
          <no></no>
        </el-main>
      </el-container>
</template>

<script>
import Yes from './Yes'
import No from './No'
export default {
  name: 'YesNoIndex',
  components: {Yes, No}
}
</script>

<style scoped>
  .el-main{
    padding: 10px;
  }
</style>
