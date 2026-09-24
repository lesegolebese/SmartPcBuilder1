import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json'
  }
})

export default {
  // Products
  getProducts: () => api.get('/product/getall'),
  getProduct: (id) => api.get(`/product/read/${id}`),
  createProduct: (product) => api.post('/product/create', product),
  updateProduct: (product) => api.put('/product/update', product),
  deleteProduct: (id) => api.delete(`/product/delete/${id}`),

  // Categories
  getCategories: () => api.get('/category/getall'),
  getCategory: (id) => api.get(`/category/read/${id}`),
  createCategory: (category) => api.post('/category/create', category),
  updateCategory: (category) => api.put('/category/update', category),
  deleteCategory: (id) => api.delete(`/category/delete/${id}`),

  // Cart
  getCarts: () => api.get('/cart/getall'),
  getCart: (id) => api.get(`/cart/read/${id}`),
  createCart: (cart) => api.post('/cart/create', cart),
  updateCart: (cart) => api.put('/cart/update', cart),
  deleteCart: (id) => api.delete(`/cart/delete/${id}`),

  // PC Builds
  getPcBuilds: () => api.get('/pcbuild/getall'),
  getPcBuild: (id) => api.get(`/pcbuild/read/${id}`),
  createPcBuild: (pcBuild) => api.post('/pcbuild/create', pcBuild),
  updatePcBuild: (pcBuild) => api.put('/pcbuild/update', pcBuild),
  deletePcBuild: (id) => api.delete(`/pcbuild/delete/${id}`),

  // Users
  getUsers: () => api.get('/user/getall'),
  getUser: (id) => api.get(`/user/read/${id}`),
  createUser: (user) => api.post('/user/create', user),
  updateUser: (user) => api.put('/user/update', user),
  deleteUser: (id) => api.delete(`/user/delete/${id}`)
}
