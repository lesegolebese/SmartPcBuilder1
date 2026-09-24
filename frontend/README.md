# Smart PC Builder - Frontend

A modern Vue.js frontend for the Smart PC Builder application.

## Features

- **Product Management**: Browse, add, edit, and delete products
- **Category Management**: Organize products into categories
- **PC Builder**: Create and manage custom PC builds
- **Shopping Cart**: Add products to cart, manage quantities, and checkout
- **User Management**: Manage users and roles
- **Modern UI**: Beautiful gradient design with smooth animations

## Tech Stack

- Vue 3 (Composition API)
- Vue Router for navigation
- Pinia for state management
- Axios for API calls
- Vite for build tooling

## Prerequisites

- Node.js (v16 or higher)
- npm or yarn

## Installation

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

## Running the Application

1. Start the Spring Boot backend on port 8080
2. Start the Vue frontend:
```bash
npm run dev
```

3. Open your browser and navigate to `http://localhost:5173`

## API Configuration

The frontend is configured to proxy API requests to the Spring Boot backend running on `http://localhost:8080`. This is handled by the Vite proxy configuration in `vite.config.js`.

## Building for Production

```bash
npm run build
```

The built files will be in the `dist` directory.

## Project Structure

```
frontend/
├── src/
│   ├── api/           # API service layer
│   ├── components/    # Reusable components
│   ├── router/        # Vue Router configuration
│   ├── stores/        # Pinia stores
│   ├── views/         # Page components
│   ├── App.vue        # Root component
│   ├── main.js        # Entry point
│   └── style.css      # Global styles
├── index.html         # HTML template
├── package.json       # Dependencies
└── vite.config.js     # Vite configuration
```

## Available Pages

- **Home**: Landing page with feature highlights
- **Products**: Product catalog with CRUD operations
- **Categories**: Category management
- **PC Builder**: Create and manage PC builds
- **Cart**: Shopping cart with checkout
- **Users**: User management
