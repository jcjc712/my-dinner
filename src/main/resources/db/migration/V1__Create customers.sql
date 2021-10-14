CREATE TABLE customers (
    email VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(100),
    phone VARCHAR(20)
) engine=InnoDB;
