<template>
  <div class="users">
    <div class="page-header">
      <h1>Users</h1>
      <button @click="showAddModal = true" class="btn btn-primary">Add User</button>
    </div>

    <div v-if="loading" class="loading">Loading...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="users-table">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.userId">
            <td>{{ user.name }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.role }}</td>
            <td>
              <button @click="editUser(user)" class="btn btn-edit">Edit</button>
              <button @click="deleteUser(user.userId)" class="btn btn-delete">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Add/Edit Modal -->
    <div v-if="showAddModal" class="modal">
      <div class="modal-content">
        <h2>{{ editingUser ? 'Edit User' : 'Add User' }}</h2>
        <form @submit.prevent="saveUser">
          <div class="form-group">
            <label>Name</label>
            <input v-model="formData.name" required>
          </div>
          <div class="form-group">
            <label>Email</label>
            <input v-model="formData.email" type="email" required>
          </div>
          <div class="form-group">
            <label>Role</label>
            <select v-model="formData.role" required>
              <option value="">Select Role</option>
              <option value="USER">User</option>
              <option value="ADMIN">Admin</option>
            </select>
          </div>
          <div class="form-group">
            <label>Password</label>
            <input v-model="formData.password" type="password" :required="!editingUser">
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

const users = ref([])
const loading = ref(true)
const error = ref(null)
const showAddModal = ref(false)
const editingUser = ref(null)
const formData = ref({
  name: '',
  email: '',
  role: '',
  password: ''
})

const loadUsers = async () => {
  try {
    const response = await api.getUsers()
    users.value = response.data
  } catch (err) {
    error.value = 'Failed to load users'
    console.error(err)
  } finally {
    loading.value = false
  }
}

const editUser = (user) => {
  editingUser.value = user
  formData.value = { ...user, password: '' }
  showAddModal.value = true
}

const saveUser = async () => {
  try {
    if (editingUser.value) {
      await api.updateUser({ ...formData.value, userId: editingUser.value.userId })
    } else {
      await api.createUser(formData.value)
    }
    closeModal()
    loadUsers()
  } catch (err) {
    error.value = 'Failed to save user'
    console.error(err)
  }
}

const deleteUser = async (id) => {
  if (confirm('Are you sure you want to delete this user?')) {
    try {
      await api.deleteUser(id)
      loadUsers()
    } catch (err) {
      error.value = 'Failed to delete user'
      console.error(err)
    }
  }
}

const closeModal = () => {
  showAddModal.value = false
  editingUser.value = null
  formData.value = {
    name: '',
    email: '',
    role: '',
    password: ''
  }
}

onMounted(() => {
  loadUsers()
})
</script>

<style scoped>
.users {
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

.users-table {
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
}

thead {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

th {
  padding: 1rem;
  text-align: left;
  font-weight: 600;
}

td {
  padding: 1rem;
  border-bottom: 1px solid #eee;
}

tbody tr:hover {
  background: #f9f9f9;
}

.btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s;
  margin-right: 0.5rem;
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
.form-group select {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
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
