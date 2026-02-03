-- Insert Brands
INSERT INTO brands (name, description, logo_url, active) VALUES
('Fisher-Price', 'Leading brand in educational toys for children', 'https://example.com/logos/fisher-price.png', TRUE),
('LEGO', 'Famous construction toys and building blocks', 'https://example.com/logos/lego.png', TRUE),
('Pampers', 'Premium baby care products and diapers', 'https://example.com/logos/pampers.png', TRUE),
('Huggies', 'Trusted baby diapers and wipes', 'https://example.com/logos/huggies.png', TRUE),
('Gerber', 'Quality baby food and nutrition products', 'https://example.com/logos/gerber.png', TRUE),
('Chicco', 'Italian brand for baby products', 'https://example.com/logos/chicco.png', TRUE),
('Melissa & Doug', 'Educational toys and crafts', 'https://example.com/logos/melissa-doug.png', TRUE),
('VTech', 'Electronic learning toys', 'https://example.com/logos/vtech.png', TRUE);

-- Insert Categories
INSERT INTO categories (name, description, parent_id, active) VALUES
('Toys', 'All types of toys for children', NULL, TRUE),
('Baby Care', 'Products for baby care and hygiene', NULL, TRUE),
('Clothing', 'Baby and kids clothing', NULL, TRUE),
('Food & Nutrition', 'Baby food and nutrition products', NULL, TRUE),
('Furniture', 'Kids furniture and room decor', NULL, TRUE);

-- Insert Subcategories
INSERT INTO categories (name, description, parent_id, active) VALUES
('Educational Toys', 'Toys that help children learn', 1, TRUE),
('Building Blocks', 'Construction and building toys', 1, TRUE),
('Stuffed Animals', 'Soft toys and plushies', 1, TRUE),
('Diapers', 'Baby diapers and training pants', 2, TRUE),
('Baby Wipes', 'Wet wipes for babies', 2, TRUE),
('Baby Bottles', 'Feeding bottles and accessories', 2, TRUE),
('Baby Formula', 'Infant formula and supplements', 4, TRUE),
('Baby Food', 'Ready-to-eat baby meals', 4, TRUE);

