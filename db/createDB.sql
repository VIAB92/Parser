DROP TABLE rozetka_products;

CREATE TABLE rozetka_products(
    pr_id NUMBER(7) NOT NULL,
    product_type VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    characteristics VARCHAR(255) NOT NULL,
    price NUMBER(7) NOT NULL,
    PRIMARY KEY (pr_id)
    );

CREATE SEQUENCE products
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    NOCYCLE
    NOCACHE;