# Inventory Service

Microservice quản lý kho hàng và tồn kho cho hệ thống bán đồ chơi trẻ em KidFavor.

## 🎯 Chức năng chính

- Quản lý thông tin kho (Warehouse)
- Quản lý thông tin cửa hàng (Store)
- Quản lý tồn kho tại kho (Warehouse Products)
- Quản lý tồn kho tại cửa hàng (Store Inventory)
- Cảnh báo tồn kho thấp
- Tích hợp Kafka cho event-driven architecture
- Tích hợp với Product Service

## 🛠 Technology Stack

- **Java**: 21
- **Framework**: Spring Boot 3.2.5
- **Database**: PostgreSQL 16
- **ORM**: Spring Data JPA + Hibernate
- **Migration**: Flyway
- **Service Discovery**: Consul
- **Message Broker**: Apache Kafka
- **API Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven

## 📋 Prerequisites

- Java 21+
- Maven 3.8+
- PostgreSQL 16
- Kafka
- Consul

## 🚀 Getting Started

### Local Development

1. **Clone repository**

```bash
git clone <repository-url>
cd inventory-service
```

2. **Build project**

```bash
mvn clean install
```

3. **Run application**

```bash
mvn spring-boot:run
```

### Docker Deployment

1. **Build Docker image**

```bash
docker build -t inventory-service .
```

2. **Run with Docker Compose**

```bash
cd ../docker
docker-compose up -d inventory-service
```

## 📊 Database Schema

### Tables

1. **warehouses** - Thông tin kho hàng
2. **stores** - Thông tin cửa hàng
3. **warehouse_products** - Tồn kho tại kho
4. **store_inventory** - Tồn kho tại cửa hàng

## 🔌 API Endpoints

### Warehouse Management

- `GET /api/warehouses` - Lấy danh sách tất cả kho
- `GET /api/warehouses/active` - Lấy danh sách kho đang hoạt động
- `GET /api/warehouses/{id}` - Lấy thông tin kho theo ID
- `GET /api/warehouses/code/{code}` - Lấy thông tin kho theo mã
- `POST /api/warehouses` - Tạo kho mới
- `PUT /api/warehouses/{id}` - Cập nhật thông tin kho
- `DELETE /api/warehouses/{id}` - Xóa kho

### Store Management

- `GET /api/stores` - Lấy danh sách tất cả cửa hàng
- `GET /api/stores/active` - Lấy danh sách cửa hàng đang hoạt động
- `GET /api/stores/{id}` - Lấy thông tin cửa hàng theo ID
- `GET /api/stores/code/{code}` - Lấy thông tin cửa hàng theo mã
- `POST /api/stores` - Tạo cửa hàng mới
- `PUT /api/stores/{id}` - Cập nhật thông tin cửa hàng
- `DELETE /api/stores/{id}` - Xóa cửa hàng

### Warehouse Inventory

- `GET /api/warehouses/{warehouseId}/products` - Lấy danh sách sản phẩm trong kho
- `GET /api/warehouses/{warehouseId}/products/{productId}` - Lấy thông tin sản phẩm cụ thể
- `GET /api/warehouses/{warehouseId}/products/{productId}/stock` - Kiểm tra số lượng tồn kho
- `GET /api/warehouses/{warehouseId}/low-stock` - Lấy sản phẩm sắp hết hàng
- `POST /api/warehouses/{warehouseId}/products` - Thêm/cập nhật sản phẩm
- `PUT /api/warehouses/{warehouseId}/products/{productId}` - Cập nhật số lượng
- `DELETE /api/warehouses/{warehouseId}/products/{productId}` - Xóa sản phẩm

### Store Inventory

- `GET /api/stores/{storeId}/inventory` - Lấy danh sách tồn kho cửa hàng
- `GET /api/stores/{storeId}/inventory/{productId}` - Lấy tồn kho sản phẩm cụ thể
- `GET /api/stores/{storeId}/inventory/{productId}/stock` - Kiểm tra số lượng tồn kho
- `GET /api/stores/{storeId}/low-stock` - Lấy sản phẩm sắp hết hàng
- `POST /api/stores/{storeId}/inventory` - Thêm/cập nhật tồn kho
- `PUT /api/stores/{storeId}/inventory/{productId}` - Cập nhật số lượng
- `DELETE /api/stores/{storeId}/inventory/{productId}` - Xóa khỏi tồn kho

### General Inventory

- `GET /api/inventory/low-stock` - Lấy tất cả sản phẩm sắp hết hàng
- `GET /api/inventory/health` - Health check

## 📡 Kafka Events

### Published Events

1. **StockUpdatedEvent** - Khi tồn kho được cập nhật
   - Topic: `stock-updated`
   - Keys: productId, locationType, locationId, quantityChange

2. **StockLowAlertEvent** - Khi tồn kho thấp hơn mức tối thiểu
   - Topic: `stock-low-alert`
   - Keys: productId, locationType, locationId, currentQuantity, minStockLevel

### Consumed Events

1. **OrderCreatedEvent** - Từ order-service
   - Topic: `order-created`
   - Action: Giảm tồn kho khi có đơn hàng mới

## 🔧 Configuration

### Application Properties

```yaml
server:
  port: 8084

spring:
  application:
    name: inventory-service
  datasource:
    url: jdbc:postgresql://localhost:5435/inventorydb
    username: postgres
    password: postgres
```

### Environment Variables

- `SPRING_DATASOURCE_URL` - Database connection URL
- `SPRING_DATASOURCE_USERNAME` - Database username
- `SPRING_DATASOURCE_PASSWORD` - Database password
- `SPRING_CLOUD_CONSUL_HOST` - Consul host
- `SPRING_CLOUD_CONSUL_PORT` - Consul port
- `SPRING_KAFKA_BOOTSTRAP_SERVERS` - Kafka servers
- `PRODUCT_SERVICE_URL` - Product service URL

## 📚 API Documentation

Sau khi khởi động service, truy cập Swagger UI tại:

```
http://localhost:8084/swagger-ui.html
```

## 🏗 Architecture

```
inventory-service/
├── src/main/java/com/kidfavor/inventoryservice/
│   ├── config/          # Kafka configuration
│   ├── controller/      # REST controllers
│   ├── dto/             # Data Transfer Objects
│   ├── entity/          # JPA entities
│   ├── event/           # Kafka events
│   ├── exception/       # Exception handling
│   ├── kafka/           # Kafka producers/consumers
│   ├── mapper/          # Entity-DTO mappers
│   ├── repository/      # Data repositories
│   └── service/         # Business logic
└── src/main/resources/
    ├── application.yml  # Configuration
    └── db/migration/    # Flyway migrations
```

## 📝 Sample Data

Service đã được cấu hình với dữ liệu mẫu:

### Warehouses

- WH001 - Kho Tổng Hà Nội
- WH002 - Kho Tổng TP.HCM
- WH003 - Kho Miền Trung

### Stores

- ST001 - KidFavor Hà Nội - Royal City
- ST002 - KidFavor TP.HCM - Crescent Mall
- ST003 - KidFavor Hà Nội - Times City
- ST004 - KidFavor Đà Nẵng - Vincom
- ST005 - KidFavor TP.HCM - Aeon Mall

## 🧪 Testing

```bash
mvn test
```

## 📦 Build

```bash
mvn clean package
```

## 👥 Team

MSS301 - Microservice Architecture Course

## 📄 License

Educational Project - FPT University
