import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useCartStore = defineStore('cart', () => {
  const items = ref([])

  const addItem = (product) => {
    const existingItem = items.value.find(item => item.productId === product.productId)
    if (existingItem) {
      existingItem.quantity++
    } else {
      items.value.push({ ...product, quantity: 1 })
    }
  }

  const removeItem = (productId) => {
    items.value = items.value.filter(item => item.productId !== productId)
  }

  const updateQuantity = (productId, quantity) => {
    const item = items.value.find(item => item.productId === productId)
    if (item) {
      item.quantity = quantity
    }
  }

  const clearCart = () => {
    items.value = []
  }

  const total = () => {
    return items.value.reduce((sum, item) => sum + (item.price * item.quantity), 0)
  }

  return {
    items,
    addItem,
    removeItem,
    updateQuantity,
    clearCart,
    total
  }
})
