# 🛒 Cartify

A scalable **E-commerce application** built using **Spring Boot Microservices Architecture**, following best practices for distributed systems, security, and service communication.

---

## 🚀 Overview

This project demonstrates how to design and implement a **modern microservices-based system** with:

- Independent services
- Centralized API Gateway
- Secure authentication using Keycloak
- Service discovery & communication

It is inspired by real-world production architectures used in large-scale systems.

---

## 🏗️ Architecture

The system is composed of multiple microservices:

- **Product Service** → Manages product catalog
- **Order Service** → Handles customer orders
- **Inventory Service** → Tracks stock availability
- **API Gateway** → Entry point for all client requests
- **Discovery Server** → Service registration & discovery
- **Auth Server (Keycloak)** → Authentication & authorization

### 🔁 Flow

1. Client sends request to API Gateway  
2. Gateway routes request to appropriate microservice  
3. Services communicate with each other via REST  
4. Authentication handled via Keycloak (JWT)

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot, Spring Cloud
- **Security:** Keycloak, OAuth2, OpenID Connect
- **Service Discovery:** Eureka
- **API Gateway:** Spring Cloud Gateway
- **Database:** MongoDB / PostgreSQL
- **Build Tool:** Maven
- **Containerization:** Docker

---

## 🔐 Security

- Implemented **OAuth2 & OpenID Connect**
- Integrated **Keycloak** for authentication
- Role-Based Access Control (RBAC)
- JWT-based secure communication

---

## ⚙️ Features

- ✅ Microservices architecture
- ✅ API Gateway routing
- ✅ Service discovery
- ✅ Secure authentication & authorization
- ✅ Inter-service communication
- ✅ Scalable & modular design

---

## 📦 Installation & Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/M-o-m-e-n/cartify.git
cd cartify
```

### 2️⃣ Run infrastructure (Docker)
```bash
docker-compose up -d
```

### 3️⃣ Start services
Run each microservice using:
```bash
./mvnw spring-boot:run
```

---

## 🌐 API Gateway

All requests go through:
```
http://localhost:8080
```

Example:
```
GET /api/product
POST /api/order
```

---

## 🔎 Service Discovery

Access Eureka dashboard:
```
http://localhost:8761
```

---

## 🔑 Keycloak Setup

- Access Keycloak Admin Panel:
```
http://localhost:8080
```

- Configure:
  - Realm
  - Client
  - Roles
  - Users

---

## 🧪 Testing

You can test APIs using:

- Postman
- cURL
- Browser (for GET endpoints)

Make sure to include JWT token for secured endpoints.

---

## 📈 Future Improvements

- Add messaging queue (Kafka / RabbitMQ)
- Implement Circuit Breaker (Resilience4j)
- Add centralized logging (ELK Stack)
- Add distributed tracing (Zipkin)

