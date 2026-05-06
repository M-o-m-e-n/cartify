# 🧩 Spring Boot Microservices — Full Stack

A full-featured **microservices architecture** built with Spring Boot, demonstrating real-world patterns including inter-service communication, async messaging, distributed tracing, and multi-database support.

---

## 📐 Architecture Overview

The system is composed of independently deployable services, each responsible for a single business domain, all wired together through an API Gateway and a centralized Config & Discovery server.

```
Client
  │
  ▼
API Gateway
  ├── Customer Service   (PostgreSQL)
  ├── Product Service    (PostgreSQL)
  ├── Order Service      (PostgreSQL)
  ├── Payment Service    (PostgreSQL)
  └── Notification Service (MongoDB)
          │
          └── Apache Kafka (async events)
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot |
| Service Discovery | Eureka (Spring Cloud Netflix) |
| Config Management | Spring Cloud Config Server |
| API Gateway | Spring Cloud Gateway |
| Async Messaging | Apache Kafka + Zookeeper |
| Relational DB | PostgreSQL |
| NoSQL DB | MongoDB |
| Distributed Tracing | Zipkin |
| Email (Dev) | MailDev |
| Containerization | Docker & Docker Compose |

---

## 📦 Services

| Service | Description | Port |
|---|---|---|
| `config-server` | Centralized configuration for all services | `8888` |
| `discovery-server` | Eureka service registry | `8761` |
| `api-gateway` | Single entry point, routes to downstream services | `8222` |
| `customer-service` | Manages customer data | `8090` |
| `product-service` | Manages product catalog | `8050` |
| `order-service` | Handles order placement and lifecycle | `8070` |
| `payment-service` | Processes payments per order | `8060` |
| `notification-service` | Sends email notifications via Kafka events | `8040` |

---

## 🚀 Getting Started

### Prerequisites

- Java 21
- Maven
- Docker & Docker Compose

### 1. Start infrastructure

Spin up all required infrastructure services (PostgreSQL, MongoDB, Kafka, Zipkin, MailDev) with a single command:

```bash
docker-compose up -d
```

This starts the following containers:

| Container | Purpose | Port |
|---|---|---|
| `ms_pg_sql` | PostgreSQL database | `5432` |
| `ms_pgadmin` | pgAdmin UI | `5050` |
| `mongo_db` | MongoDB database | `27017` |
| `mongo_express` | MongoDB UI | `8081` |
| `ms_kafka` | Kafka broker | `9092` |
| `zookeeper` | Kafka coordination | `2181` |
| `zipkin` | Distributed tracing UI | `9411` |
| `ms-mail-dev` | Local email server (SMTP + UI) | `1025` / `1080` |

### 2. Start services (in order)

Start services in the following order to respect dependencies:

```bash
# 1. Config Server
cd services/config-server && ./mvnw spring-boot:run

# 2. Discovery Server
cd services/discovery-server && ./mvnw spring-boot:run

# 3. Business Services (any order)
cd services/customer-service && ./mvnw spring-boot:run
cd services/product-service  && ./mvnw spring-boot:run
cd services/order-service    && ./mvnw spring-boot:run
cd services/payment-service  && ./mvnw spring-boot:run
cd services/notification-service && ./mvnw spring-boot:run

# 4. API Gateway (last)
cd services/api-gateway && ./mvnw spring-boot:run
```

---

## 🔍 Useful UIs

| Tool | URL |
|---|---|
| Eureka Dashboard | http://localhost:8761 |
| Zipkin Tracing | http://localhost:9411 |
| pgAdmin | http://localhost:5050 |
| Mongo Express | http://localhost:8081 |
| MailDev | http://localhost:1080 |

---

## 📨 Async Communication (Kafka)

The **Order Service** publishes events to Kafka topics upon order confirmation. The **Notification Service** consumes these events and sends email notifications to customers via MailDev (in development) or a real SMTP server (in production).

---

## 🔭 Distributed Tracing (Zipkin)

All services are instrumented with **Zipkin** for distributed tracing. You can trace a full request lifecycle — from the API Gateway through each downstream service — at:

```
http://localhost:9411
```

---

## 📁 Project Structure

```
microservices-full-code/
├── services/
│   ├── config-server/
│   ├── discovery-server/
│   ├── api-gateway/
│   ├── customer-service/
│   ├── product-service/
│   ├── order-service/
│   ├── payment-service/
│   └── notification-service/
├── diagrams/
├── resources/
├── docker-compose.yml
└── README.md
```