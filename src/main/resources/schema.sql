CREATE TABLE IF NOT EXISTS encargados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS partes_diarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    texto_dictado VARCHAR(500) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    encargado_id BIGINT NOT NULL,
    CONSTRAINT fk_partes_diarios_encargados
        FOREIGN KEY (encargado_id)
        REFERENCES encargados(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);
