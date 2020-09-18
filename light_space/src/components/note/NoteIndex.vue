<template>
  <div style="margin-top: 20px">
    <el-button  @click="dialogShow">添加便签</el-button>
    <div class="articles-area">
      <el-card style="text-align: left">
        <div v-for="note in noteList" :key="note.id" style="margin-bottom: 50px">
          <div style="float:left;width:95%;height: 150px;">
            <el-divider content-position="left">{{moment(note.createTime).format('YYYY-MM-DD HH:mm:ss')}}</el-divider>
            <router-link class="article-link" :to="{path:'/noteDetail',query:{id: note.id}}">
              <span style="font-size: 20px" ><strong>{{note.value}}</strong></span>
            </router-link>
          </div>
        </div>
      </el-card>
    </div>
    <!--<div>-->
      <!--<el-pagination-->
        <!--@size-change="handleSizeChange"-->
        <!--@current-change="handleCurrentChange"-->
        <!--:current-page="pagination.currentPage"-->
        <!--:page-sizes="[3,6,9,12]"-->
        <!--:page-size="pagination.pageSize"-->
        <!--layout="total, prev, pager, next, jumper"-->
        <!--:total="pagination.totalSize">-->
      <!--</el-pagination>-->
    <!--</div>-->
    <!--弹窗数据-->
    <el-dialog title="便签" :visible.sync="dialogFormVisible">
      <el-form  :model="note" status-icon  ref="note" class="login-container" label-position="left"
                label-width="0px" :rules="rules">
        <el-form-item prop="value">
          <el-input  type="textarea" :rows="5" v-model="note.value"
                    auto-complete="off" placeholder="便签详情"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" :plain="true" @click="addNote">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  inject: ['reload'],
  name: 'noteIndex',
  data () {
    let checkText = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('便签详情不能为空'))
      }
    }
    return {
      dialogFormVisible: false,
      username: Cookies.get('username'),
      note: {
        value: '',
        createTime: ''
      },
      pagination: {
        totalSize: 0,
        currentPage: 1,
        pageSize: 3
      },
      noteList: [],
      rules: {
        text: [
          { validator: checkText, trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    this.loadNote()
  },
  methods: {
    handleSizeChange (val) {
      this.pagination.pageSize = val
    },
    handleCurrentChange (val) {
      this.pagination.currentPage = val
      this.loadNote()
    },
    loadNote () {
      const that = this
      this.axios.post('/loadNote?username=' + this.username)
        .then(resp => {
          if (resp && resp.status === 200) {
            that.noteList = resp.data
            this.pagination.totalSize = resp.data.total
            console.log(this.pagination.totalSize)
          }
        })
        .catch(failResponse => {
        })
    },
    dialogShow () {
      this.note.createTime = Date.parse(new Date())
      // 显示弹窗
      this.dialogFormVisible = true
    },
    addNote () {
      // var that = this
      this.axios.post('/addNote?username=' + this.username, {
        value: this.note.value,
        createTime: this.note.createTime
      }).then(resp => {
        this.$message({
          message: '便签添加成功',
          type: 'success'
        })
        this.dialogFormVisible = false
        this.reload()
      })
    }
  }
}
</script>

<style scoped>
  .articles-area {
    width: 990px;
    height: 500px;
    margin-left: auto;
    margin-right: auto;
  }

  .article-link {
    text-decoration: none;
    color: #606266;
  }

  /deep/ .el-textarea__inner{
    border: hidden;
  }
</style>
