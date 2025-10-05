-- Crear base de datos
CREATE DATABASE mhtorremolinos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mhtorremolinos;

-- Crear tabla contenido
CREATE TABLE contenido (
  id_contenido INT AUTO_INCREMENT PRIMARY KEY,
  titulo_es VARCHAR(255),
  titulo_en VARCHAR(255),
  titulo_de VARCHAR(255),
  titulo_no VARCHAR(255),
  texto_es TEXT,
  texto_en TEXT,
  texto_de TEXT,
  texto_no TEXT,
  pagina VARCHAR(100)
);

-- Crear usuario y darle permisos solo sobre esta base de datos
CREATE USER 'mhuser'@'localhost' IDENTIFIED BY 'db7f098vUy9$d';
GRANT ALL PRIVILEGES ON mhtorremolinos.* TO 'mhuser'@'localhost';
FLUSH PRIVILEGES;


INSERT INTO contenido (
  titulo_es, titulo_en, titulo_de, titulo_no,
  texto_es, texto_en, texto_de, texto_no,
  pagina
) VALUES
-- Contenido 1: Lema
('Lema', 'Slogan', 'Slogan', 'Slagord',
 'Para unas vacaciones ideales.',
 'For an ideal vacation.',
 'Für einen idealen Urlaub.',
 'For en ideell ferie.',
 'home'),

-- Contenido 2: Cama Doble
('Cama Doble', 'Double Bed', 'Doppelbett', 'Dobbeltseng',
 '1 Cama Doble',
 '1 Double Bed',
 '1 Doppelbett',
 '1 Dobbeltseng',
 'home'),

-- Contenido 3: Camas Individuales
('Camas Individuales', 'Single Beds', 'Einzelbetten', 'Enkeltsenger',
 '3 Camas Individuales',
 '3 Single Beds',
 '3 Einzelbetten',
 '3 Enkeltsenger',
 'home'),

-- Contenido 4: Cuna
('Cuna', 'Crib', 'Kinderbett', 'Barneseng',
 '1 Cuna',
 '1 Crib',
 '1 Kinderbett',
 '1 Barneseng',
 'home'),

-- Contenido 5: RELÁJATE. DISFRUTA.
('RELAJATE. DISFRUTA.', 'RELAX. ENJOY.', 'ENTSPANNEN. GENIESSEN.', 'SLAPP AV. NYT.',
 '<p>Vive una estancia única en nuestro bonito apartamento, ubicado en el corazón de Torremolinos, a pocos minutos de la playa.</p>\
<p>Ideal para escapadas familiares o en pareja, con todas las comodidades necesarias para hacer de tu visita una experiencia inolvidable.</p>',
 '<p>Enjoy a unique stay in our beautiful apartment, located in the heart of Torremolinos, just minutes from the beach.</p>\
<p>Perfect for family or couples’ getaways, with all the comforts needed to make your visit unforgettable.</p>',
 '<p>Genießen Sie einen einzigartigen Aufenthalt in unserer schönen Wohnung im Herzen von Torremolinos, nur wenige Minuten vom Strand entfernt.</p>\
<p>Ideal für Familien- oder Paarurlaube, mit allem Komfort für einen unvergesslichen Aufenthalt.</p>',
 '<p>Nyt et unikt opphold i vår vakre leilighet i hjertet av Torremolinos, bare noen minutter fra stranden.</p>\
<p>Perfekt for familieferier eller par, med alle fasiliteter for å gjøre besøket uforglemmelig.</p>',
 'home'),

