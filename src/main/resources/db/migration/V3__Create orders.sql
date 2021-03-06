CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_email VARCHAR(50) NOT NULL,
    ordered_at TIMESTAMP NOT NULL,
    address VARCHAR(100) NOT NULL,
    total DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (customer_email) REFERENCES customers(email) ON DELETE CASCADE
) engine=InnoDB;
