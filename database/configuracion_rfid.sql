-- ============================================================
-- Sistema RFID de Gestion de Activos
-- Modulo: Categorias de activos - API REST Spring Boot
-- Evidencia: GA7-220501096-AA3-EV01
-- Autor: Cristian Camilo Betancur Alzate
-- ============================================================

-- Crea la base de datos solamente si no existe.
CREATE DATABASE IF NOT EXISTS rfid_gestion_activos
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE rfid_gestion_activos;

-- Crea la tabla utilizada por la entidad CategoriaActivo.
CREATE TABLE IF NOT EXISTS categoriaactivo (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT pk_categoriaactivo
        PRIMARY KEY (id_categoria)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ============================================================
-- Usuario local recomendado para la aplicacion
-- ============================================================
-- Reemplace CONTRASENA_LOCAL por una clave segura antes de
-- ejecutar este bloque. No publique la contrasena utilizada.
--
-- CREATE USER IF NOT EXISTS 'rfid_app'@'localhost'
--     IDENTIFIED BY 'CONTRASENA_LOCAL';
--
-- GRANT SELECT, INSERT, UPDATE, DELETE
--     ON rfid_gestion_activos.*
--     TO 'rfid_app'@'localhost';
--
-- FLUSH PRIVILEGES;

-- ============================================================
-- Datos de ejemplo opcionales
-- ============================================================
-- El bloque permanece comentado para no alterar datos existentes.
--
-- INSERT INTO categoriaactivo (
--     nombre,
--     descripcion,
--     activo
-- ) VALUES (
--     'Equipos de computo',
--     'Computadores de escritorio y portatiles',
--     1
-- );

-- ============================================================
-- Consultas de verificacion
-- ============================================================

SHOW TABLES;

DESCRIBE categoriaactivo;

SELECT
    id_categoria,
    nombre,
    descripcion,
    activo
FROM categoriaactivo
ORDER BY id_categoria;
