CREATE TABLE authority
(
    id         BIGINT       NOT NULL,
    permission VARCHAR(255) NULL,
    CONSTRAINT pk_authority PRIMARY KEY (id)
);

CREATE TABLE combo_item
(
    discount_item_id VARCHAR(255) NOT NULL,
    discount_id      VARCHAR(255) NOT NULL,
    product_id       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_combo_item PRIMARY KEY (discount_item_id)
);

CREATE TABLE config_param
(
    id    VARCHAR(45)  NOT NULL,
    value VARCHAR(150) NOT NULL,
    CONSTRAINT pk_config_param PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id            VARCHAR(255) NOT NULL,
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

CREATE TABLE discount
(
    id             VARCHAR(255) NOT NULL,
    date_create    date         NULL,
    time_create    time         NULL,
    who_create     BIGINT       NULL,
    version        BIGINT       NULL,
    date_update    date         NULL,
    time_update    time         NULL,
    who_update     BIGINT       NULL,
    title          VARCHAR(255) NULL,
    status         VARCHAR(255) NULL,
    start_date     date         NULL,
    to_date        date         NULL,
    amount_from    DOUBLE       NULL,
    amount_to      DOUBLE       NULL,
    discount       DOUBLE       NULL,
    quantity_items DOUBLE       NULL,
    discount_type  VARCHAR(255) NULL,
    CONSTRAINT pk_discount PRIMARY KEY (id)
);

CREATE TABLE discount_item
(
    discount_item_id VARCHAR(255) NOT NULL,
    discount_id      VARCHAR(255) NOT NULL,
    product_id       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_discount_item PRIMARY KEY (discount_item_id)
);

CREATE TABLE login_failure
(
    id                 BIGINT       NOT NULL,
    username           VARCHAR(255) NULL,
    user_id            BIGINT       NULL,
    source_ip          VARCHAR(255) NULL,
    created_date       datetime     NULL,
    last_modified_date datetime     NULL,
    CONSTRAINT pk_login_failure PRIMARY KEY (id)
);

CREATE TABLE login_success
(
    id                 BIGINT       NOT NULL,
    user_id            BIGINT       NULL,
    username           VARCHAR(255) NULL,
    source_ip          VARCHAR(255) NULL,
    created_date       datetime     NULL,
    last_modified_date datetime     NULL,
    CONSTRAINT pk_login_success PRIMARY KEY (id)
);

CREATE TABLE order_request
(
    id                 VARCHAR(255) NOT NULL,
    date_create        date         NULL,
    time_create        time         NULL,
    who_create         BIGINT       NULL,
    version            BIGINT       NULL,
    date_update        date         NULL,
    time_update        time         NULL,
    who_update         BIGINT       NULL,
    order_date_time    datetime     NULL,
    status             VARCHAR(255) NULL,
    total_quantity     BIGINT       NULL,
    total_amount       DOUBLE       NULL,
    discount           DOUBLE       NULL,
    promotion_discount DOUBLE       NULL,
    customer_id        VARCHAR(255) NULL,
    CONSTRAINT pk_order_request PRIMARY KEY (id)
);

CREATE TABLE order_request_request_item
(
    order_request_items_id VARCHAR(255) NOT NULL,
    order_request_id       VARCHAR(255) NOT NULL,
    product_id             VARCHAR(255) NOT NULL,
    discount               DOUBLE       NULL,
    price_unit             DOUBLE       NULL,
    CONSTRAINT pk_order_request_request_item PRIMARY KEY (order_request_items_id)
);

CREATE TABLE product
(
    id            VARCHAR(255) NOT NULL,
    date_create   date         NULL,
    time_create   time         NULL,
    who_create    BIGINT       NULL,
    version       BIGINT       NULL,
    date_update   date         NULL,
    time_update   time         NULL,
    who_update    BIGINT       NULL,
    name          VARCHAR(45)  NOT NULL,
    `description` VARCHAR(100) NULL,
    status        VARCHAR(255) NULL,
    quantity      BIGINT       NULL,
    price_unit    DOUBLE       NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE role_authority
(
    authority_id BIGINT NOT NULL,
    role_id      BIGINT NOT NULL,
    CONSTRAINT pk_role_authority PRIMARY KEY (authority_id, role_id)
);

CREATE TABLE user
(
    id                      BIGINT       NOT NULL,
    username                VARCHAR(255) NULL,
    password                VARCHAR(255) NULL,
    customer_id             VARCHAR(255) NULL,
    account_non_expired     BIT(1)       NULL,
    account_non_locked      BIT(1)       NULL,
    credential_non_expired  BIT(1)       NULL,
    enable                  BIT(1)       NULL,
    use_google_2fa          BIT(1)       NULL,
    user_google_2_fa_secret VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

CREATE TABLE user_session
(
    user_id         VARCHAR(64)   NOT NULL,
    active_sessions VARCHAR(1024) NULL,
    timestamp       datetime      NULL,
    application     VARCHAR(1)    NULL,
    CONSTRAINT pk_user_session PRIMARY KEY (user_id)
);

ALTER TABLE combo_item
    ADD CONSTRAINT FK_COMBO_ITEM_ON_DISCOUNT FOREIGN KEY (discount_id) REFERENCES discount (id);

ALTER TABLE combo_item
    ADD CONSTRAINT FK_COMBO_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE discount_item
    ADD CONSTRAINT FK_DISCOUNT_ITEM_ON_DISCOUNT FOREIGN KEY (discount_id) REFERENCES discount (id);

ALTER TABLE discount_item
    ADD CONSTRAINT FK_DISCOUNT_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE login_failure
    ADD CONSTRAINT FK_LOGIN_FAILURE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE order_request
    ADD CONSTRAINT FK_ORDER_REQUEST_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE order_request_request_item
    ADD CONSTRAINT FK_ORDER_REQUEST_REQUEST_ITEM_ON_ORDER_REQUEST FOREIGN KEY (order_request_id) REFERENCES order_request (id);

ALTER TABLE order_request_request_item
    ADD CONSTRAINT FK_ORDER_REQUEST_REQUEST_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE role_authority
    ADD CONSTRAINT fk_rolaut_on_authority_entity FOREIGN KEY (authority_id) REFERENCES authority (id);

ALTER TABLE role_authority
    ADD CONSTRAINT fk_rolaut_on_role_entity FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_role_entity FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_user_entity FOREIGN KEY (user_id) REFERENCES user (id);