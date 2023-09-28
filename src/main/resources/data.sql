INSERT INTO `USER`(`id`,`username`,`password`,`account_non_expired`,`account_non_locked`,`credential_non_expired`,`enable`) VALUES(1, 'admin',    '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', true, true, true, true);
INSERT INTO `USER`(`id`,`username`,`password`,`account_non_expired`,`account_non_locked`,`credential_non_expired`,`enable`) VALUES(2, 'user',     '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', true, true, true, true);
INSERT INTO `USER`(`id`,`username`,`password`,`account_non_expired`,`account_non_locked`,`credential_non_expired`,`enable`) VALUES(3, 'customer', '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', true, true, true, true);

INSERT INTO `ROLE` (`id`,`name`) VALUES(1, 'ADMIN');
INSERT INTO `ROLE` (`id`,`name`) VALUES(2, 'USER');
INSERT INTO `ROLE` (`id`,`name`) VALUES(3, 'CUSTOMER');

-- admin
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (1,  'ORDER_OPEN');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (2,  'ORDER_ADD');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (3,  'ORDER_REMOVE');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (4,  'ORDER_CLOSE');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (5,  'ORDER_PRINT');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (6,  'ORDER_DELETE');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (7,  'ORDER_REOPEN');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (8,  'ORDER_SEARCH');
-- customer
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (9,  'CUSTOMER_SEARCH');

-- product
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (10, 'PRODUCT_PRINT');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (11, 'PRODUCT_SEARCH');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (12, 'PRODUCT_ADD');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (13, 'PRODUCT_DELETE');
INSERT INTO `AUTHORITY`(`id`,`permission`) VALUES (14, 'PRODUCT_UPDATE');

-- order + admin
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(1, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(2, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(3, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(4, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(5, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(6, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(7, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(8, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(9, 1);

--customer + customer
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(9, 3);

-- product + admin
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(10, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(11, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(12, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(13, 1);
INSERT INTO `ROLE_AUTHORITY` (`authority_id`, `role_id`) VALUES(14, 1);



INSERT INTO `USER_ROLE` (`role_id`, `user_id`) VALUES(1, 1);
INSERT INTO `USER_ROLE` (`role_id`, `user_id`) VALUES(2, 2);
INSERT INTO `USER_ROLE` (`role_id`, `user_id`) VALUES(3, 3);


INSERT INTO `PRODUCT` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,`who_update`, `who_create`, `date_update`, `date_create`, `time_create`, `version`) VALUES (1, 'Latte',      'Latte',      'available', 100,   5.3, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `PRODUCT` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,`who_update`, `who_create`, `date_update`, `date_create`, `time_create`, `version`) VALUES (2, 'Espresso',   'Espresso',   'available', 100,   4.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `PRODUCT` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,`who_update`, `who_create`, `date_update`, `date_create`, `time_create`, `version`) VALUES (3, 'Sandwich',   'Sandwich',   'available', 100, 10.10, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `PRODUCT` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,`who_update`, `who_create`, `date_update`, `date_create`, `time_create`, `version`) VALUES (4, 'Milk',       'Milk',       'available', 100,   1.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `PRODUCT` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,`who_update`, `who_create`, `date_update`, `date_create`, `time_create`, `version`) VALUES (5, 'Cake Slice', 'Cake Slice', 'available', 100,   9.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `PRODUCT` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,`who_update`, `who_create`, `date_update`, `date_create`, `time_create`, `version`) VALUES (6, 'Capuccino',  'Capuccino',  'available', 100,   8.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);
INSERT INTO `PRODUCT` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,`who_update`, `who_create`, `date_update`, `date_create`, `time_create`, `version`) VALUES (7, 'Tea',        'Tea',        'available', 100,   6.1, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

-- If the order have more than 8 products give a 5% discount on the total.
INSERT INTO `DISCOUNT` (`id`, `date_create`, `date_update`, `time_create`, `time_update`, `version`, `who_create`, `who_update`, `discount`, `start_date`, `status`, `to_date`, quantity_items, discount_type) VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(), 1, 0, 0, 5.0, CURRENT_TIMESTAMP(), 'Active',CURRENT_TIMESTAMP(), 8, 'DISCOUNT_ORDER');

-- Print the Receipt
INSERT INTO `ORDER_REQUEST` (`id`, `date_create`, `date_update`, `time_create`, `time_update`, `version`, `who_create`, `who_update`, `order_date_time`, `promotion_discount`, `status`, `total_amount`, `discount`, `total_quantity`) VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, CURRENT_TIMESTAMP(), 0,'CLOSE', 21.1, 0.0, 4);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (1, 0, 1, 6.1, 7);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (2, 0, 1, 4.0, 2);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (3, 0, 1, 4.0, 2);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (4, 0, 1, 6.0, 6);

--If the order have more than 8 products give a 5% discount on the total.
INSERT INTO `ORDER_REQUEST` (`id`, `date_create`, `date_update`, `time_create`, `time_update`, `version`, `who_create`, `who_update`, `order_date_time`, `promotion_discount`, `status`, `total_amount`, `discount`, `total_quantity`) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, CURRENT_TIMESTAMP(), 0,'OPEN', 12.0, 0.0, 4);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (6 , 0, 2,   4.0, 2);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (5 , 0, 2,   5.3, 1);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (7 , 0, 2, 10.10, 3);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (8 , 0, 2,   1.0, 4);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (9 , 0, 2,   9.0, 5);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (10, 0, 2,   8.0, 6);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (11, 0, 2,   8.0, 6);
INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (12, 0, 2,   8.0, 6);


