SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `serving_styles`;
DROP TABLE IF EXISTS `breakfast_sets`;
DROP TABLE IF EXISTS `item_qtys`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `breakfast_sets_orders`;
DROP TABLE IF EXISTS `bfset_servingstyles_map`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `users` (
    `userId` VARCHAR(100) NOT NULL,
    `email` VARCHAR(256) NOT NULL,
    `address` VARCHAR(500) NOT NULL,
    `phone_no` VARCHAR(15) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `password` VARCHAR NOT NULL,
    `is_admin` BOOLEAN NOT NULL,
    PRIMARY KEY (`userId`)
);

CREATE TABLE `items` (
    `id` INTEGER NOT NULL auto_increment,
    `name` VARCHAR(100) NOT NULL,
    `unitPrice` DOUBLE NOT NULL,
    `servingUnit` TEXT NOT NULL,
    `description` VARCHAR(1000) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `serving_styles` (
    `id` INTEGER NOT NULL auto_increment,
    `name` VARCHAR(100) NOT NULL,
    `description` VARCHAR(1000) NOT NULL,
    `price` DOUBLE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `breakfast_sets` (
    `id` INTEGER NOT NULL auto_increment,
    `name` VARCHAR(100) NOT NULL,
    `description` VARCHAR(1000) NOT NULL,
    `price` DOUBLE NOT NULL,
    `isTemplate` BOOLEAN NOT NULL,
    `serving_style` INTEGER,
    PRIMARY KEY (`id`)
);

CREATE TABLE `item_qtys` (
    `bf_set_id` INTEGER NOT NULL,
    `item_id` INTEGER NOT NULL,
    `quantity` INTEGER NOT NULL
);

CREATE TABLE `orders` (
    `id` INTEGER NOT NULL auto_increment,
    `customer` VARCHAR(100) ,
    `total_price` INTEGER NOT NULL,
    `payment_info` VARCHAR(100) NOT NULL,
    `order_status` VARCHAR(20) NOT NULL,
    `delivery_address` VARCHAR(1000) NOT NULL,
    `delivery_time` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `breakfast_sets_orders` (
    `order_id` INTEGER NOT NULL,
    `breakfast_set_id` INTEGER NOT NULL
);

CREATE TABLE `bfset_servingstyles_map` (
    `serving_style_id` INTEGER NOT NULL,
    `bf_set_id` INTEGER NOT NULL
);

ALTER TABLE `breakfast_sets` ADD FOREIGN KEY (`serving_style`) REFERENCES `serving_styles`(`id`);
ALTER TABLE `item_qtys` ADD FOREIGN KEY (`item_id`) REFERENCES `items`(`id`);
ALTER TABLE `item_qtys` ADD FOREIGN KEY (`bf_set_id`) REFERENCES `breakfast_sets`(`id`);
ALTER TABLE `orders` ADD FOREIGN KEY (`customer`) REFERENCES `users`(`userId`);
ALTER TABLE `breakfast_sets_orders` ADD FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`);
ALTER TABLE `breakfast_sets_orders` ADD FOREIGN KEY (`breakfast_set_id`) REFERENCES `breakfast_sets`(`id`);
ALTER TABLE `bfset_servingstyles_map` ADD FOREIGN KEY (`serving_style_id`) REFERENCES `serving_styles`(`id`);
ALTER TABLE `bfset_servingstyles_map` ADD FOREIGN KEY (`bf_set_id`) REFERENCES `breakfast_sets`(`id`);