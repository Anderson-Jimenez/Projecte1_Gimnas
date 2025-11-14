DROP DATABASE IF EXISTS gym;
CREATE DATABASE gym;
USE gym;

-- ==============================
--  ADMINISTRATOR
-- ==============================
CREATE TABLE administrator (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    surnames VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    dni VARCHAR(9) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    state ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- ==============================
--  INSTRUCTOR
-- ==============================
CREATE TABLE instructor (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    surnames VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    dni VARCHAR(9) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(20),
    address VARCHAR(255),
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- ==============================
--  USERS
-- ==============================
CREATE TABLE users (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    surnames VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    dni VARCHAR(9) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    mail VARCHAR(255),
    phone VARCHAR(20),
    IBAN VARCHAR(34) NOT NULL,
    address VARCHAR(255),
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- ==============================
--  CLASSES
-- ==============================
CREATE TABLE classes (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date VARCHAR(20) NOT NULL,
    start_time VARCHAR(20) NOT NULL,
    end_time VARCHAR(20) NOT NULL,
    capacity INT NOT NULL,
    fk_id_instructor INT,
    FOREIGN KEY (fk_id_instructor) REFERENCES instructor(ID) ON DELETE CASCADE
);

-- ==============================
--  RESERVATION
-- ==============================
CREATE TABLE reservation (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    status ENUM('PENDING', 'CONFIRMED', 'CANCELLED') DEFAULT 'PENDING',
    fk_id_user INT,
    fk_id_class INT,
    FOREIGN KEY (fk_id_user) REFERENCES users(ID) ON DELETE CASCADE,
    FOREIGN KEY (fk_id_class) REFERENCES classes(ID) ON DELETE CASCADE
);
-- =============================
-- 	INSERT ADMINISTRADOR
-- =============================
INSERT INTO administrator (surnames, name, dni, password, state)
VALUES
('López García', 'artur', '99911122A', '1234', 'ACTIVE'),
('admin', 'admin', '0000000A', '1234', 'ACTIVE'),
('Ruiz Fernández', 'anderson', '88822233B', '1234', 'ACTIVE');
-- =====================================
--  INSERTS DE PRUEBA PARA INSTRUCTORS
-- =====================================
INSERT INTO instructor (surnames, name, dni, password, email, phone, address, status)
VALUES
('Gómez López', 'Laura', '12345678A', '1234', 'laura.gomez@gym.com', '600111222', 'Carrer Major 12, BCN', 'ACTIVE'),
('Martínez Ruiz', 'Carlos', '87654321B', '1234', 'carlos.martinez@gym.com', '600222333', 'Av. Diagonal 34, BCN', 'ACTIVE'),
('Santos Pérez', 'Lucía', '23456789C', '1234', 'lucia.santos@gym.com', '600333444', 'C/ Aragó 78, BCN', 'ACTIVE'),
('Rodríguez Díaz', 'David', '34567890D', '1234', 'david.rodriguez@gym.com', '600444555', 'C/ Balmes 45, BCN', 'ACTIVE'),
('Fernández Mora', 'Ana', '45678901E', '1234', 'ana.fernandez@gym.com', '600555666', 'C/ Casanova 89, BCN', 'ACTIVE'),
('Torres Gil', 'Miguel', '56789012F', '1234', 'miguel.torres@gym.com', '600666777', 'C/ Provença 120, BCN', 'ACTIVE');

-- =====================================
--  INSERTS DE PRUEBA PARA CLASSES
-- =====================================
INSERT INTO classes (name, date, start_time, end_time, capacity, fk_id_instructor)
VALUES
-- DILLUNS
('Spinning', 'dilluns', '09:00', '10:00', 20, 1),
('Ioga', 'dilluns', '10:00', '11:00', 20, 2),
('BodyPump', 'dilluns', '11:00', '12:00', 20, 3),
('Crossfit', 'dilluns', '17:00', '18:00', 20, 4),
('Zumba', 'dilluns', '18:00', '19:00', 20, 5),
('Pilates', 'dilluns', '19:00', '20:00', 20, 6),
('Stretching', 'dilluns', '20:00', '21:00', 20, 2),
('Cardio', 'dilluns', '21:00', '22:00', 20, 5),

-- DIMARTS
('Crossfit', 'dimarts', '09:00', '10:00', 20, 4),
('Pilates', 'dimarts', '10:00', '11:00', 20, 6),
('BodyPump', 'dimarts', '11:00', '12:00', 20, 3),
('Spinning', 'dimarts', '17:00', '18:00', 20, 1),
('Zumba', 'dimarts', '18:00', '19:00', 20, 5),
('BodyCombat', 'dimarts', '19:00', '20:00', 20, 3),
('HIIT', 'dimarts', '20:00', '21:00', 20, 6),
('Stretching', 'dimarts', '21:00', '22:00', 20, 2),

-- DIMECRES
('Ioga', 'dimecres', '09:00', '10:00', 20, 2),
('BodyPump', 'dimecres', '10:00', '11:00', 20, 3),
('Crossfit', 'dimecres', '11:00', '12:00', 20, 4),
('Spinning', 'dimecres', '17:00', '18:00', 20, 1),
('Zumba', 'dimecres', '18:00', '19:00', 20, 5),
('Pilates', 'dimecres', '19:00', '20:00', 20, 6),
('BodyCombat', 'dimecres', '20:00', '21:00', 20, 3),
('Cardio', 'dimecres', '21:00', '22:00', 20, 5),

-- DIJOUS
('BodyPump', 'dijous', '09:00', '10:00', 20, 3),
('Pilates', 'dijous', '10:00', '11:00', 20, 6),
('Crossfit', 'dijous', '11:00', '12:00', 20, 4),
('Spinning', 'dijous', '17:00', '18:00', 20, 1),
('Ioga', 'dijous', '18:00', '19:00', 20, 2),
('BodyCombat', 'dijous', '19:00', '20:00', 20, 3),
('HIIT', 'dijous', '20:00', '21:00', 20, 6),
('Stretching', 'dijous', '21:00', '22:00', 20, 2),

-- DIVENDRES
('Spinning', 'divendres', '09:00', '10:00', 20, 1),
('Ioga', 'divendres', '10:00', '11:00', 20, 2),
('BodyPump', 'divendres', '11:00', '12:00', 20, 3),
('Crossfit', 'divendres', '17:00', '18:00', 20, 4),
('Zumba', 'divendres', '18:00', '19:00', 20, 5),
('BodyCombat', 'divendres', '19:00', '20:00', 20, 3),
('Pilates', 'divendres', '20:00', '21:00', 20, 6),
('Cardio', 'divendres', '21:00', '22:00', 20, 5),

-- DISSABTE
('BodyPump', 'dissabte', '09:00', '10:00', 20, 3),
('Ioga', 'dissabte', '10:00', '11:00', 20, 2),
('Crossfit', 'dissabte', '11:00', '12:00', 20, 4),
('Zumba', 'dissabte', '17:00', '18:00', 20, 5),
('Spinning', 'dissabte', '18:00', '19:00', 20, 1),
('Pilates', 'dissabte', '19:00', '20:00', 20, 6),
('Stretching', 'dissabte', '20:00', '21:00', 20, 2),
('BodyCombat', 'dissabte', '21:00', '22:00', 20, 3)
