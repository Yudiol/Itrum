
CREATE TABLE IF NOT EXISTS customers
(
    customer_id          BIGINT    GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name                 VARCHAR   NOT NULL,
    email                VARCHAR   NOT NULL ,
    phone                VARCHAR   NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    order_id               BIGINT    GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    customer_id            BIGINT    REFERENCES customers(customer_id),
    address                VARCHAR   NOT NULL,
    price                  INT       CHECK( price >= 0 ),
    status                 INT       NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    product_id         BIGINT     GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name               VARCHAR    NOT NULL,
    description        VARCHAR,
    price              INT        CHECK( price >= 0 ),
    quantity_in_stock  INT
);

CREATE TABLE IF NOT EXISTS orders_products
(
    order_id       BIGINT REFERENCES orders(order_id),
    product_id     BIGINT REFERENCES products(product_id),
    PRIMARY KEY (  order_id ,product_id)
);
