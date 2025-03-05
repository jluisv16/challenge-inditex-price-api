DROP TABLE IF EXISTS price;
DROP TABLE IF EXISTS brand;
DROP TABLE IF EXISTS product;

CREATE TABLE brand (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE price (
    id BIGINT PRIMARY KEY,
    brand_id BIGINT,
    product_id BIGINT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    price_list INT,
    priority INT,
    price DECIMAL(10, 2),
    currency VARCHAR(3),
    FOREIGN KEY (brand_id) REFERENCES brand(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
