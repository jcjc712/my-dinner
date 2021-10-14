CREATE TABLE dishes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    description VARCHAR(100) NOT NULL,
    price DECIMAL(15,2) NOT NULL,
    cuisine ENUM('mexican','italian', 'japanese') DEFAULT 'mexican' NOT NULL,
    status ENUM('available', 'disable') DEFAULT 'disable' NOT NULL
) engine=InnoDB;
