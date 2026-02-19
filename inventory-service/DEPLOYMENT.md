# Hướng dẫn Build & Deploy Inventory Service

## Bước 1: Build service

```bash
cd inventory-service
mvn clean package -DskipTests
```

## Bước 2: Chạy với Docker Compose

```bash
cd ../docker
docker-compose up -d postgres-inventory consul kafka zookeeper
```

Đợi các service khởi động xong (khoảng 30 giây)

```bash
docker-compose up -d inventory-service
```

## Bước 3: Kiểm tra service

### Kiểm tra logs

```bash
docker logs -f inventory-service
```

### Kiểm tra health

```bash
curl http://localhost:8084/api/inventory/health
```

### Truy cập Swagger UI

Mở trình duyệt: http://localhost:8084/swagger-ui.html

### Kiểm tra Consul

Mở trình duyệt: http://localhost:8500

## Bước 4: Test APIs

### Lấy danh sách kho

```bash
curl http://localhost:8084/api/warehouses
```

### Lấy danh sách cửa hàng

```bash
curl http://localhost:8084/api/stores
```

### Thêm sản phẩm vào kho WH001

```bash
curl -X POST http://localhost:8084/api/warehouses/1/products \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "quantity": 100,
    "minStockLevel": 10,
    "maxStockLevel": 500,
    "locationCode": "A1-01"
  }'
```

### Kiểm tra sản phẩm sắp hết hàng

```bash
curl http://localhost:8084/api/inventory/low-stock
```

## Troubleshooting

### Service không khởi động được

```bash
# Xem logs
docker logs inventory-service

# Restart service
docker-compose restart inventory-service
```

### Database connection error

```bash
# Kiểm tra postgres-inventory
docker ps | grep postgres-inventory
docker logs postgres-inventory

# Restart database
docker-compose restart postgres-inventory
```

### Port đã được sử dụng

Kiểm tra file docker-compose.yml, inventory-service dùng port 8084

## Dừng services

```bash
# Dừng tất cả
docker-compose down

# Dừng và xóa volumes (reset database)
docker-compose down -v
```

## Development

### Chạy local không dùng Docker

```bash
# Đảm bảo PostgreSQL đang chạy ở port 5435
# Đảm bảo Consul đang chạy ở port 8500
# Đảm bảo Kafka đang chạy ở port 9092

cd inventory-service
mvn spring-boot:run
```

## Useful Commands

```bash
# Build lại sau khi sửa code
mvn clean package -DskipTests
docker-compose up -d --build inventory-service

# Xem tất cả services
docker-compose ps

# Xem logs của nhiều services
docker-compose logs -f inventory-service product-service

# Vào container
docker exec -it inventory-service bash

# Kết nối database
docker exec -it postgres-inventory psql -U postgres -d inventorydb
```
