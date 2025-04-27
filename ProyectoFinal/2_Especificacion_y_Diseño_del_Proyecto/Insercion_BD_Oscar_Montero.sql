-- Crear un usuario administrador MySQL
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'adminpassword';

-- Otorgar privilegios al administrador
GRANT ALL PRIVILEGES ON mh_torremolinos.* TO 'admin'@'localhost';

-- Crear un usuario cliente en MySQL
CREATE USER 'cliente'@'localhost' IDENTIFIED BY 'clientepassword';

-- No otorgar privilegios de administración al cliente
GRANT SELECT, INSERT, UPDATE ON mh_torremolinos.* TO 'cliente'@'localhost';

-- Insertar datos en la tabla Usuario
INSERT INTO Usuario (nombre, apellidos, email, contrasena, telefono, rol)
VALUES 
('Oscar', 'Montero', 'oscar@example.com', 'contraseña123', '123456789', 'Administrador'),
('Antonio', 'Pérez', 'antonio@example.com', 'contraseña456', '987654321', 'Cliente');

-- Insertar datos en la tabla Reserva
INSERT INTO Reserva (id_usuario, fecha_inicio, fecha_fin, n_personas, nota_adicional, estado_reserva)
VALUES 
(1, '2025-03-01', '2025-03-07', 2, 'Reserva para una semana', 'Confirmada'),
(2, '2025-04-01', '2025-04-10', 4, 'Reserva para vacaciones', 'Pendiente');

-- Insertar datos en la tabla Comentario
INSERT INTO Comentario (id_reserva, comentario, calificacion)
VALUES 
(1, 'Muy buena experiencia, excelente lugar', 9),
(2, 'Todo muy bien, pero el servicio podría mejorar', 7);

-- Insertar datos en la tabla Noche
INSERT INTO Noche (fecha, precio, estado, id_reserva)
VALUES 
('2025-05-01', 100.00, 'abierto', 1),
('2025-05-02', 100.00, 'abierto', 1),
('2025-05-03', 120.00, 'cerrado', 1);

-- Insertar datos en la tabla Imagen
INSERT INTO Imagen (url_imagen, descripcion)
VALUES 
('assets/IMG_01.jpg', 'Vista del salón'),
('assets/IMG_03.jpg', 'Vista de la habitación');

-- Insertar datos en la tabla Contenido
INSERT INTO Contenido (titulo, titulo_en, texto, texto_en, pagina)
VALUES 
('Bienvenido a M&H Torremolinos', 'Welcome to M&H Torremolinos', 'Disfruta de un lugar único para tus vacaciones.', 'Enjoy a unique place for your holidays.', 'Inicio'),
('Galería de imágenes', 'Gallery of images', 'Aquí puedes ver algunas fotos del apartamento.', 'Here you can see some photos of the apartment.', 'Galería'),
('Ubicación', 'Location', 'Estamos ubicados en el centro de Torremolinos, a pocos minutos de la playa.', 'We are located in the center of Torremolinos, just minutes from the beach.', 'Localización'),
('Reserva tu estancia', 'Book your stay', 'Haz tu reserva de forma fácil y rápida.', 'Make your booking easily and quickly.', 'Reservar'),
('Contáctanos', 'Contact us', 'Puedes contactarnos por teléfono o email para más información.', 'You can contact us by phone or email for more information.', 'Contacto'),
('Información legal', 'Legal Information', 'Aquí encontrarás todos los detalles legales sobre nuestra política.', 'Here you will find all the legal details about our policy.', 'Informacion_legal');
