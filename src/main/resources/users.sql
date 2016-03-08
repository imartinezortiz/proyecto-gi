INSERT INTO Users (usersId, username, password, enabled, accountExpired, accountLocked, credentialsExpired) VALUES (0, 'root', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', true, false, false, false);
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_USER');
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_ADMIN');


INSERT INTO investigadores (investigadorId, centro, departamento, apellidos, email, nombre, password, role, telefono) VALUES ('1', 'madrid', NULL, 'nieto', NULL, 'david', NULL, NULL, NULL);
INSERT INTO investigadores (investigadorId, centro, departamento, apellidos, email, nombre, password, role, telefono) VALUES ('2', 'segovia', NULL, 'jefe', NULL, 'ivan', NULL, NULL, NULL);
INSERT INTO investigadores (investigadorId, centro, departamento, apellidos, email, nombre, password, role, telefono) VALUES ('3', 'madrid', NULL, 'becario', NULL, 'rico', NULL, NULL, NULL);


INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES ('1', '150', 'FA1903', 'Primera Proyecto', '1');
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES ('2', '200', 'atm1903', 'Segunda Proyecto', '1');
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES ('3', '250', 'cod1903', 'Tercer Proyecto', '2');
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES ('4', '300', 'ft9', 'Primera Proyecto', '3');