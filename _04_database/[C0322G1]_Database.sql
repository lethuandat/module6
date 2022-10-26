create database farm;

use farm;

CREATE TABLE `users` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(45) UNIQUE,
    `password` VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    creation_date DATE,
    is_deleted BIT DEFAULT 0
);
CREATE TABLE `roles` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(45),
    is_deleted BIT DEFAULT 0
);
CREATE TABLE user_role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    is_deleted BIT DEFAULT 0,
    user_id INT,
    role_id INT,
    FOREIGN KEY (user_id)
        REFERENCES `users` (id),
    FOREIGN KEY (role_id)
        REFERENCES `roles` (id)
);

CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `code` VARCHAR(10) UNIQUE,
    `name` VARCHAR(30),
    email VARCHAR(50) UNIQUE,
    birth_day VARCHAR(10),
    gender VARCHAR(10),
    id_card VARCHAR(15) UNIQUE,
    image TEXT,
    is_deleted BIT DEFAULT 0,
    users_id INT,
    FOREIGN KEY (users_id)
        REFERENCES users (id)
);

CREATE TABLE pigsty (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `code` VARCHAR(30) NOT NULL,
    creation_date DATE NOT NULL,
    build_date DATE,
    max_number INT NOT NULL,
    is_deleted BIT DEFAULT 0,
    employee_id INT,
    FOREIGN KEY (employee_id)
        REFERENCES employee (id)
);

CREATE TABLE export_port (
    id INT PRIMARY KEY AUTO_INCREMENT,
    company VARCHAR(255),
    start_day DATE,
    amount INT,
    kilogram DOUBLE,
    price DOUBLE,
    total_money DOUBLE,
    is_deleted BIT DEFAULT 0,
    employee_id INT,
    FOREIGN KEY (employee_id)
        REFERENCES employee (id),
    pigsty_id INT,
    FOREIGN KEY (pigsty_id)
        REFERENCES pigsty (id)
);

CREATE TABLE vaccination (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vaccination_date DATE NOT NULL,
    amount INT,
    type_of_vaccine VARCHAR(45),
    vaccinated_person VARCHAR(45),
    note VARCHAR(255),
    is_deleted BIT DEFAULT 0,
    pigsty_id INT,
    FOREIGN KEY (pigsty_id)
        REFERENCES pigsty (id)
);

CREATE TABLE pig (
    id INT PRIMARY KEY,
    id_pig VARCHAR(10),
    date_in DATE,
    date_out DATE,
    `status` VARCHAR(20),
    weight VARCHAR(20),
    is_deleted BIT DEFAULT 0,
    pigsty_id INT,
    FOREIGN KEY (pigsty_id)
        REFERENCES pigsty (id)
);

CREATE TABLE treatment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    treatment_date DATE,
    doctor VARCHAR(50),
    diseases VARCHAR(255),
    medicine VARCHAR(5),
    amount INT,
    is_deleted BIT DEFAULT 0,
    id_pig INT,
    FOREIGN KEY (id_pig)
        REFERENCES pig (id)
);
    
CREATE TABLE `storage` (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    food_type VARCHAR(30),
    amount INT(10),
    unit VARCHAR(15),
    date_storage DATETIME,
    is_deleted BIT DEFAULT 0
);
    
CREATE TABLE food (
    id INT AUTO_INCREMENT PRIMARY KEY,
    food_type VARCHAR(30),
    amount INT,
    unit VARCHAR(15),
    id_storage INT NOT NULL,
    FOREIGN KEY (id_storage)
        REFERENCES storage (id),
    pigsty_id INT NOT NULL,
    FOREIGN KEY (pigsty_id)
        REFERENCES pigsty (id),
    is_deleted BIT DEFAULT 0
);

CREATE TABLE notification (
    id INT PRIMARY KEY,
    content VARCHAR(1000),
    submitted_date DATE,
    image VARCHAR(100),
    is_deleted BIT DEFAULT 0
);

CREATE TABLE placement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255)
);

CREATE TABLE advertisement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    image VARCHAR(255),
    submitted_date DATE,
    time_of_existence VARCHAR(50),
    placement_id INT,
    is_deleted BIT DEFAULT 0,
    FOREIGN KEY (placement_id)
        REFERENCES placement (id)
);

CREATE TABLE contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    email VARCHAR(45),
    phone_number VARCHAR(45),
    address VARCHAR(45),
    content VARCHAR(255),
    `date` DATE,
    is_deleted BIT DEFAULT 0
);







