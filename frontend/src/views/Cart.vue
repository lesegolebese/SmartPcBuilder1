<template>
  <div class="cart">
    <div class="page-header">
      <h1>Shopping Cart</h1>
      <button v-if="cartStore.items.length > 0" @click="clearCart" class="btn btn-danger">Clear Cart</button>
    </div>

    <div v-if="cartStore.items.length === 0" class="empty-cart">
      <div class="empty-icon">🛒</div>
      <h2>Your cart is empty</h2>
      <p>Add some products to get started!</p>
      <router-link to="/products" class="btn btn-primary">Browse Products</router-link>
    </div>
    <div v-else>
      <div class="cart-items">
        <div v-for="item in cartStore.items" :key="item.productId" class="cart-item">
          <div class="item-info">
            <h3>{{ item.productName }}</h3>
            <p class="item-description">{{ item.description }}</p>
            <p class="item-price">${{ item.price }}</p>
          </div>
          <div class="item-quantity">
            <button @click="updateQuantity(item.productId, item.quantity - 1)" class="qty-btn">-</button>
            <span>{{ item.quantity }}</span>
            <button @click="updateQuantity(item.productId, item.quantity + 1)" class="qty-btn">+</button>
          </div>
          <div class="item-total">
            <p>${{ (item.price * item.quantity).toFixed(2) }}</p>
            <button @click="removeItem(item.productId)" class="btn btn-remove">Remove</button>
          </div>
        </div>
      </div>

      <div class="cart-summary">
        <div class="summary-row">
          <span>Subtotal:</span>
          <span>${{ cartStore.total().toFixed(2) }}</span>
        </div>
        <div class="summary-row">
          <span>Tax (15%):</span>
          <span>${{ (cartStore.total() * 0.15).toFixed(2) }}</span>
        </div>
        <div class="summary-row total">
          <span>Total:</span>
          <span>${{ (cartStore.total() * 1.15).toFixed(2) }}</span>
        </div>
        <button @click="checkout" class="btn btn-checkout">Proceed to Checkout</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useCartStore } from '../stores/cart'
import { useRouter } from 'vue-router'

const cartStore = useCartStore()
const router = useRouter()

const updateQuantity = (productId, quantity) => {
  if (quantity <= 0) {
    cartStore.removeItem(productId)
  } else {
    cartStore.updateQuantity(productId, quantity)
  }
}

const removeItem = (productId) => {
  cartStore.removeItem(productId)
}

const clearCart = () => {
  if (confirm('Are you sure you want to clear your cart?')) {
    cartStore.clearCart()
  }
}

const checkout = () => {
  alert('Proceeding to checkout...')
  // Here you would integrate with your checkout/payment system
}
</script>

<style scoped>
.cart {
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

.empty-cart {
  text-align: center;
  padding: 4rem 2rem;
}

.empty-icon {
  font-size: 5rem;
  margin-bottom: 1rem;
}

.empty-cart h2 {
  margin: 0 0 0.5rem 0;
  color: #333;
}

.empty-cart p {
  color: #666;
  margin-bottom: 2rem;
}

.cart-items {
  margin-bottom: 2rem;
}

.cart-item {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr;
  gap: 2rem;
  padding: 1.5rem;
  background: white;
  border-radius: 10px;
  margin-bottom: 1rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  align-items: center;
}

.item-info h3 {
  margin: 0 0 0.5rem 0;
  color: #333;
}

.item-description {
  color: #666;
  margin: 0.5rem 0;
  font-size: 0.9rem;
}

.item-price {
  font-weight: bold;
  color: #667eea;
  margin: 0;
}

.item-quantity {
  display: flex;
  align-items: center;
  gap: 1rem;
  justify-content: center;
}

.qty-btn {
  width: 35px;
  height: 35px;
  border: 1px solid #ddd;
  background: white;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1.2rem;
  transition: all 0.3s;
}

.qty-btn:hover {
  background: #f0f0f0;
}

.item-total {
  text-align: right;
}

.item-total p {
  font-weight: bold;
  font-size: 1.2rem;
  color: #667eea;
  margin: 0 0 0.5rem 0;
}

.cart-summary {
  background: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.summary-row {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
  color: #666;
}

.summary-row.total {
  font-size: 1.3rem;
  font-weight: bold;
  color: #333;
  margin-top: 1.5rem;
  padding-top: 1rem;
  border-top: 2px solid #eee;
}

.btn {
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 600;
  transition: all 0.3s;
  text-decoration: none;
  display: inline-block;
}

.btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.btn-danger {
  background: #f44336;
  color: white;
}

.btn-remove {
  background: #f44336;
  color: white;
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
}

.btn-checkout {
  width: 100%;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  margin-top: 1.5rem;
  padding: 1rem;
}

.btn:hover {
  opacity: 0.9;
  transform: translateY(-2px);
}
</style>
