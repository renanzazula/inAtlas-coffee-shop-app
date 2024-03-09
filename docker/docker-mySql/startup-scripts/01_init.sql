-- MySQL Script generated by MySQL Workbench
-- Tue Feb 27 13:26:23 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema standardcoffeeshopapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema standardcoffeeshopapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `standardcoffeeshopapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `standardcoffeeshopapp`;

-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`authority`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `permission` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 15
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`product`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `date_create` DATE         NULL DEFAULT NULL,
    `date_update` DATE         NULL DEFAULT NULL,
    `time_create` TIME         NULL DEFAULT NULL,
    `time_update` TIME         NULL DEFAULT NULL,
    `version`     BIGINT       NULL DEFAULT NULL,
    `who_create`  BIGINT       NULL DEFAULT NULL,
    `who_update`  BIGINT       NULL DEFAULT NULL,
    `description` VARCHAR(100) NULL DEFAULT NULL,
    `name`        VARCHAR(45)  NOT NULL,
    `price_unit`  DOUBLE       NULL DEFAULT '0',
    `quantity`    BIGINT       NULL DEFAULT '0',
    `status`      VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`discount`
(
    `id`             BIGINT       NOT NULL AUTO_INCREMENT,
    `date_create`    DATE         NULL DEFAULT NULL,
    `date_update`    DATE         NULL DEFAULT NULL,
    `time_create`    TIME         NULL DEFAULT NULL,
    `time_update`    TIME         NULL DEFAULT NULL,
    `version`        BIGINT       NULL DEFAULT NULL,
    `who_create`     BIGINT       NULL DEFAULT NULL,
    `who_update`     BIGINT       NULL DEFAULT NULL,
    `amount_from`    DOUBLE       NULL DEFAULT NULL,
    `amount_to`      DOUBLE       NULL DEFAULT NULL,
    `discount`       DOUBLE       NULL DEFAULT NULL,
    `discount_type`  VARCHAR(255) NULL DEFAULT NULL,
    `start_date`     DATE         NULL DEFAULT NULL,
    `quantity_items` DOUBLE       NULL DEFAULT NULL,
    `status`         VARCHAR(255) NULL DEFAULT NULL,
    `title`          VARCHAR(255) NULL DEFAULT NULL,
    `to_date`        DATE         NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`combo_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`combo_item`
