CREATE TABLE reserva(
    id INT NOT NULL AUTO_INCREMENT,
    fecha_entrada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    valor BIGINT(10) NOT NULL,
    forma_pago VARCHAR(35) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE huesped(
    id INT NOT NULL AUTO_INCREMENT,
    idReserva INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_huespedXreserva FOREIGN KEY (idReserva) REFERENCES reserva(id)
);