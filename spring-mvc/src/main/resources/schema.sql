DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders_items;
DROP TABLE IF EXISTS orders;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `photo` VARCHAR(255) DEFAULT NULL,
  `address` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `products` (
  `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
  `item_name` VARCHAR(50) NOT NULL,
  `item_price` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `orders` (
  `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
  `user_id` INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `orders_items` (
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (order_id, item_id)
);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk0` FOREIGN KEY (user_id) REFERENCES `users` (id);
ALTER TABLE `orders_items` ADD CONSTRAINT `orders_items_fk0` FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE `orders_items` ADD CONSTRAINT `orders_items_fk1` FOREIGN KEY (item_id) REFERENCES products (id);