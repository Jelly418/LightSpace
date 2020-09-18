<template>
  <div>
    <nav-menu></nav-menu>
    <div class="question-area">
      <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto">
        <span content-position="left">{{moment(note.createTime).format('YYYY-MM-DD HH:mm:ss')}}</span><br>
        <el-input style="font-size: 20px" v-model="note.value" type="textarea"  :rows="5"><strong>{{note.value}}</strong></el-input>
        <el-button  @click="update()" style="float:right" :plain="true">修改便签</el-button>
      </el-card>
    </div>
  </div>
</template>

<script>
import NavMenu from '../common/NavMenu'
export default {
  name: 'NoteDetail',
  components: {NavMenu},
  data () {
    return {
      note: {
        id: this.$route.query.id,
        value: '',
        createTime: ''
      }
    }
  },
  mounted () {
    this.loadNote()
  },
  methods: {
    loadNote () {
      var that = this
      this.axios.post('/findNoteById?id=' + this.$route.query.id).then(resp => {
        // console.log(resp)
        if (resp && resp.status === 200) {
          that.note = resp.data
        }
      })
    },
    update () {
      this.axios.post('/updateNote', {
        id: this.note.id,
        value: this.note.value
      }).then(resp => {
        this.$message({
          showClose: true,
          message: '修改成功',
          type: 'success'
        })
        this.$router.replace({path: '/noteIndex'})
      })
    }
  }
}
</script>

<style scoped>
  /deep/ .el-textarea__inner{
    border: hidden;
  }
</style>