(
    `discount_item_id` BIGINT NOT NULL AUTO_INCREMENT,
    `discount_id`      BIGINT NOT NULL,
    `product_id`       BIGINT NOT NULL,
    PRIMARY KEY (`discount_item_id`),
    INDEX `FKslvgydnx95qnh2cupnyhlxd8c` (`discount_id` ASC) VISIBLE,
    INDEX `FK4ge3twm5tdeijqahqqxf4h9ob` (`product_id` ASC) VISIBLE,
    CONSTRAINT `FK4ge3twm5tdeijqahqqxf4h9ob`
        FOREIGN KEY (`product_id`)
            REFERENCES `standardcoffeeshopapp`.`product` (`id`),
    CONSTRAINT `FKslvgydnx95qnh2cupnyhlxd8c`
        FOREIGN KEY (`discount_id`)
            REFERENCES `standardcoffeeshopapp`.`discount` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`config_param`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`config_param`
(
    `id`    VARCHAR(45)  NOT NULL,
    `value` VARCHAR(150) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`discount_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`discount_item`
(
    `discount_item_id` BIGINT NOT NULL AUTO_INCREMENT,
    `discount_id`      BIGINT NOT NULL,
    `product_id`       BIGINT NOT NULL,
    PRIMARY KEY (`discount_item_id`),
    INDEX `FK1ovav2qpof9ayr4equis62hr0` (`discount_id` ASC) VISIBLE,
    INDEX `FK5p4cgllwloc1g2qarpnifoikt` (`product_id` ASC) VISIBLE,
    CONSTRAINT `FK1ovav2qpof9ayr4equis62hr0`
        FOREIGN KEY (`discount_id`)
            REFERENCES `standardcoffeeshopapp`.`discount` (`id`),
    CONSTRAINT `FK5p4cgllwloc1g2qarpnifoikt`
        FOREIGN KEY (`product_id`)
            REFERENCES `standardcoffeeshopapp`.`product` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`user`
(
    `id`                      BIGINT       NOT NULL AUTO_INCREMENT,
    `account_non_expired`     BIT(1)       NULL DEFAULT NULL,
    `account_non_locked`      BIT(1)       NULL DEFAULT NULL,
    `credential_non_expired`  BIT(1)       NULL DEFAULT NULL,
    `enable`                  BIT(1)       NULL DEFAULT NULL,
    `password`                VARCHAR(255) NULL DEFAULT NULL,
    `use_google_2fa`          BIT(1)       NULL DEFAULT NULL,
    `user_google_2_fa_secret` VARCHAR(255) NULL DEFAULT NULL,
    `username`                VARCHAR(255) NULL DEFAULT NULL,
    `customer_id`             BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`user_pass`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`user_pass`
(
    `id`
              INT
                           NOT
                               NULL,
    `password`
              VARCHAR(255) NULL,
    `user_id` BIGINT       NOT NULL,
    PRIMARY KEY
        (
         `id`
            ),
    INDEX `fk_user_pass_user1_idx`
        (
         `user_id`
         ASC
            ) VISIBLE,
    CONSTRAINT `fk_user_pass_user1`
        FOREIGN KEY
            (
             `user_id`
                )
            REFERENCES `standardcoffeeshopapp`.`user`
                (
                 `id`
                    )
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`login_failure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`login_failure`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME     NULL DEFAULT NULL,
    `last_modified_date` DATETIME     NULL DEFAULT NULL,
    `source_ip`          VARCHAR(255) NULL DEFAULT NULL,
    `username`           VARCHAR(255) NULL DEFAULT NULL,
    `user_id`            BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK7yuqycsl6io9aivn03yr2hiia` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FK7yuqycsl6io9aivn03yr2hiia`
        FOREIGN KEY (`user_id`)
            REFERENCES `standardcoffeeshopapp`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`login_success`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`login_success`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_date`       DATETIME     NULL DEFAULT NULL,
    `last_modified_date` DATETIME     NULL DEFAULT NULL,
    `source_ip`          VARCHAR(255) NULL DEFAULT NULL,
    `username`           VARCHAR(255) NULL DEFAULT NULL,
    `user_id`            BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKbhp83p080sodt4vrtcgbsdl9e` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FKbhp83p080sodt4vrtcgbsdl9e`
        FOREIGN KEY (`user_id`)
            REFERENCES `standardcoffeeshopapp`.`user` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`customer`
(
    id            BIGINT       NOT NULL,
    date_create   date         NULL,
    time_create   time         NULL,
    who_create    BIGINT       NULL,
    version       BIGINT       NULL,
    date_update   date         NULL,
    time_update   time         NULL,
    who_update    BIGINT       NULL,
    customer_name VARCHAR(255) NULL,
    api_key       VARCHAR(255) NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`order_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`order_request`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `date_create`        DATE         NULL DEFAULT NULL,
    `date_update`        DATE         NULL DEFAULT NULL,
    `time_create`        TIME         NULL DEFAULT NULL,
    `time_update`        TIME         NULL DEFAULT NULL,
    `version`            BIGINT       NULL DEFAULT NULL,
    `who_create`         BIGINT       NULL DEFAULT NULL,
    `who_update`         BIGINT       NULL DEFAULT NULL,
    `order_date_time`    DATETIME     NULL DEFAULT NULL,
    `promotion_discount` DOUBLE       NULL DEFAULT '0',
    `status`             VARCHAR(255) NULL DEFAULT NULL,
    `total_amount`       DOUBLE       NULL DEFAULT NULL,
    `discount`           DOUBLE       NULL DEFAULT '0',
    `total_quantity`     BIGINT       NULL DEFAULT NULL,
    `customer_id`        BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`order_request_request_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`order_request_request_item`
(
    `order_request_items_id` BIGINT NOT NULL AUTO_INCREMENT,
    `discount`               DOUBLE NULL DEFAULT NULL,
    `price_unit`             DOUBLE NULL DEFAULT NULL,
    `order_request_id`       BIGINT NOT NULL,
    `product_id`             BIGINT NOT NULL,
    PRIMARY KEY (`order_request_items_id`),
    INDEX `FKl8xskl4ihjd9psceluoqxe51b` (`order_request_id` ASC) VISIBLE,
    INDEX `FK8fynf5octydlmjuca7akemlnn` (`product_id` ASC) VISIBLE,
    CONSTRAINT `FK8fynf5octydlmjuca7akemlnn`
        FOREIGN KEY (`product_id`)
            REFERENCES `standardcoffeeshopapp`.`product` (`id`),
    CONSTRAINT `FKl8xskl4ihjd9psceluoqxe51b`
        FOREIGN KEY (`order_request_id`)
            REFERENCES `standardcoffeeshopapp`.`order_request` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 13
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`persistent_logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`persistent_logins`
(
    `username`  VARCHAR(64) NOT NULL,
    `series`    VARCHAR(64) NOT NULL,
    `token`     VARCHAR(64) NOT NULL,
    `last_used` TIMESTAMP   NOT NULL,
    PRIMARY KEY (`series`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`role`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`role_authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`role_authority`
(
    `role_id`      BIGINT NOT NULL,
    `authority_id` BIGINT NOT NULL,
    PRIMARY KEY (`role_id`, `authority_id`),
    INDEX `FKqbri833f7xop13bvdje3xxtnw` (`authority_id` ASC) VISIBLE,
    CONSTRAINT `FK2052966dco7y9f97s1a824bj1`
        FOREIGN KEY (`role_id`)
            REFERENCES `standardcoffeeshopapp`.`role` (`id`),
    CONSTRAINT `FKqbri833f7xop13bvdje3xxtnw`
        FOREIGN KEY (`authority_id`)
            REFERENCES `standardcoffeeshopapp`.`authority` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`user_role`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    INDEX `FKa68196081fvovjhkek5m97n3y` (`role_id` ASC) VISIBLE,
    CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o`
        FOREIGN KEY (`user_id`)
            REFERENCES `standardcoffeeshopapp`.`user` (`id`),
    CONSTRAINT `FKa68196081fvovjhkek5m97n3y`
        FOREIGN KEY (`role_id`)
            REFERENCES `standardcoffeeshopapp`.`role` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`user_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`user_session`
(
    `user_id`         VARCHAR(64)   NOT NULL,
    `active_sessions` VARCHAR(1024) NULL DEFAULT NULL,
    `application`     VARCHAR(1)    NULL DEFAULT NULL,
    `timestamp`       DATETIME      NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `standardcoffeeshopapp`.`persistent_logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `standardcoffeeshopapp`.`persistent_logins`
(
    `username`  varchar(64) not null,
    `series`    varchar(64) primary key,
    `token`     varchar(64) not null,
    `last_used` timestamp   not null
);

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

INSERT INTO `standardcoffeeshopapp`.`user`(`id`, `username`, `password`, `account_non_expired`, `account_non_locked`,
                                           `credential_non_expired`, `enable`, `use_google_2fa`)
VALUES (2, 'user', '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', true, true, true, true,
        false);
INSERT INTO `standardcoffeeshopapp`.`user`(`id`, `username`, `password`, `account_non_expired`, `account_non_locked`,
                                           `credential_non_expired`, `enable`, `use_google_2fa`)
VALUES (3, 'customer', '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', true, true, true, true,
        false);
INSERT INTO `standardcoffeeshopapp`.`user`(`id`, `username`, `password`, `account_non_expired`, `account_non_locked`,
                                           `credential_non_expired`, `enable`, `use_google_2fa`)
VALUES (1, 'admin', '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', true, true, true, true,
        false);

INSERT INTO `standardcoffeeshopapp`.`user_pass`(`id`, `password`, `user_id`)
VALUES (2, '${bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', 2);
INSERT INTO `standardcoffeeshopapp`.`user_pass`(`id`, `password`, `user_id`)
VALUES (3, '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', 3);
INSERT INTO `standardcoffeeshopapp`.`user_pass`(`id`, `password`, `user_id`)
VALUES (1, '{bcrypt}$2a$10$3w//CgAicchkVo0iCYJ0W.oXv911VdgfHsuS0hFks5joDpFFakUS2', 1);

INSERT INTO `standardcoffeeshopapp`.`role` (`id`, `name`)
VALUES (1, 'ADMIN');
INSERT INTO `standardcoffeeshopapp`.`role` (`id`, `name`)
VALUES (2, 'USER'); -- user application internal
INSERT INTO `standardcoffeeshopapp`.`role` (`id`, `name`)
VALUES (3, 'CUSTOMER'); -- user application external or second role
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (1, 'ORDER_OPEN');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (2, 'ORDER_ADD');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (3, 'ORDER_REMOVE');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (4, 'ORDER_CLOSE');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (5, 'ORDER_PRINT');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (6, 'ORDER_DELETE');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (7, 'ORDER_REOPEN');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (8, 'ORDER_SEARCH');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (9, 'CUSTOMER_SEARCH');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (10, 'PRODUCT_PRINT');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (11, 'PRODUCT_SEARCH');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (12, 'PRODUCT_ADD');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (13, 'PRODUCT_DELETE');
INSERT INTO `standardcoffeeshopapp`.`authority`(`id`, `permission`)
VALUES (14, 'PRODUCT_UPDATE');
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (1, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (2, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (3, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (4, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (5, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (6, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (7, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (8, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (9, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (9, 3);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (10, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (11, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (12, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (13, 1);
INSERT INTO `standardcoffeeshopapp`.`role_authority` (`authority_id`, `role_id`)
VALUES (14, 1);
INSERT INTO `standardcoffeeshopapp`.`user_role` (`role_id`, `user_id`)
VALUES (1, 1);
INSERT INTO `standardcoffeeshopapp`.`user_role` (`role_id`, `user_id`)
VALUES (2, 2);
INSERT INTO `standardcoffeeshopapp`.`user_role` (`role_id`, `user_id`)
VALUES (3, 3);

INSERT INTO `standardcoffeeshopapp`.`product` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,
                                               `who_update`, `who_create`, `date_update`, `date_create`, `time_create`,
                                               `version`)
VALUES ('1', 'Latte', 'Latte', 'available', 100, 5.3, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(), 0);
INSERT INTO `standardcoffeeshopapp`.`product` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,
                                               `who_update`, `who_create`, `date_update`, `date_create`, `time_create`,
                                               `version`)
VALUES ('2', 'Espresso', 'Espresso', 'available', 100, 4.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(), 0);
INSERT INTO `standardcoffeeshopapp`.`product` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,
                                               `who_update`, `who_create`, `date_update`, `date_create`, `time_create`,
                                               `version`)
VALUES ('3', 'Sandwich', 'Sandwich', 'available', 100, 10.10, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(), 0);
INSERT INTO `standardcoffeeshopapp`.`product` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,
                                               `who_update`, `who_create`, `date_update`, `date_create`, `time_create`,
                                               `version`)
VALUES ('4', 'Milk', 'Milk', 'available', 100, 1.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        0);
INSERT INTO `standardcoffeeshopapp`.`product` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,
                                               `who_update`, `who_create`, `date_update`, `date_create`, `time_create`,
                                               `version`)
VALUES ('5', 'Cake Slice', 'Cake Slice', 'available', 100, 9.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(), 0);
INSERT INTO `standardcoffeeshopapp`.`product` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,
                                               `who_update`, `who_create`, `date_update`, `date_create`, `time_create`,
                                               `version`)
VALUES ('6', 'Capuccino', 'Capuccino', 'available', 100, 8.0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(), 0);
INSERT INTO `standardcoffeeshopapp`.`product` (`id`, `name`, `description`, `status`, `quantity`, `price_unit`,
                                               `who_update`, `who_create`, `date_update`, `date_create`, `time_create`,
                                               `version`)
VALUES ('7', 'Tea', 'Tea', 'available', 100, 6.1, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        0);

INSERT INTO `standardcoffeeshopapp`.`discount` (`id`, `date_create`, `date_update`, `time_create`, `time_update`,
                                                `version`, `who_create`, `who_update`, `discount`, `start_date`,
                                                `status`, `to_date`, quantity_items, discount_type)
VALUES ('1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 0, 0, 5.0,
        CURRENT_TIMESTAMP(), 'Active', CURRENT_TIMESTAMP(), 8, 'DISCOUNT_ORDER');

INSERT INTO `standardcoffeeshopapp`.`order_request` (`id`, `date_create`, `date_update`, `time_create`, `time_update`,
                                                     `version`, `who_create`, `who_update`, `order_date_time`,
                                                     `promotion_discount`, `status`, `total_amount`, `discount`,
                                                     `total_quantity`)
VALUES ('1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0,
        CURRENT_TIMESTAMP(), 0, 'CLOSE', 21.1, 0.0, 4);
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('1', 0, '1', 6.1, '7');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('2', 0, '1', 4.0, '2');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('3', 0, '1', 4.0, '2');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('4', 0, '1', 6.0, '6');

INSERT INTO `standardcoffeeshopapp`.`order_request` (`id`, `date_create`, `date_update`, `time_create`, `time_update`,
                                                     `version`, `who_create`, `who_update`, `order_date_time`,
                                                     `promotion_discount`, `status`, `total_amount`, `discount`,
                                                     `total_quantity`)
VALUES ('2', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0, 0, 0,
        CURRENT_TIMESTAMP(), 0, 'OPEN', 12.0, 0.0, 4);
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('6', 0, '2', 4.0, '2');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('5', 0, '2', 5.3, '1');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('7', 0, '2', 10.10, '3');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('8', 0, '2', 1.0, '4');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('9', 0, '2', 9.0, '5');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('10', 0, '2', 8.0, '6');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('11', 0, '2', 8.0, '6');
INSERT INTO `standardcoffeeshopapp`.`order_request_request_item` (`order_request_items_id`, `discount`,
                                                                  `order_request_id`, `price_unit`, `product_id`)
VALUES ('12', 0, '2', 8.0, '6');

COMMIT;