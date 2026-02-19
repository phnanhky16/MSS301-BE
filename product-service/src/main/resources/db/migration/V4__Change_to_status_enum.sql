-- Migration to change from active/deleted to status enum

-- Add status column to products
ALTER TABLE products ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';
ALTER TABLE products ADD COLUMN status_changed_at TIMESTAMP;

-- Migrate existing data for products
UPDATE products SET status = 'DELETED' WHERE deleted = TRUE;
UPDATE products SET status = 'ACTIVE' WHERE deleted = FALSE AND active = TRUE;
UPDATE products SET status = 'INACTIVE' WHERE deleted = FALSE AND active = FALSE;
UPDATE products SET status_changed_at = deleted_at WHERE deleted_at IS NOT NULL;

-- Drop old columns from products
ALTER TABLE products DROP COLUMN active;
ALTER TABLE products DROP COLUMN deleted;
ALTER TABLE products DROP COLUMN deleted_at;

-- Add status column to brands
ALTER TABLE brands ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';
ALTER TABLE brands ADD COLUMN status_changed_at TIMESTAMP;
ALTER TABLE brands ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE brands ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- Migrate existing data for brands
UPDATE brands SET status = 'DELETED' WHERE deleted = TRUE;
UPDATE brands SET status = 'ACTIVE' WHERE deleted = FALSE AND active = TRUE;
UPDATE brands SET status = 'INACTIVE' WHERE deleted = FALSE AND active = FALSE;
UPDATE brands SET status_changed_at = deleted_at WHERE deleted_at IS NOT NULL;

-- Drop old columns from brands
ALTER TABLE brands DROP COLUMN active;
ALTER TABLE brands DROP COLUMN deleted;
ALTER TABLE brands DROP COLUMN deleted_at;

-- Add status column to categories
ALTER TABLE categories ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';
ALTER TABLE categories ADD COLUMN status_changed_at TIMESTAMP;
ALTER TABLE categories ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE categories ADD COLUMN updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- Migrate existing data for categories
UPDATE categories SET status = 'DELETED' WHERE deleted = TRUE;
UPDATE categories SET status = 'ACTIVE' WHERE deleted = FALSE AND active = TRUE;
UPDATE categories SET status = 'INACTIVE' WHERE deleted = FALSE AND active = FALSE;
UPDATE categories SET status_changed_at = deleted_at WHERE deleted_at IS NOT NULL;

-- Drop old columns from categories
ALTER TABLE categories DROP COLUMN active;
ALTER TABLE categories DROP COLUMN deleted;
ALTER TABLE categories DROP COLUMN deleted_at;

-- Drop old indexes
DROP INDEX IF EXISTS idx_products_deleted;
DROP INDEX IF EXISTS idx_brands_deleted;
DROP INDEX IF EXISTS idx_categories_deleted;

-- Create new indexes for status
CREATE INDEX idx_products_status ON products(status);
CREATE INDEX idx_brands_status ON brands(status);
CREATE INDEX idx_categories_status ON categories(status);
