import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/view/Welcome'
import Users from '@/view/Users'
import Roles from '@/view/Roles'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '首页',
      component: Welcome
    },
    {
      path: '/users',
      name: '用户管理',
      component: Users
    },
    {
      path: '/roles',
      name: '角色管理',
      component: Roles
    }
  ]
})
