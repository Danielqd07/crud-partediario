INSERT IGNORE INTO encargados (id, nombre, email, telefono) VALUES
(1, 'Daniel García', 'daniel.garcia@email.com', '600111222'),
(2, 'Antonio López', 'antonio.lopez@email.com', '600333444'),
(3, 'María Rodríguez', 'maria.rodriguez@email.com', '600555666');

INSERT IGNORE INTO partes_diarios (id, fecha, texto_dictado, estado, encargado_id) VALUES
(1, '2026-11-15', 'Revisión general de la obra y comprobación del material.', 'PENDIENTE', 1),
(2, '2026-11-16', 'Seguimiento de los trabajos realizados durante la jornada.', 'REVISADO', 1),
(3, '2026-11-17', 'Control del avance de la obra y revisión de incidencias.', 'FINALIZADO', 2),
(4, '2026-11-17', 'Comprobación de tareas pendientes y organización del personal.', 'PENDIENTE', 3);
