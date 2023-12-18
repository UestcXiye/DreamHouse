import Vue from 'vue'
import VueRouter from 'vue-router'
import LikesSortedView from '../views/LikesSortedView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '排行',
    component: LikesSortedView
  },
  {
    path: '/Newest',
    name: '最新',
    // route level code-splitting
    // this generates a separate chunk (Newest.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/NewestView.vue')
  },
  {
    path: '/Random',
    name: '随机',
    component: () => import('../views/RandomView.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
