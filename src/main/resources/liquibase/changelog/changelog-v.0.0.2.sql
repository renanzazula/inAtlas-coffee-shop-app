CREATE TABLE `standardcoffeeshopapp`.`user_pass`
(
    `id`       INT          NOT NULL,
    `password` VARCHAR(255) NULL,
    `user_id`  BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_pass_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_pass_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `standardcoffeeshopapp`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB