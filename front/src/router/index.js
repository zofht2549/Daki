import Vue from 'vue'
import VueRouter from 'vue-router'

// Landing Page
import LandingPage from '@/views/LandingPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name: 'LandingPage',
    component: LandingPage
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('../views/Signup.vue')
  },
  {
    path: '/main',
    name: 'Main',
    component: () => import('../views/Main.vue')
  },
  {
    path:'/mypage',
    name:'Mypage',
    component: () => import('../views/MyPage.vue')
  },
  {
    path:'/diary-create',
    name:'DiaryCreate',
    component: () => import('../views/DiaryCreate.vue')
  },
  {
    path:'/oauth',
    name:'Oauth',
    component: () => import('../views/Oauth.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// eslint-disable-next-line no-unused-vars
router.beforeEach((to, from, next) => {
  if (to.name == 'Main' && !to.query.tab){
    next({ name: 'Main', query: { tab: 'calendar' } })
  }
  next()
})

export default router