-- Insert Products
-- Educational Toys
INSERT INTO products (name, description, price, category_id, brand_id, stock, active, created_at, updated_at) VALUES
('Laugh & Learn Smart Stages Puppy', 'Interactive learning toy that teaches first words, letters, and numbers', 29.99, 6, 1, 50, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('VTech Touch and Learn Activity Desk', 'Interactive desk with activities for letters, numbers, and music', 89.99, 6, 8, 25, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Melissa & Doug Wooden Building Blocks', 'Classic wooden blocks set with 100 pieces', 34.99, 6, 7, 40, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Building Blocks
INSERT INTO products (name, description, price, category_id, brand_id, stock, active, created_at, updated_at) VALUES
('LEGO Classic Large Creative Brick Box', '790 pieces of colorful LEGO bricks for creative building', 59.99, 7, 2, 60, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('LEGO DUPLO My First Number Train', 'Learning toy for toddlers to learn numbers', 24.99, 7, 2, 45, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('LEGO City Fire Station', 'Complete fire station set with vehicles and figures', 89.99, 7, 2, 30, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Stuffed Animals
INSERT INTO products (name, description, price, category_id, brand_id, stock, active, created_at, updated_at) VALUES
('Fisher-Price Soothe & Glow Seahorse', 'Soft plush seahorse with soothing music and lights', 19.99, 8, 1, 55, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Melissa & Doug Giant Teddy Bear', 'Large stuffed teddy bear, perfect for hugs', 44.99, 8, 7, 20, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Diapers
INSERT INTO products (name, description, price, category_id, brand_id, stock, active, created_at, updated_at) VALUES
('Pampers Swaddlers Newborn Diapers Size 1', 'Soft and absorbent diapers for newborns, 198 count', 49.99, 9, 3, 100, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Pampers Baby Dry Size 3', 'Overnight protection diapers, 162 count', 44.99, 9, 3, 85, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Huggies Little Snugglers Size 2', 'Gentle care diapers for sensitive skin, 180 count', 47.99, 9, 4, 90, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Huggies Pull-Ups Training Pants', 'Training pants for potty training, 84 count', 34.99, 9, 4, 70, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Baby Wipes
INSERT INTO products (name, description, price, category_id, brand_id, stock, active, created_at, updated_at) VALUES
('Pampers Sensitive Water Baby Wipes', 'Gentle and hypoallergenic wipes, 9 packs (576 total)', 24.99, 10, 3, 120, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Huggies Natural Care Baby Wipes', 'Thick and gentle wipes, 8 packs (560 total)', 22.99, 10, 4, 110, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Baby Bottles
INSERT INTO products (name, description, price, category_id, brand_id, stock, active, created_at, updated_at) VALUES
('Chicco Natural Feeling Bottle Set', 'Anti-colic bottles with natural nipple, 4-pack', 29.99, 11, 6, 65, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chicco Well-Being Silicone Bottle', 'Silicone feeding bottle with physiological nipple', 19.99, 11, 6, 75, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Baby Formula & Food
INSERT INTO products (name, description, price, category_id, brand_id, stock, active, created_at, updated_at) VALUES
('Gerber Good Start Gentle Infant Formula', 'Easy-to-digest infant formula, 12.7 oz', 32.99, 12, 5, 80, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gerber 1st Foods Banana Baby Food', 'Single ingredient banana puree, 6 jars', 6.99, 13, 5, 150, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gerber 2nd Foods Variety Pack', 'Mixed vegetables and fruits, 12 jars', 14.99, 13, 5, 100, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gerber Puffs Cereal Snack', 'Melt-in-mouth snacks for babies, 4 pack', 12.99, 13, 5, 95, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert Product Images
-- Educational Toys Images
INSERT INTO product_images (product_id, image_url, is_primary, display_order) VALUES
(1, 'https://example.com/products/smart-puppy-1.jpg', TRUE, 1),
(1, 'https://example.com/products/smart-puppy-2.jpg', FALSE, 2),
(2, 'https://example.com/products/activity-desk-1.jpg', TRUE, 1),
(2, 'https://example.com/products/activity-desk-2.jpg', FALSE, 2),
(3, 'https://example.com/products/wooden-blocks-1.jpg', TRUE, 1);

-- LEGO Images
INSERT INTO product_images (product_id, image_url, is_primary, display_order) VALUES
(4, 'https://example.com/products/lego-classic-1.jpg', TRUE, 1),
(4, 'https://example.com/products/lego-classic-2.jpg', FALSE, 2),
(5, 'https://example.com/products/lego-duplo-train-1.jpg', TRUE, 1),
(6, 'https://example.com/products/lego-fire-station-1.jpg', TRUE, 1),
(6, 'https://example.com/products/lego-fire-station-2.jpg', FALSE, 2);

-- Stuffed Animals Images
INSERT INTO product_images (product_id, image_url, is_primary, display_order) VALUES
(7, 'https://example.com/products/seahorse-1.jpg', TRUE, 1),
(8, 'https://example.com/products/teddy-bear-1.jpg', TRUE, 1);

-- Diapers Images
INSERT INTO product_images (product_id, image_url, is_primary, display_order) VALUES
(9, 'https://example.com/products/pampers-newborn-1.jpg', TRUE, 1),
(10, 'https://example.com/products/pampers-size3-1.jpg', TRUE, 1),
(11, 'https://example.com/products/huggies-size2-1.jpg', TRUE, 1),
(12, 'https://example.com/products/huggies-pullups-1.jpg', TRUE, 1);

-- Baby Wipes Images
INSERT INTO product_images (product_id, image_url, is_primary, display_order) VALUES
(13, 'https://example.com/products/pampers-wipes-1.jpg', TRUE, 1),
(14, 'https://example.com/products/huggies-wipes-1.jpg', TRUE, 1);

-- Baby Bottles Images
INSERT INTO product_images (product_id, image_url, is_primary, display_order) VALUES
(15, 'https://example.com/products/chicco-bottle-set-1.jpg', TRUE, 1),
(16, 'https://example.com/products/chicco-silicone-bottle-1.jpg', TRUE, 1);

-- Baby Food Images
INSERT INTO product_images (product_id, image_url, is_primary, display_order) VALUES
(17, 'https://example.com/products/gerber-formula-1.jpg', TRUE, 1),
(18, 'https://example.com/products/gerber-banana-1.jpg', TRUE, 1),
(19, 'https://example.com/products/gerber-variety-1.jpg', TRUE, 1),
(20, 'https://example.com/products/gerber-puffs-1.jpg', TRUE, 1);
