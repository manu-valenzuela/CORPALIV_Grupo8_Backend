-- =====================================================
-- CORPALIV - Creación de bases de datos y tablas
-- Una base de datos por microservicio
-- =====================================================

-- ---------- auth (puerto 8081) ----------
CREATE DATABASE IF NOT EXISTS corpaliv_auth;
USE corpaliv_auth;

CREATE TABLE IF NOT EXISTS cuenta (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    correo      VARCHAR(100) NOT NULL,
    contrasena  VARCHAR(100) NOT NULL,   -- hash BCrypt
    rol         VARCHAR(20)  NOT NULL,   -- SOCIO | FUNCIONARIO
    PRIMARY KEY (id),
    UNIQUE KEY uk_cuenta_correo (correo)
);

-- ---------- usuarios (puerto 8082) ----------
CREATE DATABASE IF NOT EXISTS corpaliv_usuarios;
USE corpaliv_usuarios;

CREATE TABLE IF NOT EXISTS usuario (
    id                      BIGINT       NOT NULL AUTO_INCREMENT,
    nombre                  VARCHAR(50)  NOT NULL,
    apellido                VARCHAR(50)  NOT NULL,
    rut                     VARCHAR(12)  NOT NULL,
    fecha_nacimiento        DATE         NOT NULL,
    direccion               VARCHAR(150) NOT NULL,
    latitud                 DOUBLE       NULL,
    longitud                DOUBLE       NULL,
    monto_donacion_mensual  INT          NOT NULL,
    correo                  VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_usuario_rut (rut),
    UNIQUE KEY uk_usuario_correo (correo)
);

-- ---------- donaciones (puerto 8083) ----------
CREATE DATABASE IF NOT EXISTS corpaliv_donaciones;
USE corpaliv_donaciones;

CREATE TABLE IF NOT EXISTS donacion (
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    nombre  VARCHAR(100) NOT NULL,
    correo  VARCHAR(100) NOT NULL,
    monto   INT          NOT NULL,
    fecha   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- ---------- noticias (puerto 8084) ----------
CREATE DATABASE IF NOT EXISTS corpaliv_noticias;
USE corpaliv_noticias;

CREATE TABLE IF NOT EXISTS noticia (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    titulo      VARCHAR(150) NOT NULL,
    resumen     VARCHAR(500) NOT NULL,
    url_imagen  VARCHAR(255) NULL,
    fecha       DATE         NOT NULL,
    PRIMARY KEY (id)
);

-- ---------- ventas (puerto 8085) ----------
CREATE DATABASE IF NOT EXISTS corpaliv_ventas;
USE corpaliv_ventas;

CREATE TABLE IF NOT EXISTS producto (
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    nombre       VARCHAR(100) NOT NULL,
    descripcion  VARCHAR(255) NOT NULL,
    precio       INT          NOT NULL,
    url_imagen   VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

-- La venta guarda nombre y precio del producto al momento de la compra,
-- así el historial no cambia si el producto se edita o se elimina.
CREATE TABLE IF NOT EXISTS venta (
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    producto_id      BIGINT       NOT NULL,
    nombre_producto  VARCHAR(100) NOT NULL,
    cantidad         INT          NOT NULL,
    precio_unitario  INT          NOT NULL,
    correo_comprador VARCHAR(100) NOT NULL,
    fecha            DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
