# Todo_App_Spring_Boot
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcmF2ZWVuQGdtYWlsLmNvbSIsImlhdCI6MTc3NTM4NDQ3NSwiZXhwIjoxNzc1Mzg0Nzc1fQ.qvKRD3O6rytdpAphNYYLPoET-08XgwSKdXiDjfx9zW4

# 📝 Full Stack Todo Application

A complete **Full Stack Todo Application** built using **Spring Boot (Backend)** and **Vanilla JavaScript (Frontend)** with **JWT Authentication**.

This project demonstrates secure authentication, REST API development, and frontend-backend integration.

---

## 🚀 Features

### 🔐 Authentication

* User Registration
* User Login
* JWT Token-based Authentication
* Secure API access

### 📋 Todo Management

* Create Todo
* View All Todos
* Update Todo (title & status)
* Delete Todo
* Mark as Completed / Pending

### 🎨 Frontend

* Clean and modern UI
* Dynamic DOM updates
* Smooth user interactions
* Responsive design

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* MySQL / H2 Database

### Frontend

* HTML
* CSS (Modern UI + Glassmorphism)
* Vanilla JavaScript (Fetch API)

---

## 📁 Project Structure

```
project-root/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── config/
│   ├── filter/
│   └── utils/
│
├── frontend/
│   ├── login.html
│   ├── register.html
│   ├── todos.html
│   ├── script.js
│   └── style.css
```

---

## ⚙️ How to Run

### 🔹 Backend Setup

1. Clone the repository
2. Open in IDE (IntelliJ / Eclipse)
3. Configure database (MySQL or H2)
4. Run Spring Boot application

Backend runs on:

```
http://localhost:8080
```

---

### 🔹 Frontend Setup

1. Open frontend folder
2. Run using **Live Server (VS Code recommended)**

Example:

```
http://127.0.0.1:5500
```

---

## 🔐 API Endpoints

### Auth APIs

```
POST /auth/register
POST /auth/login
```

### Todo APIs

```
GET    /api/todo/getTodos
POST   /api/todo/create
PUT    /api/todo/update/{id}
DELETE /api/todo/delete/{id}
```

---

## 🔑 Authentication Flow

1. User logs in → receives JWT token
2. Token stored in browser (localStorage)
3. Token sent in every request:

```
Authorization: Bearer <token>
```

---

## 🧠 Key Concepts Learned

* Spring Security configuration
* JWT Authentication flow
* Custom filters in Spring Boot
* REST API design
* Frontend-Backend integration
* CORS handling
* Async JavaScript (fetch API)
* DOM manipulation

---

## 📸 Screenshots

(Add your screenshots here)

---

## 🚀 Future Enhancements

* Dark Mode 🌙
* Search & Filter Todos 🔍
* Pagination
* User-specific authorization (ownership)
* Deploy to cloud (Render / Netlify)

---

## 👨‍💻 Author

**Your Name**

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!

