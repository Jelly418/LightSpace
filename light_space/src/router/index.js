import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/AppIndex'
import Login from '../components/Login'
import Home from '../components/Home'
import Video from '../components/video/Video'
import UpToYou from '../components/upToYou/UpToYou'
import YesNoIndex from '../components/yesNo/YesNoIndex'
import NoteIndex from '../components/note/NoteIndex'
import NoteDetail from '../components/note/NoteDetail'
import RecommendIndex from '../components/recommend/RecommendIndex'
import Registration from '../components/registration/Registration'
import FindPassword0 from '../components/registration/FindPassword0'
import FindPassword1 from '../components/registration/FindPassword1'
import FindPassword2 from '../components/registration/FindPassword2'
import QuestionIndex from '../components/question/QuestionIndex'
import QuestionDetail from '../components/question/QuestionDetail'
import MyQuestion from '../components/userInfo/MyQuestion'
import UserInfoIndex from '../components/userInfo/UserInfoIndex'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex
        },
        {
          path: '/video',
          name: 'Video',
          component: Video
        },
        {
          path: '/upToYou',
          name: 'upToYou',
          component: UpToYou
        },
        {
          path: '/yesNoIndex',
          name: 'yesNoIndex',
          component: YesNoIndex
        },
        {
          path: '/noteIndex',
          name: 'noteIndex',
          component: NoteIndex
        },
        {
          path: '/recommend',
          name: 'recommend',
          component: RecommendIndex
        },
        {
          path: '/question',
          name: 'question',
          component: QuestionIndex
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/registration',
      name: 'registration',
      component: Registration
    },
    {
      path: '/findPassword0',
      name: 'findPassword0',
      component: FindPassword0
    },
    {
      path: '/findPassword1',
      name: 'findPassword1',
      component: FindPassword1
    },
    {
      path: '/findPassword2',
      name: 'findPassword2',
      component: FindPassword2
    },
    {
      path: '/questionDetail',
      name: 'questionDetail',
      component: QuestionDetail
    },
    {
      path: '/userInfoIndex',
      name: 'userInfoIndex',
      component: UserInfoIndex
    },
    {
      path: '/myQuestion',
      name: 'myQuestion',
      component: MyQuestion
    },
    {
      path: '/noteDetail',
      name: 'noteDetail',
      component: NoteDetail
    }
  ]
})
