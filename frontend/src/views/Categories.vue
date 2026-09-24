<template>
  <div class="categories">
    <div class="page-header">
      <h1>Categories</h1>
      <button @click="showAddModal = true" class="btn btn-primary">Add Category</button>
    </div>

    <div v-if="loading" class="loading">Loading...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="categories-grid">
      <div v-for="category in categories" :key="category.categoryId" class="category-card">
        <div class="category-icon">📁</div>
        <h3>{{ category.categoryName }}</h3>
        <p>{{ category.description }}</p>
        <div class="category-actions">
          <button @click="editCategory(category)" class="btn btn-edit">Edit</button>
          <button @click="deleteCategory(category.categoryId)" class="btn btn-delete">Delete</button>
        </div>
      </div>
    </div>

    <!-- Add/Edit Modal -->
    <div v-if="showAddModal" class="modal">
      <div class="modal-content">
        <h2>{{ editingCategory ? 'Edit Category' : 'Add Category' }}</h2>
        <form @submit.prevent="saveCategory">
          <div class="form-group">
            <label>Category Name</label>
            <input v-model="formData.categoryName" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <textarea v-model="formData.description" required></textarea>
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

const categories = ref([])
const loading = ref(true)
const error = ref(null)
const showAddModal = ref(false)
const editingCategory = ref(null)
const formData = ref({
  categoryName: '',
  description: ''
})

const loadCategories = async () => {
  try {
    const response = await api.getCategories()
    categories.value = response.data
  } catch (err) {
    error.value = 'Failed to load categories'
    console.error(err)
  } finally {
    loading.value = false
  }
}

const editCategory = (category) => {
  editingCategory.value = category
  formData.value = { ...category }
  showAddModal.value = true
}

const saveCategory = async () => {
  try {
    if (editingCategory.value) {
      await api.updateCategory({ ...formData.value, categoryId: editingCategory.value.categoryId })
    } else {
      await api.createCategory(formData.value)
    }
    closeModal()
    loadCategories()
  } catch (err) {
    error.value = 'Failed to save category'
    console.error(err)
  }
}

const deleteCategory = async (id) => {
  if (confirm('Are you sure you want to delete this category?')) {
    try {
      await api.deleteCategory(id)
      loadCategories()
    } catch (err) {
      error.value = 'Failed to delete category'
      console.error(err)
    }
  }
}

const closeModal = () => {
  showAddModal.value = false
  editingCategory.value = null
  formData.value = {
    categoryName: '',
    description: ''
  }
}

onMounted(() => {
  loadCategories()
})
</script>

<style scoped>
.categories {
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

.categories-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 2rem;
}

.category-card {
  background: white;
  padding: 2rem;
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  text-align: center;
  transition: transform 0.3s;
}

.category-card:hover {
  transform: translateY(-5px);
}

.category-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.category-card h3 {
  margin: 0 0 0.5rem 0;
  color: #333;
}

.category-card p {
  color: #666;
  margin-bottom: 1.5rem;
}

.category-actions {
  display: flex;
  gap: 0.5rem;
  justify-content: center;
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
