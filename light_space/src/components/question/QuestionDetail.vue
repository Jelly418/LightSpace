<template>
  <div>
    <nav-menu></nav-menu>
  <div class="question-area">
    <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto">
      <div>
        <span style="font-size: 20px"><strong>{{question.title}}</strong></span>
        <el-divider content-position="left">{{moment(question.createTime).format('YYYY-MM-DD HH:mm:ss')}}</el-divider>
        <span style="font-size: 20px">{{question.text}}</span>
        <div style="margin-top: 20px">
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
        <div style="margin-top: 20px">
        <el-input style="font-size: 20px" v-model="comment.value" type="textarea"  :rows="1" placeholder="发表评论" ></el-input>
          <el-button  type="primary" :plain="true" style="float: right;margin-top: 10px" @click="commentAdd">发表评论</el-button>
        </div>
        <div  v-for="comment in question.commentList" :key="comment.id" style="margin-top: 50px">
          <span>{{comment.value}}</span><br>
          <span>{{moment(comment.createTime).format('YYYY-MM-DD HH:mm:ss')}}</span>
          <!--<span> {{this.$route.query.id}}</span>-->
        </div>
      </div>
    </el-card>
  </div>
  </div>
</template>

<script>
import NavMenu from '../common/NavMenu'
export default {
  inject: ['reload'],
  name: 'QuestionDetail',
  components: {NavMenu},
  data () {
    return {
      question: {
        title: '',
        text: '',
        createTime: '',
        commentList: []
      },
      comment: {
        qid: this.$route.query.id,
        value: '',
        createTime: ''
      }
    }
  },
  mounted () {
    this.loadQuestion()
  },
  methods: {
    loadQuestion () {
      var that = this
      this.axios.post('/findQuesById?id=' + this.$route.query.id).then(resp => {
        console.log(resp)
        if (resp && resp.status === 200) {
          that.question = resp.data
        }
      })
    },
    commentAdd () {
      this.comment.createTime = Date.parse(new Date())
      // var that = this
      this.axios.post('/commentAdd', {
        qid: this.comment.qid,
        value: this.comment.value,
        createTime: this.comment.createTime
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message({
            message: '评论发布成功',
            type: 'success'
          })
          this.reload()
        }
      })
    }
  }
}
</script>

<style scoped>
  /*@import "../../styles/markdown.css";*/
</style>
