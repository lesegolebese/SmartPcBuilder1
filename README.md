# PCSmartBuilder - E-Commerce Management System

PCSmartBuilder is a specialized e-commerce platform designed to simplify the process of purchasing custom-built desktop computers and pre-configured hardware components. The system addresses common hardware assembly pain points by validating component compatibility dynamically, while handling traditional e-commerce operations like customer accounts, shipping addresses, active shopping carts, and order execution.

This repository hosts the core domain layer of the application, structured using **Domain-Driven Design (DDD)** principles. All domain entities utilize the **Builder Pattern** to guarantee immutability, enforce safe instantiation states, and maintain zero-argument protected constructors in compliance with enterprise Java specifications.

---

## 1. Domain Model Overview

The system architecture maps directly to our approved system blueprint.

### Architectural Blueprint (UML Class Diagram)
![PCSmartBuilder UML Structure]
<img width="1280" height="1276" alt="WhatsApp Image 2026-06-21 at 8 04 48 PM" src="https://github.com/user-attachments/assets/7509e658-b062-4703-9cfc-45630f7e87fb" />

### Key Sub-Domain Domains Implemented:
* **User & Profiles Domain (`User`, `Admin`, `Address`):** Manages secure system access, delivery coordinates, and operational permissions.
* **Shopping & Ordering Domain (`Cart`, `CartItem`, `Order`, `OrderItem`):** Handles operational transactional lifecycles from building a components list to checkout completion.
* **Custom Configuration Domain (`PcBuild`, `PcBuildItem`, `CompatibilityRule`):** The logical core of **PCSmartBuilder**. It tracks user-customized PC configurations and evaluates component attributes against rules to prevent hardware mismatches.
* **Catalog & Feedback Domain (`Product`, `Specification`, `Category`, `Review`):** Manages inventory metadata, granular sorting parameters, and customer product reviews.

---

## 2. Technical Design Decisions

### The Builder Pattern
To safeguard the integrity of core business rules, entities do not expose public setters. Modification or object creation goes entirely through an inner static `Builder` class. This guarantees that an entity cannot exist in an incomplete or broken state.

---

## 3. Frontend Application

The project includes a modern Vue.js frontend that provides a user-friendly interface for managing PC builds, products, and orders.

### Tech Stack
- **Vue 3** with Composition API
- **Vue Router** for navigation
- **Pinia** for state management
- **Axios** for API communication
- **Vite** for build tooling

### Features
- **Home Page**: Landing page with feature highlights
- **Products**: Browse, add, edit, and delete products
- **Categories**: Manage product categories
- **PC Builder**: Create and manage custom PC builds
- **Shopping Cart**: Add products, manage quantities, and checkout
- **Users**: User management with role-based access

### Running the Frontend

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

4. Open your browser at `http://localhost:5173`

**Note**: The Spring Boot backend must be running on port 8080 for the frontend to function properly.

### API Integration
The frontend uses a Vite proxy to forward API requests to the Spring Boot backend. All API calls are configured in `frontend/src/api/index.js` and automatically route to `http://localhost:8080`.

### Building for Production
```bash
cd frontend
npm run build
```

For more details, see the [frontend README](frontend/README.md).

---

## 4. Running the Full Application

### Prerequisites
- Java 21 or higher
- Maven
- Node.js 16 or higher
- MySQL database

### Steps

1. **Configure Database**
   - Update `src/main/resources/application.properties` with your MySQL credentials
   - Ensure the database exists

2. **Start the Backend**
```bash
mvn spring-boot:run
```

3. **Start the Frontend** (in a new terminal)
```bash
cd frontend
npm install
npm run dev
```

4. **Access the Application**
   - Frontend: http://localhost:5173
   - Backend API: http://localhost:8080

