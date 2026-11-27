-- =====================================================
-- CORPALIV - Datos de ejemplo (ejecutar después de bd.sql)
-- El funcionario inicial (admin@corpaliv.cl) lo crea auth al arrancar.
-- =====================================================

USE corpaliv_noticias;

INSERT INTO noticia (titulo, resumen, url_imagen, fecha) VALUES
('Proyecto Impulso Laboral Alba Lab',
 'Formación laboral y acompañamiento para personas con discapacidad múltiple.',
 NULL, '2026-10-15'),
('Escuela Jan Van Dijk amplía matrícula',
 'Acreditación ministerial permite aumentar la cobertura educativa.',
 NULL, '2026-10-10');

USE corpaliv_ventas;

INSERT INTO producto (nombre, descripcion, precio, url_imagen) VALUES
('Agenda Institucional 2026',
 'Agenda encuadernada con ilustraciones creadas por los estudiantes.',
 12500, 'https://picsum.photos/seed/corpaliv-agenda/600/400'),
('Tazón Solidario CORPALIV',
 'Tazón cerámico de 350 ml con diseño exclusivo.',
 6500, 'https://picsum.photos/seed/corpaliv-tazon/600/400'),
('Bolsa Ecológica de Tela',
 'Bolsa de algodón orgánico reutilizable.',
 4500, 'https://picsum.photos/seed/corpaliv-bolsa/600/400');
