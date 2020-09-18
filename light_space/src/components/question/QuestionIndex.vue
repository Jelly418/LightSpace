<template>
  <div style="margin-top: 20px">
    <el-button  @click="dialogShow">添加问题</el-button>
    <div class="articles-area">
      <el-card style="text-align: left">
        <div v-for="question in QuestionList" :key="question.id" style="margin-bottom: 50px">
          <div style="float:left;width:95%;height: 150px;">
            <router-link class="article-link" :to="{path:'/questionDetail',query:{id: question.id}}">
              <span style="font-size: 20px" @click="viewNumberAdd(question.id,question.viewNumber)"><strong>{{question.title}}</strong></span>
            </router-link>
            <el-divider content-position="left">{{moment(question.createTime).format('YYYY-MM-DD HH:mm:ss')}}</el-divider>
            <!--<router-link class="article-link" >-->
              <p>{{question.text}}</p>
            <!--</router-link>-->
            <div style="margin-top: 20px;background-color: #f3f3f3">
              <el-badge  class="item" style="margin: 0 15px; font-size: 12px">分享</el-badge>
              <span class="interval">|</span>
              <el-badge  class="item" style="margin: 0 15px; font-size: 12px">评论</el-badge>
              <span class="interval">|</span>
              <el-badge  class="item" type="primary" style="margin: 0 15px; font-size: 12px">收藏</el-badge>
              <span class="interval">|</span>
              <el-badge  class="item" type="warning" style="margin: 0 15px; font-size: 12px">浏览{{question.viewNumber}}</el-badge>
              <span class="interval">|</span>
              <el-badge  class="item" type="warning" style="margin: 0 15px; font-size: 12px">举报</el-badge>
            </div>
          </div>
        </div>
      </el-card>
    </div>
    <div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-sizes="[3,6,9,12]"
        :page-size="pagination.pageSize"
        layout="total, prev, pager, next, jumper"
        :total="pagination.totalSize">
      </el-pagination>
    </div>
    <!--弹窗数据-->
    <el-dialog title="提问" :visible.sync="dialogFormVisible">
      <el-form  :model="question" status-icon  ref="question" class="login-container" label-position="left"
                label-width="0px" :rules="rules">
        <el-form-item prop="title">
          <el-input type="text" v-model="question.title"
                    auto-complete="off" placeholder="问题标题" ></el-input>
        </el-form-item>
        <el-form-item prop="text">
          <el-input type="textarea" :rows="5" v-model="question.text"
                    auto-complete="off" placeholder="问题详情" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" :plain="true" @click="addQuestion">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  inject: ['reload'],
  name: 'Question',
  data () {
    let checkTitle = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('标题不能为空'))
      }
    }
    let checkText = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('问题详情不能为空'))
      }
    }
    return {
      dialogFormVisible: false,
      username: Cookies.get('username'),
      question: {
        title: '',
        text: '',
        createTime: ''
      },
      pagination: {
        totalSize: 0,
        currentPage: 1,
        pageSize: 3
      },
      QuestionList: [],
      rules: {
        title: [
          { validator: checkTitle, trigger: 'blur' }
        ],
        text: [
          { validator: checkText, trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    this.loadQuestion()
  },
  methods: {
    handleSizeChange (val) {
      this.pagination.pageSize = val
    },
    handleCurrentChange (val) {
      this.pagination.currentPage = val
      this.loadQuestion()
    },
    loadQuestion () {
      const that = this
      this.axios.post('/loadQuestion?currentPage=' + this.pagination.currentPage + '&pageSize=' + this.pagination.pageSize).then(resp => {
        if (resp && resp.status === 200) {
          that.QuestionList = resp.data.list
          this.pagination.totalSize = resp.data.total
        }
      })
    },
    dialogShow () {
      this.question.createTime = Date.parse(new Date())
      // 显示弹窗
      this.dialogFormVisible = true
    },
    addQuestion () {
      // var that = this
      this.axios.post('/addQuestion?username=' + this.username, {
        title: this.question.title,
        text: this.question.text,
        createTime: this.question.createTime
      }).then(resp => {
        this.$message({
          message: '问题发布成功',
          type: 'success'
        })
        this.reload()
        this.dialogFormVisible = false
        // if (resp && resp.status === 200) {
        //   that.QuestionList = resp.data
        //   // that.total = resp.data.totalElements
        // }
      })
    },
    viewNumberAdd (id, viewNumber) {
      this.axios.post('/viewNumberAdd?viewNumber=' + viewNumber + '&id=' + id).then(resp => {
        if (resp && resp.status === 200) {
          // console.log(resp)
        }
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

  /*.article-link:hover {*/
    /*color: #409EFF;*/
  /*}*/
</style>
