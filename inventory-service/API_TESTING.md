# API Testing Collection - Inventory Service

Base URL: `http://localhost:8084`

## 1. Health Check

```bash
GET /api/inventory/health
```

## 2. Warehouse APIs

### Get all warehouses

```bash
GET /api/warehouses
```

### Get active warehouses

```bash
GET /api/warehouses/active
```

### Get warehouse by ID

```bash
GET /api/warehouses/1
```

### Get warehouse by code

```bash
GET /api/warehouses/code/WH001
```

### Create warehouse

```bash
POST /api/warehouses
Content-Type: application/json

{
  "warehouseCode": "WH004",
  "warehouseName": "Kho Miền Nam",
  "address": "123 Nguyễn Huệ",
  "city": "Cần Thơ",
  "district": "Ninh Kiều",
  "phone": "0292123456",
  "managerName": "Trần Văn X",
  "capacity": 12000.00,
  "warehouseType": "REGIONAL",
  "isActive": true
}
```

### Update warehouse

```bash
PUT /api/warehouses/1
Content-Type: application/json

{
  "warehouseCode": "WH001",
  "warehouseName": "Kho Tổng Hà Nội - Cập nhật",
  "address": "123 Đường Láng, Đống Đa",
  "city": "Hà Nội",
  "district": "Đống Đa",
  "phone": "0241234567",
  "managerName": "Nguyễn Văn A",
  "capacity": 12000.00,
  "warehouseType": "CENTRAL",
  "isActive": true
}
```

### Delete warehouse

```bash
DELETE /api/warehouses/4
```

## 3. Store APIs

### Get all stores

```bash
GET /api/stores
```

### Get active stores

```bash
GET /api/stores/active
```

### Get store by ID

```bash
GET /api/stores/1
```

### Get store by code

```bash
GET /api/stores/code/ST001
```

### Create store

```bash
POST /api/stores
Content-Type: application/json

{
  "storeCode": "ST006",
  "storeName": "KidFavor Cần Thơ - Vincom",
  "address": "Vincom Cần Thơ",
  "city": "Cần Thơ",
  "district": "Ninh Kiều",
  "phone": "0292456789",
  "managerName": "Lê Thị Y",
  "isActive": true
}
```

### Update store

```bash
PUT /api/stores/1
Content-Type: application/json

{
  "storeCode": "ST001",
  "storeName": "KidFavor Hà Nội - Royal City Premium",
  "address": "Royal City, Thanh Xuân",
  "city": "Hà Nội",
  "district": "Thanh Xuân",
  "phone": "0243456789",
  "managerName": "Phạm Thị D",
  "isActive": true
}
```

### Delete store

```bash
DELETE /api/stores/6
```

## 4. Warehouse Inventory APIs

### Get all products in warehouse

```bash
GET /api/warehouses/1/products
```

### Get specific product in warehouse

```bash
GET /api/warehouses/1/products/1
```

### Get available stock

```bash
GET /api/warehouses/1/products/1/stock
```

### Get low stock products in warehouse

```bash
GET /api/warehouses/1/low-stock
```

### Add product to warehouse

```bash
POST /api/warehouses/1/products
Content-Type: application/json

{
  "productId": 1,
  "quantity": 100,
  "minStockLevel": 10,
  "maxStockLevel": 500,
  "locationCode": "A1-01"
}
```

### Update stock (increase)

```bash
PUT /api/warehouses/1/products/1
Content-Type: application/json

{
  "quantityChange": 50,
  "reason": "Nhập hàng từ nhà cung cấp"
}
```

### Update stock (decrease)

```bash
PUT /api/warehouses/1/products/1
Content-Type: application/json

{
  "quantityChange": -20,
  "reason": "Chuyển hàng về cửa hàng"
}
```

### Remove product from warehouse

```bash
DELETE /api/warehouses/1/products/1
```

## 5. Store Inventory APIs

### Get all inventory in store

```bash
GET /api/stores/1/inventory
```

### Get specific product inventory

```bash
GET /api/stores/1/inventory/1
```

### Get available stock

```bash
GET /api/stores/1/inventory/1/stock
```

### Get low stock products in store

```bash
GET /api/stores/1/low-stock
```

### Add inventory to store

```bash
POST /api/stores/1/inventory
Content-Type: application/json

{
  "productId": 1,
  "quantity": 30,
  "minStockLevel": 5,
  "shelfLocation": "A-01"
}
```

### Update stock (increase)

```bash
PUT /api/stores/1/inventory/1
Content-Type: application/json

{
  "quantityChange": 10,
  "reason": "Nhận hàng từ kho"
}
```

### Update stock (decrease - bán hàng)

```bash
PUT /api/stores/1/inventory/1
Content-Type: application/json

{
  "quantityChange": -5,
  "reason": "Bán hàng cho khách"
}
```

### Remove inventory from store

```bash
DELETE /api/stores/1/inventory/1
```

## 6. General Inventory APIs

### Get all low stock products

```bash
GET /api/inventory/low-stock
```

## Complete Workflow Example

### Bước 1: Tạo kho mới

```bash
curl -X POST http://localhost:8084/api/warehouses \
  -H "Content-Type: application/json" \
  -d '{
    "warehouseCode": "WH004",
    "warehouseName": "Kho Test",
    "city": "Hà Nội",
    "isActive": true
  }'
```

### Bước 2: Thêm sản phẩm vào kho (giả sử productId=1 tồn tại trong product-service)

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

### Bước 3: Chuyển hàng sang cửa hàng

```bash
# Giảm tồn kho ở kho
curl -X PUT http://localhost:8084/api/warehouses/1/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "quantityChange": -30,
    "reason": "Chuyển sang cửa hàng ST001"
  }'

# Tăng tồn kho ở cửa hàng
curl -X POST http://localhost:8084/api/stores/1/inventory \
  -H "Content-Type: application/json" \
  -d '{
    "productId": 1,
    "quantity": 30,
    "minStockLevel": 5,
    "shelfLocation": "A-01"
  }'
```

### Bước 4: Bán hàng

```bash
curl -X PUT http://localhost:8084/api/stores/1/inventory/1 \
  -H "Content-Type: application/json" \
  -d '{
    "quantityChange": -5,
    "reason": "Bán hàng"
  }'
```

### Bước 5: Kiểm tra sản phẩm sắp hết

```bash
curl http://localhost:8084/api/inventory/low-stock
```

## Notes

- Tất cả các endpoint đều trả về JSON
- productId phải tồn tại trong product-service
- Quantity không thể âm
- MinStockLevel dùng để cảnh báo khi tồn kho thấp
