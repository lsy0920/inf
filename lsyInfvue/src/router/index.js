import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Staff from '@/components/information/Staff'
import StaffUser from '@/components/information/StaffUser'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/information',
      name: 'information',
      component: Staff
    },
    {
      path: '/inftable',
      name: 'inftable',
      component: StaffUser
    }
  ]
})
