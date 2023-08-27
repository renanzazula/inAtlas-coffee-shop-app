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
--
-- INSERT INTO `ORDER_REQUEST` (`id`, `date_create`, `date_update`, `time_create`, `time_update`, `version`, `who_create`, `who_update`, `order_date_time`, `promotion_discount`, `status`, `total_amount`, `discount`, `total_quantity`) VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0, CURRENT_TIMESTAMP(), 0,'CLOSE', 12.0, 0.0, 4);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (6 , 0, 2,   4.0, 2);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (5 , 0, 2,   5.3, 1);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (7 , 0, 2, 10.10, 3);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (8 , 0, 2,   1.0, 4);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (9 , 0, 2,   9.0, 5);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (10, 0, 2,   8.0, 6);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (11, 0, 2,   8.0, 6);
-- INSERT INTO `ORDER_REQUEST_REQUEST_ITEM` (`order_request_items_id`, `discount`, `order_request_id`, `price_unit`, `product_id`) VALUES (12, 0, 2,   8.0, 6);


