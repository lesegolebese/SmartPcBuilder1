<template>
  <div class="pc-builder">
    <div class="page-header">
      <h1>PC Builder</h1>
      <button @click="showAddModal = true" class="btn btn-primary">Create New Build</button>
    </div>

    <div v-if="loading" class="loading">Loading...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="builds-grid">
      <div v-for="build in pcBuilds" :key="build.buildId" class="build-card">
        <div class="build-header">
          <h3>{{ build.buildName || 'Untitled Build' }}</h3>
          <span class="build-date">{{ formatDate(build.createdAt) }}</span>
        </div>
        <div class="build-info">
          <p><strong>Total Price:</strong> ${{ build.totalPrice || 0 }}</p>
          <p><strong>Components:</strong> {{ build.componentCount || 0 }}</p>
        </div>
        <div class="build-actions">
          <button @click="viewBuild(build)" class="btn btn-view">View Details</button>
          <button @click="editBuild(build)" class="btn btn-edit">Edit</button>
          <button @click="deleteBuild(build.buildId)" class="btn btn-delete">Delete</button>
        </div>
      </div>
    </div>

    <!-- Add/Edit Modal -->
    <div v-if="showAddModal" class="modal">
      <div class="modal-content">
        <h2>{{ editingBuild ? 'Edit Build' : 'Create New Build' }}</h2>
        <form @submit.prevent="saveBuild">
          <div class="form-group">
            <label>Build Name</label>
            <input v-model="formData.buildName" required>
          </div>
          <div class="form-group">
            <label>Description</label>
            <textarea v-model="formData.description"></textarea>
          </div>
          <div class="form-group">
            <label>Total Price</label>
            <input v-model.number="formData.totalPrice" type="number" step="0.01">
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

const pcBuilds = ref([])
const loading = ref(true)
const error = ref(null)
const showAddModal = ref(false)
const editingBuild = ref(null)
const formData = ref({
  buildName: '',
  description: '',
  totalPrice: 0
})

const loadPcBuilds = async () => {
  try {
    const response = await api.getPcBuilds()
    pcBuilds.value = response.data
  } catch (err) {
    error.value = 'Failed to load PC builds'
    console.error(err)
  } finally {
    loading.value = false
  }
}

const viewBuild = (build) => {
  alert(`Viewing build: ${build.buildName || 'Untitled'}`)
}

const editBuild = (build) => {
  editingBuild.value = build
  formData.value = { ...build }
  showAddModal.value = true
}

const saveBuild = async () => {
  try {
    if (editingBuild.value) {
      await api.updatePcBuild({ ...formData.value, buildId: editingBuild.value.buildId })
    } else {
      await api.createPcBuild(formData.value)
    }
    closeModal()
    loadPcBuilds()
  } catch (err) {
    error.value = 'Failed to save build'
    console.error(err)
  }
}

const deleteBuild = async (id) => {
  if (confirm('Are you sure you want to delete this build?')) {
    try {
      await api.deletePcBuild(id)
      loadPcBuilds()
    } catch (err) {
      error.value = 'Failed to delete build'
      console.error(err)
    }
  }
}

const closeModal = () => {
  showAddModal.value = false
  editingBuild.value = null
  formData.value = {
    buildName: '',
    description: '',
    totalPrice: 0
  }
}

const formatDate = (date) => {
  if (!date) return 'N/A'
  return new Date(date).toLocaleDateString()
}

onMounted(() => {
  loadPcBuilds()
})
</script>

<style scoped>
.pc-builder {
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

.builds-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 2rem;
}

.build-card {
  background: white;
  padding: 2rem;
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.build-card:hover {
  transform: translateY(-5px);
}

.build-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
  padding-bottom: 1rem;
  border-bottom: 1px solid #eee;
}

.build-header h3 {
  margin: 0;
  color: #333;
}

.build-date {
  color: #999;
  font-size: 0.9rem;
}

.build-info {
  margin-bottom: 1.5rem;
}

.build-info p {
  margin: 0.5rem 0;
  color: #666;
}

.build-actions {
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

.btn-view {
  background: #2196f3;
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
