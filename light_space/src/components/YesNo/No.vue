<template>
  <div class="container">
    <h1 >No</h1>
    <div class="header">
      <!--<section>-->
        <form  id="form">
          <input type="text" id="title"  placeholder="童鞋，你的缺点" required="required" autocomplete="off"  v-model="inputValue" @keyup.enter="addToList()"/>
        </form>
      <!--</section>-->
    </div>
    <section>
      <h2 onclick="save()">正在进行 <span id="todocount"></span></h2>
      <ol id="todolist" class="demo-box">
        <li v-for="(item,index) in noList" :key="index" v-show="item.flag">{{item.value}}<p @click="addToDoneList(item.id)">-</p></li>

      </ol>
      <h2>已经完成 <span id="donecount"></span></h2>
      <ul id="donelist">
        <li v-for="(item,index) in noList" :key="index" v-show="!item.flag">{{item.value}}</li>
      </ul>
    </section>
    <footer>
      <h2>Be A Better Man</h2>
    </footer>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  inject: ['reload'],
  name: 'No',
  data () {
    return {
      noList: [],
      inputValue: '',
      username: Cookies.get('username')
    }
  },
  methods: {
    addToList () {
      this.axios
        .post('/noAdd?username=' + this.username, {
          value: this.inputValue
        })
        .then(successResponse => {
          this.$router.go(0)
        })
        .catch(failResponse => {
        })
    },
    addToDoneList (id) {
      this.axios
        .post('/addToDoneList?id=' + id)
        .then(successResponse => {
          this.reload()
        })
        .catch(failResponse => {
        })
    },
    getNoList () {
      this.axios
        .post('/getNoList', {
          username: this.username
        })
        .then(successResponse => {
          if (successResponse.status === 200) {
            console.log(successResponse)
            this.noList = successResponse.data
          }
        })
        .catch(failResponse => {
        })
    }
  },
  mounted: function () {
    this.getNoList()
  }
}
</script>

<style scoped>
  @import "../../../static/css/no.css";
</style>
