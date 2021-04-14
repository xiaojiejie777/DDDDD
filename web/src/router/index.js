import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Home from '../views/Home'
import LiveChat from '../views/chat/LiveChat'
import AdminInfo from '../views/AdminInfo'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden: true
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children:[
        {
            path: '/chat',
            name: '在线聊天',
            component: LiveChat,
        },
        {
            path: '/admin/info',
            name: '个人中心',
            component: AdminInfo,
        }
    ]
  }

]

const router = new VueRouter({
  routes
})

export default router
