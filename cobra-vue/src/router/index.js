import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    },{
      path: '/home',
      name: 'Home',
      component: HelloWorld,
      hidden: true,
      meta:{
        requireAuth: true
      }
    }
  ]
})
