-- Add soft delete columns to products table
ALTER TABLE products ADD COLUMN deleted BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE products ADD COLUMN deleted_at TIMESTAMP;

-- Add soft delete columns to brands table
ALTER TABLE brands ADD COLUMN deleted BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE brands ADD COLUMN deleted_at TIMESTAMP;

-- Add soft delete columns to categories table
ALTER TABLE categories ADD COLUMN deleted BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE categories ADD COLUMN deleted_at TIMESTAMP;

-- Create index for better query performance on deleted column
CREATE INDEX idx_products_deleted ON products(deleted);
CREATE INDEX idx_brands_deleted ON brands(deleted);
CREATE INDEX idx_categories_deleted ON categories(deleted);
