<template>
  <div class="products">
    <div class="page-header">
      <h1>Products</h1>
      <button @click="showAddModal = true" class="btn btn-primary">Add Product</button>
    </div>

    <div v-if="loading" class="loading">Loading...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="products-grid">
      <div v-for="product in products" :key="product.productId" class="product-card">
        <div class="product-image">
          <div class="placeholder-image">📦</div>
        </div>
        <div class="product-info">
          <h3>{{ product.productName }}</h3>
          <p class="product-description">{{ product.description }}</p>
          <div class="product-details">
            <span class="price">${{ product.price }}</span>
            <span class="stock">Stock: {{ product.stockQuantity }}</span>
          </div>
          <div class="product-actions">
            <button @click="addToCart(product)" class="btn btn-add">Add to Cart</button>
            <button @click="editProduct(product)" class="btn btn-edit">Edit</button>
            <button @click="deleteProduct(product.productId)" class="btn btn-delete">Delete</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Add/Edit Modal -->
    <div v-if="showAddModal" class="modal">
      <div class="modal-content">
        <h2>{{ editingProduct ? 'Edit Product' : 'Add Product' }}</h2>
        <form @submit.prevent="saveProduct">
          <div class="form-group">
            <label>Product Name</label>
            <input v-model="formData.productName" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <textarea v-model="formData.description" required></textarea>
          </div>
          <div class="form-group">
            <label>Price</label>
            <input v-model.number="formData.price" type="number" step="0.01" required>
          </div>
          <div class="form-group">
            <label>Stock Quantity</label>
            <input v-model.number="formData.stockQuantity" type="number" required>
          </div>
          <div class="form-actions">
            <button type="submit" class="btn btn-primary">Save</button>
            <button type="button" @click="closeModal" class="btn btn-secondary">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '../api'
import { useCartStore } from '../stores/cart'

const cartStore = useCartStore()
const products = ref([])
const loading = ref(true)
const error = ref(null)
const showAddModal = ref(false)
const editingProduct = ref(null)
const formData = ref({
  productName: '',
  description: '',
  price: 0,
  stockQuantity: 0
})

const loadProducts = async () => {
  try {
    const response = await api.getProducts()
    products.value = response.data
  } catch (err) {
    error.value = 'Failed to load products'
    console.error(err)
  } finally {
    loading.value = false
  }
}

const addToCart = (product) => {
  cartStore.addItem(product)
  alert('Product added to cart!')
}

const editProduct = (product) => {
  editingProduct.value = product
  formData.value = { ...product }
  showAddModal.value = true
}

const saveProduct = async () => {
  try {
    if (editingProduct.value) {
      await api.updateProduct({ ...formData.value, productId: editingProduct.value.productId })
    } else {
      await api.createProduct(formData.value)
    }
    closeModal()
    loadProducts()
  } catch (err) {
    error.value = 'Failed to save product'
    console.error(err)
  }
}

const deleteProduct = async (id) => {
  if (confirm('Are you sure you want to delete this product?')) {
    try {
      await api.deleteProduct(id)
      loadProducts()
    } catch (err) {
      error.value = 'Failed to delete product'
      console.error(err)
    }
  }
}

const closeModal = () => {
  showAddModal.value = false
  editingProduct.value = null
  formData.value = {
    productName: '',
    description: '',
    price: 0,
    stockQuantity: 0
  }
}

onMounted(() => {
  loadProducts()
})
</script>

<style scoped>
.products {
  background: rgba(255, 255, 255, 0.95);
  padding: 2rem;
  border-radius: 20px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.page-header h1 {
  margin: 0;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 2rem;
}

.product-card {
  background: white;
  border-radius: 15px;
  overflow: hidden;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.product-card:hover {
  transform: translateY(-5px);
}

.product-image {
  height: 200px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
}

.placeholder-image {
  font-size: 4rem;
}

.product-info {
  padding: 1.5rem;
}

.product-info h3 {
  margin: 0 0 0.5rem 0;
  color: #333;
}

.product-description {
  color: #666;
  margin-bottom: 1rem;
  font-size: 0.9rem;
}

.product-details {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
}

.price {
  font-weight: bold;
  color: #667eea;
  font-size: 1.2rem;
}

.stock {
  color: #666;
}

.product-actions {
  display: flex;
  gap: 0.5rem;
}

.btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s;
}

.btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.btn-secondary {
  background: #e0e0e0;
  color: #333;
}

.btn-add {
  background: #4caf50;
  color: white;
  flex: 1;
}

.btn-edit {
  background: #ff9800;
  color: white;
}

.btn-delete {
  background: #f44336;
  color: white;
}

.btn:hover {
  opacity: 0.9;
  transform: translateY(-2px);
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 2rem;
  border-radius: 15px;
  width: 90%;
  max-width: 500px;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
}

.form-group textarea {
  resize: vertical;
  min-height: 100px;
}

.form-actions {
  display: flex;
  gap: 1rem;
  margin-top: 1.5rem;
}

.loading,
.error {
  text-align: center;
  padding: 2rem;
  font-size: 1.2rem;
}

.error {
  color: #f44336;
}
</style>
