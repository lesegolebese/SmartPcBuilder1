<template>
  <div class="home">
    <div class="hero">
      <h1>Welcome to Smart PC Builder</h1>
      <p>Build your dream PC with our intelligent compatibility system</p>
      <div class="hero-buttons">
        <router-link to="/products" class="btn btn-primary">Browse Products</router-link>
        <router-link to="/pc-builder" class="btn btn-secondary">Start Building</router-link>
      </div>
    </div>

    <div class="features">
      <div class="feature-card">
        <div class="feature-icon">🎯</div>
        <h3>Smart Compatibility</h3>
        <p>Our system ensures all components work together seamlessly</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">📦</div>
        <h3>Wide Selection</h3>
        <p>Choose from hundreds of quality PC components</p>
      </div>
      <div class="feature-card">
        <div class="feature-icon">💰</div>
        <h3>Best Prices</h3>
        <p>Competitive pricing on all PC parts</p>
      </div>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.home {
  text-align: center;
}

.hero {
  background: rgba(255, 255, 255, 0.95);
  padding: 4rem 2rem;
  border-radius: 20px;
  margin-bottom: 3rem;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
}

.hero h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero p {
  font-size: 1.2rem;
  color: #666;
  margin-bottom: 2rem;
}

.hero-buttons {
  display: flex;
  gap: 1rem;
  justify-content: center;
}

.btn {
  padding: 1rem 2rem;
  border-radius: 10px;
  text-decoration: none;
  font-weight: 600;
  transition: transform 0.3s, box-shadow 0.3s;
}

.btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

.btn-primary {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.btn-secondary {
  background: white;
  color: #667eea;
  border: 2px solid #667eea;
}

.features {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 2rem;
}

.feature-card {
  background: rgba(255, 255, 255, 0.95);
  padding: 2rem;
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.feature-card:hover {
  transform: translateY(-5px);
}

.feature-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.feature-card h3 {
  margin-bottom: 0.5rem;
  color: #333;
}

.feature-card p {
  color: #666;
}
</style>
