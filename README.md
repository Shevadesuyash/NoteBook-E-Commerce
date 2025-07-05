# 🛒 NoteBook E-Commerce – Full Stack App (Spring Boot + Angular)

A full-stack notebook-selling e-commerce application built with a secure and scalable **Spring Boot backend** and a dynamic **Angular frontend**, developed during the course **"Building FullStack E-Commerce App using SpringBoot & Angular"** by Rahul Sahay.

> ⚠️ Note: The frontend is adapted from the course and customized to align with the backend APIs developed independently.

---

## 🌐 Tech Stack Overview

| Layer      | Technology                       |
| ---------- | -------------------------------- |
| Backend    | Java 21, Spring Boot 3.5.0       |
| Frontend   | Angular                          |
| Security   | Spring Security + JWT            |
| Database   | PostgreSQL (via Spring Data JPA) |
| ORM        | Hibernate                        |
| Caching    | Embedded Redis                   |
| Migrations | Liquibase                        |
| Build Tool | Maven                            |

---

## ✅ Key Features

### Backend

* 🔐 JWT-based user authentication & authorization
* 🛍️ Product, Brand, and Type management
* 🧺 Basket service with add/delete operations
* 🔍 Product search, pagination, filtering, and sorting
* 🧾 RESTful APIs following clean architecture principles
* 🧪 Liquibase for versioned database migrations
* 🚀 Embedded Redis support for dev environment caching

### Frontend

* 🛒 Fully responsive Angular UI
* 👤 Login/Register + Token-based session handling
* 📦 Product listing with filtering by brand/type
* 🛍️ Shopping cart (basket) integration
* ✅ Checkout workflow with review and address

---

## 📁 Backend Project Structure

```
notebooksite/
├── controller/       --> REST APIs (Auth, Basket, Product)
├── entity/           --> JPA entities
├── model/            --> DTOs for request/response
├── repository/       --> Data access layer
├── service/          --> Interfaces + implementations
├── security/         --> JWT filters, config, utils
├── configuration/    --> Redis, Web, CORS configs
└── resources/db/     --> Liquibase changelogs + data
```

---

## 📡 API Endpoints

### 🔐 Authentication (`/auth`)

| Method | Endpoint | Description                  |
| ------ | -------- | ---------------------------- |
| POST   | `/login` | Login with username/password |
| GET    | `/user`  | Get current user details     |

> ✅ Send JWT in header: `Authorization: Bearer <token>`

### 🧺 Basket API (`/api/baskets`)

| Method | Endpoint | Description         |
| ------ | -------- | ------------------- |
| GET    | `/`      | Get all baskets     |
| GET    | `/{id}`  | Get basket by ID    |
| POST   | `/`      | Create new basket   |
| DELETE | `/{id}`  | Delete basket by ID |

### 🛍️ Product API (`/api`)

| Method | Endpoint            | Description               |
| ------ | ------------------- | ------------------------- |
| GET    | `/getProduct`       | List all products         |
| GET    | `/getProducts`      | Paginated/filterable list |
| GET    | `/{id}`             | Get product by ID         |
| GET    | `/getBrand`         | Get product brands        |
| GET    | `/getType`          | Get product types         |
| GET    | `/search/{keyword}` | Search product by name    |

---

## ⚙️ Running Locally

### Prerequisites

* Java 21
* Maven
* Node.js & Angular CLI (for frontend)

### Backend

```bash
# Navigate to root project folder
./mvnw spring-boot:run
```

### Frontend

```bash
# Navigate to client folder
cd client
npm install
ng serve
```

### Access URLs

* Backend: `http://localhost:8080`
* Frontend: `http://localhost:4200`

---

## 📦 Maven Dependencies Highlights

* `spring-boot-starter-web`, `data-jpa`, `security`
* `jjwt` for JWT token handling
* `liquibase-core` for DB migrations
* `spring-boot-starter-data-redis` + `embedded-redis`
* `spring-ai-starter-model-postgresml-embedding` *(experimental)*

---

## 📄 Sample Login Request

```http
POST /auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "password"
}
```

**Response**

```json
{
  "username": "user",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## 🧪 Database Initialization

Liquibase handles schema and initial data via:

```
src/main/resources/db/changelog/
├── db.changelog-master.yaml
├── db.changelog-1.0.yaml
└── data/initial_products.sql
```

---

## 📚 Course Reference

Built while completing:
**"Building FullStack E-Commerce App using SpringBoot & Angular"** by Rahul Sahay
[Udemy Course](https://www.udemy.com/share/10dBBl3@jK_43E3VREocY5EaK0HWAV4y9vM3ERIOjymwIiIQIHLaGsEj1otaRE-Mc49ohLWfOA==/)

---

## 👤 Author

**Suyash Shevade**
Backend Developer | B.Tech CSE (2025)
🔗 [LinkedIn](https://www.linkedin.com/in/suyash-shevade-8b07a9236/)
📁 [GitHub Repo](https://github.com/Shevadesuyash/NoteBook-E-Commerce)

---

## 🪪 License

MIT License. This project is for educational/demo purposes.
