// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Vuex from 'vuex'
import animated from 'animate.css'
import VueParticles from 'vue-particles'
import APlayer from '@moefe/vue-aplayer'
import moment from 'moment'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

Vue.use(ElementUI)
Vue.use(VueAxios, axios)
Vue.use(Vuex)
Vue.use(animated)
Vue.use(VueParticles)
Vue.use(APlayer)
Vue.prototype.moment = moment
Vue.use(mavonEditor)

// // //设置反向代理，前端请求默认发送到http://localhost:8443/api
// axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'

// 阻止vue在启动时生成生产提示
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  components: { App },
  template: '<App/>'
})
new Vue({
  'el': '#markdown',
  data () {
    return { value: '' }
  }
})
