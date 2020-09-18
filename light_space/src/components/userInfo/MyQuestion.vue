<template>
      <div style="margin-top: 20px" class="articles-area">
        <el-card style="text-align: left">
        <div v-for="question in myQuestionList" :key="question.id" style="margin-bottom: 50px">
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
</template>

<script>
import Cookies from 'js-cookie'
export default {
  name: 'MyQuestion',
  data () {
    return {
      myQuestionList: [],
      username: Cookies.get('username')
    }
  },
  methods: {
    myQuestionDisplay () {
      this.axios
        .post('/myQuestionDisplay?username=' + this.username)
        .then(successResponse => {
          this.myQuestionList = successResponse.data
        })
        .catch(failResponse => {
        })
    }
  },
  mounted () {
    this.myQuestionDisplay()
  }
}
</script>

<style scoped>
  .articles-area {
    width: 990px;
    height: 750px;
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