-- Contenido 6: EL APARTAMENTO
('EL APARTAMENTO', 'THE APARTMENT', 'DIE WOHNUNG', 'LEILIGHETEN',
 '<p>Situado en la zona suroeste de Torremolinos, junto al Parque La Batería, nuestro apartamento en planta baja cuenta con entrada independiente de la calle y acceso directo a la piscina.</p>\
<p>Es un lugar único, donde se combinan la tranquilidad del descanso y la comodidad. Un paraíso a descubrir entre Málaga y Marbella en la Costa del Sol.</p>\
<p>Un alojamiento amplio, acogedor y confortable de 90m², que cuenta con tres dormitorios, dos baños, un amplio salón, comedor, cocina, y todo rodeado de un paisaje idílico.</p>',
 '<p>Located in the southwest area of Torremolinos, next to La Batería Park, our ground floor apartment has a private entrance from the street and direct access to the pool.</p>\
<p>A unique place where tranquility and comfort come together. A paradise to discover between Málaga and Marbella on the Costa del Sol.</p>\
<p>A spacious, cozy and comfortable 90m² accommodation with three bedrooms, two bathrooms, a large living room, dining room, kitchen, all surrounded by an idyllic setting.</p>',
 '<p>Unsere Erdgeschosswohnung befindet sich im Südwesten von Torremolinos, neben dem La Batería Park, mit einem eigenen Eingang von der Straße und direktem Zugang zum Pool.</p>\
<p>Ein einzigartiger Ort, an dem Ruhe und Komfort zusammenkommen. Ein Paradies zwischen Málaga und Marbella an der Costa del Sol.</p>\
<p>Eine geräumige, gemütliche und komfortable Unterkunft von 90 m² mit drei Schlafzimmern, zwei Badezimmern, großem Wohnzimmer, Esszimmer, Küche – alles umgeben von einer idyllischen Umgebung.</p>',
 '<p>Leiligheten vår i første etasje ligger sørvest i Torremolinos, ved siden av La Batería-parken, med egen inngang fra gaten og direkte tilgang til bassenget.</p>\
<p>Et unikt sted hvor ro og komfort møtes. Et paradis å oppdage mellom Málaga og Marbella på Costa del Sol.</p>\
<p>En romslig, koselig og komfortabel bolig på 90 m² med tre soverom, to bad, stor stue, spisestue og kjøkken, omgitt av et idyllisk landskap.</p>',
 'apartamento');



CREATE TABLE usuarios (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50),
  apellidos VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  contrasena VARCHAR(255),
  telefono VARCHAR(50),
  rol ENUM('Cliente', 'Administrador') DEFAULT 'Cliente'
);


CREATE TABLE reservas (
  id_reserva INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  fecha_inicio DATE NOT NULL,
  fecha_fin DATE NOT NULL,
  n_personas INT,
  nota_adicional TEXT,
  estado_reserva ENUM('Pendiente', 'Confirmada', 'Rechazada', 'Cancelada') DEFAULT 'Pendiente',
  actualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);


CREATE TABLE disponibilidad (
  id_disponibilidad INT AUTO_INCREMENT PRIMARY KEY,
  fecha DATE NOT NULL UNIQUE,
  precio DECIMAL(10,2) NOT NULL,
  estado ENUM('disponible', 'reservada', 'cerrada', 'booking') DEFAULT 'disponible',
  fuente ENUM('local', 'booking') DEFAULT 'local',
  id_reserva INT DEFAULT NULL,
  actualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva)
);


CREATE TABLE comentarios (
  id_comentario INT AUTO_INCREMENT PRIMARY KEY,
  id_reserva INT,
  comentario TEXT,
  calificacion INT CHECK (calificacion >= 1 AND calificacion <= 10),
  fecha_comentario DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva)
);


--Crear los dias del calendario a 12 meses
DELIMITER $$

CREATE PROCEDURE generar_disponibilidad_12_meses()
BEGIN
    DECLARE fecha_actual DATE;
    DECLARE fecha_limite DATE;

    SET fecha_actual = CURDATE();
    SET fecha_limite = DATE_ADD(CURDATE(), INTERVAL 12 MONTH);

    WHILE fecha_actual < fecha_limite DO
        INSERT IGNORE INTO disponibilidad (fecha, precio, estado, fuente)
        VALUES (fecha_actual, 150.00, 'cerrada', 'local');
        
        SET fecha_actual = DATE_ADD(fecha_actual, INTERVAL 1 DAY);
    END WHILE;
END$$

DELIMITER ;

-- Ejecutar el procedimiento:
CALL generar_disponibilidad_12_meses();


--Activar programador de eventos
SET GLOBAL event_scheduler = ON;

--Crear evento que añade fechas automaticamente
DELIMITER $$

CREATE EVENT IF NOT EXISTS extender_calendario
ON SCHEDULE EVERY 1 DAY
DO
BEGIN
  DECLARE nueva_fecha DATE;
  SET nueva_fecha = DATE_ADD(CURDATE(), INTERVAL 12 MONTH);

  INSERT IGNORE INTO disponibilidad (fecha, precio, estado, fuente)
  VALUES (nueva_fecha, 150.00, 'cerrada', 'local');
END$$

DELIMITER ;


