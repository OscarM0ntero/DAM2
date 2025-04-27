CREATE DATABASE IF NOT EXISTS mh_torremolinos;

USE mh_torremolinos;

CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(50),
    apellidos VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    contrasena VARCHAR(255),
    telefono VARCHAR(50),
    rol ENUM('Cliente', 'Administrador') DEFAULT 'Cliente',
    PRIMARY KEY (id_usuario)
);

CREATE TABLE Reserva (
    id_reserva INT AUTO_INCREMENT NOT NULL,
    id_usuario INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    n_personas INT,
    nota_adicional TEXT,
    estado_reserva ENUM('Pendiente', 'Confirmada', 'Rechazada', 'Cancelada') DEFAULT 'Pendiente',
    actualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_reserva),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Comentario (
    id_comentario INT AUTO_INCREMENT NOT NULL,
    id_reserva INT,
    comentario TEXT,
    calificacion INT CHECK (calificacion >= 1 AND calificacion <= 10),
    fecha_comentario DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_comentario),
    FOREIGN KEY (id_reserva) REFERENCES Reserva(id_reserva)
);

CREATE TABLE Noche (
    id_noche INT AUTO_INCREMENT NOT NULL,
    fecha DATE,
    precio DECIMAL(10, 2),
    estado ENUM('abierto', 'cerrado'),
    actualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_reserva INT,
    PRIMARY KEY (id_noche),
    FOREIGN KEY (id_reserva) REFERENCES Reserva(id_reserva)
);

CREATE TABLE Imagen (
    id_imagen INT AUTO_INCREMENT NOT NULL,
    url_imagen VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    PRIMARY KEY (id_imagen)
);

CREATE TABLE Contenido (
    id_contenido INT AUTO_INCREMENT NOT NULL,
    titulo VARCHAR(255),
    titulo_en VARCHAR(255),
    texto TEXT,
    texto_en TEXT,
    pagina ENUM('Inicio', 'Galería', 'Localización', 'Reservar', 'Contacto', 'Informacion_legal') NOT NULL,
    PRIMARY KEY (id_contenido)
);
