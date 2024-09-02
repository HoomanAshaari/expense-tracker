CREATE TABLE category
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    name   VARCHAR(255) NOT NULL,
    budget DECIMAL NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);