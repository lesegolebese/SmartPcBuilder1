import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Products from '../views/Products.vue'
import Categories from '../views/Categories.vue'
import PcBuilder from '../views/PcBuilder.vue'
import Cart from '../views/Cart.vue'
import Users from '../views/Users.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/products',
    name: 'Products',
    component: Products
  },
  {
    path: '/categories',
    name: 'Categories',
    component: Categories
  },
  {
    path: '/pc-builder',
    name: 'PcBuilder',
    component: PcBuilder
  },
  {
    path: '/cart',
    name: 'Cart',
    component: Cart
  },
  {
    path: '/users',
    name: 'Users',
    component: Users
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
