# MSS301 Microservices Architecture

## Architecture Overview

This project implements a microservices architecture for a Kids Store Management System.

### Services:

1. **api-gateway** (Port 8080) - API Gateway with Spring Cloud Gateway
2. **user-service** (Port 8081) - User and Shipment management
3. **order-service** (Port 8082) - Order, OrderItem, Payment, Coupon management
4. **product-service** (Port 8083) - Product, Category, Brand, ProductImage, ProductPackage, Package management
5. **inventory-service** (Port 8084) - Warehouse and Store management
6. **cart-service** (Port 8085) - Shopping Cart and CartItem management
7. **review-service** (Port 8086) - Product Review management

### Technology Stack:
- Spring Boot 4.0.2
- Spring Cloud 2025.1.0
- Java 21
- Consul for Service Discovery
- PostgreSQL/MySQL for Database
- Maven for Build Management

### Prerequisites:
- Java 21
- Maven 3.8+
- Consul running on localhost:8500

### Running the Services:

1. Start Consul:
```bash
consul agent -dev
```

2. Build all services:
```bash
./build-all.sh
```

3. Run each service:
```bash
cd api-gateway && mvn spring-boot:run
cd user-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
cd cart-service && mvn spring-boot:run
cd review-service && mvn spring-boot:run
```

### API Gateway Endpoints:

- `/api/users/**` -> User Service
- `/api/orders/**` -> Order Service
- `/api/products/**` -> Product Service
- `/api/inventory/**` -> Inventory Service
- `/api/cart/**` -> Cart Service
- `/api/reviews/**` -> Review Service

### Service Discovery:

All services register with Consul at `localhost:8500`
if any service's pom.xml, simply add (no version needed — managed by the root POM):
<dependency>
    <groupId>com.kidfavor</groupId>
    <artifactId>common-library</artifactId>
</dependency>