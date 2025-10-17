CREATE DATABASE gym;
USE gym;
CREATE TABLE administrator(
	ID INT AUTO_INCREMENT NOT NULL,
	surnames varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	dni varchar(9) NOT NULL,
	password varchar(255) NOT NULL,
	state tinyint(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (ID)
);

CREATE TABLE instructor(
    ID INT AUTO_INCREMENT NOT NULL,
    surnames varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    dni varchar(9) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE users(
    ID INT AUTO_INCREMENT NOT NULL,
    surnames varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    dni varchar(9) NOT NULL,
    password varchar(255) NOT NULL,
    mail varchar(255),
    phone int,
    IBAN varchar(34) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE classes(
    ID INT AUTO_INCREMENT NOT NULL,
    name varchar(255) NOT NULL,
    date varchar(20) NOT NULL,
    capacity int NOT NULL,
    start_time varchar(10) NOT NULL,
    end_time varchar(10) NOT NULL,
    fk_id_instructor int,
    PRIMARY KEY (ID),
    FOREIGN KEY (fk_id_instructor) REFERENCES instructor(ID)
);

CREATE TABLE reservation(
    status tinyint(1) NOT NULL DEFAULT 0,
    fk_id_user int,
    fk_id_class int,
    FOREIGN KEY (fk_id_user) REFERENCES users(ID),
    FOREIGN KEY (fk_id_class) REFERENCES classes(ID)
);

INSERT INTO administrator (surnames, name, dni, password)
VALUES
('Bartrès Fedyshyn', 'Artur', '41027555V', '333');

INSERT INTO instructor (surnames, name, dni, password)
VALUES
('Garcia Lopez', 'Anna', '12345678A', 'contrasenya1'),
('Martinez Ruiz', 'Pere', '23456789B', 'contrasenya2'),
('Sanchez Torres', 'Laura', '34567890C', 'contrasenya3');

INSERT INTO classes (name, date, start_time, end_time, fk_id_instructor, aforament)
VALUES
-- Dilluns (06:00–00:00)
('Spinning', 'dilluns', '06:00', '07:00', 1, 20),
('Ioga', 'dilluns', '07:00', '08:00', 2, 20),
('BodyPump', 'dilluns', '08:00', '09:00', 3, 20),
('Crossfit', 'dilluns', '09:00', '10:00', 1, 20),
('Zumba', 'dilluns', '10:00', '11:00', 2, 20),
('Pilates', 'dilluns', '11:00', '12:00', 3, 20),
('Boxa', 'dilluns', '12:00', '13:00', 1, 20),
('HIIT', 'dilluns', '13:00', '14:00', 2, 20),
('Step', 'dilluns', '14:00', '15:00', 3, 20),
('Ioga', 'dilluns', '15:00', '16:00', 1, 20),
('Crossfit', 'dilluns', '16:00', '17:00', 2, 20),
('Zumba', 'dilluns', '17:00', '18:00', 3, 20),
('BodyCombat', 'dilluns', '18:00', '19:00', 1, 20),
('Pilates', 'dilluns', '19:00', '20:00', 2, 20),
('Spinning', 'dilluns', '20:00', '21:00', 3, 20),
('Stretching', 'dilluns', '21:00', '22:00', 1, 20),
('Cardio', 'dilluns', '22:00', '23:00', 2, 20),
('Zumba', 'dilluns', '23:00', '00:00', 3, 20),

-- Dimarts (06:00–00:00)
('Crossfit', 'dimarts', '06:00', '07:00', 1, 20),
('Pilates', 'dimarts', '07:00', '08:00', 2, 20),
('Spinning', 'dimarts', '08:00', '09:00', 3, 20),
('Ioga', 'dimarts', '09:00', '10:00', 1, 20),
('BodyPump', 'dimarts', '10:00', '11:00', 2, 20),
('Zumba', 'dimarts', '11:00', '12:00', 3, 20),
('HIIT', 'dimarts', '12:00', '13:00', 1, 20),
('Boxa', 'dimarts', '13:00', '14:00', 2, 20),
('Step', 'dimarts', '14:00', '15:00', 3, 20),
('Crossfit', 'dimarts', '15:00', '16:00', 1, 20),
('Ioga', 'dimarts', '16:00', '17:00', 2, 20),
('BodyCombat', 'dimarts', '17:00', '18:00', 3, 20),
('Pilates', 'dimarts', '18:00', '19:00', 1, 20),
('Spinning', 'dimarts', '19:00', '20:00', 2, 20),
('Zumba', 'dimarts', '20:00', '21:00', 3, 20),
('Cardio', 'dimarts', '21:00', '22:00', 1, 20),
('Stretching', 'dimarts', '22:00', '23:00', 2, 20),
('HIIT', 'dimarts', '23:00', '00:00', 3, 20),

-- Dimecres (06:00–00:00)
('Ioga', 'dimecres', '06:00', '07:00', 1, 20),
('Crossfit', 'dimecres', '07:00', '08:00', 2, 20),
('Pilates', 'dimecres', '08:00', '09:00', 3, 20),
('BodyPump', 'dimecres', '09:00', '10:00', 1, 20),
('Zumba', 'dimecres', '10:00', '11:00', 2, 20),
('Spinning', 'dimecres', '11:00', '12:00', 3, 20),
('Boxa', 'dimecres', '12:00', '13:00', 1, 20),
('HIIT', 'dimecres', '13:00', '14:00', 2, 20),
('Step', 'dimecres', '14:00', '15:00', 3, 20),
('BodyCombat', 'dimecres', '15:00', '16:00', 1, 20),
('Ioga', 'dimecres', '16:00', '17:00', 2, 20),
('Crossfit', 'dimecres', '17:00', '18:00', 3, 20),
('Pilates', 'dimecres', '18:00', '19:00', 1, 20),
('Zumba', 'dimecres', '19:00', '20:00', 2, 20),
('Cardio', 'dimecres', '20:00', '21:00', 3, 20),
('Stretching', 'dimecres', '21:00', '22:00', 1, 20),
('Spinning', 'dimecres', '22:00', '23:00', 2, 20),
('BodyPump', 'dimecres', '23:00', '00:00', 3, 20),

-- Dijous (06:00–00:00)
('Crossfit', 'dijous', '06:00', '07:00', 1, 20),
('Zumba', 'dijous', '07:00', '08:00', 2, 20),
('Ioga', 'dijous', '08:00', '09:00', 3, 20),
('BodyPump', 'dijous', '09:00', '10:00', 1, 20),
('Spinning', 'dijous', '10:00', '11:00', 2, 20),
('HIIT', 'dijous', '11:00', '12:00', 3, 20),
('Boxa', 'dijous', '12:00', '13:00', 1, 20),
('Pilates', 'dijous', '13:00', '14:00', 2, 20),
('BodyCombat', 'dijous', '14:00', '15:00', 3, 20),
('Zumba', 'dijous', '15:00', '16:00', 1, 20),
('Step', 'dijous', '16:00', '17:00', 2, 20),
('Crossfit', 'dijous', '17:00', '18:00', 3, 20),
('Ioga', 'dijous', '18:00', '19:00', 1, 20),
('BodyPump', 'dijous', '19:00', '20:00', 2, 20),
('Cardio', 'dijous', '20:00', '21:00', 3, 20),
('Pilates', 'dijous', '21:00', '22:00', 1, 20),
('Stretching', 'dijous', '22:00', '23:00', 2, 20),
('Zumba', 'dijous', '23:00', '00:00', 3, 20),

-- Divendres (06:00–00:00)
('Spinning', 'divendres', '06:00', '07:00', 1, 20),
('Crossfit', 'divendres', '07:00', '08:00', 2, 20),
('BodyPump', 'divendres', '08:00', '09:00', 3, 20),
('Zumba', 'divendres', '09:00', '10:00', 1, 20),
('Pilates', 'divendres', '10:00', '11:00', 2, 20),
('BodyCombat', 'divendres', '11:00', '12:00', 3, 20),
('HIIT', 'divendres', '12:00', '13:00', 1, 20),
('Ioga', 'divendres', '13:00', '14:00', 2, 20),
('Step', 'divendres', '14:00', '15:00', 3, 20),
('Crossfit', 'divendres', '15:00', '16:00', 1, 20),
('Zumba', 'divendres', '16:00', '17:00', 2, 20),
('Boxa', 'divendres', '17:00', '18:00', 3, 20),
('BodyPump', 'divendres', '18:00', '19:00', 1, 20),
('Spinning', 'divendres', '19:00', '20:00', 2, 20),
('Cardio', 'divendres', '20:00', '21:00', 3, 20),
('Stretching', 'divendres', '21:00', '22:00', 1, 20),
('Pilates', 'divendres', '22:00', '23:00', 2, 20),
('Zumba', 'divendres', '23:00', '00:00', 3, 20),

-- Dissabte (08:00–14:00)
('BodyPump', 'dissabte', '08:00', '09:00', 1, 20),
('Ioga', 'dissabte', '09:00', '10:00', 2, 20),
('Crossfit', 'dissabte', '10:00', '11:00', 3, 20),
('Zumba', 'dissabte', '11:00', '12:00', 1, 20),
('Spinning', 'dissabte', '12:00', '13:00', 2, 20),
('Pilates', 'dissabte', '13:00', '14:00', 3, 20);
