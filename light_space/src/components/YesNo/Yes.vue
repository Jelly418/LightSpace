<template>
  <div id="container">
  <div id="todoApp">
    <header class="header">
      <h1> Yes</h1>
      <input  v-model="inputValue" @keyup.enter="add" autofocus="autofocus" autocomplete="off" placeholder="童鞋，你的优点" class="new-todo"/>
    </header>
    <!--事项展示区域-->
    <section class="main">
      <ul class="todo-list">
        <li class="todo" v-for="(item,index) in list" :key="index">
          <div class="view">
            <span class="index">{{index+1}}.</span>
            <label style="float: left">{{item.value}}</label>
            <!--<button class="destroy" @click="remove(index)"></button>-->
          </div>
        </li>
      </ul>
    </section>
    <!--计数和清空-->
    <footer class="footer" v-show="list.length!=0">
    <span class="todo-count">
       <strong>{{list.length}}</strong>&nbsp;&nbsp;items left
    </span>
      <!--<button  @click="clear" class="clear-completed">-->
        <!--Clear-->
      <!--</button>-->
    </footer>
    </div>
    <!--底部-->
  <div>
    <footer class="info"></footer>
  </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  inject: ['reload'],
  name: 'Yes',
  data () {
    return {
      list: [],
      inputValue: '',
      username: Cookies.get('username')
    }
  },
  methods: {
    add () {
      this.axios
        .post('/yesAdd?username=' + this.username, {
          value: this.inputValue
        })
        .then(successResponse => {
          this.reload()
        })
        .catch(failResponse => {
        })
    },
    // remove (index) {
    //   this.list.splice(index, 1)
    // },
    getYesList () {
      this.axios
        .post('/getYesList', {
          username: this.username
        })
        .then(successResponse => {
          if (successResponse.status === 200) {
            this.list = successResponse.data
          }
        })
        .catch(failResponse => {
        })
    }
  },
  mounted: function () {
    this.getYesList()
  }
}
</script>

<style scoped>
  @import "../../../static/css/yes.css";
</style>
