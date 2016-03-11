INSERT INTO Users (username, password, enabled, accountExpired, accountLocked, credentialsExpired, nombre, apellidos, email, telefono) VALUES ('root', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', true, false, false, false, 'david','rico', 'drv@gmail.com', '607' );
INSERT INTO Users (username, password, enabled, accountExpired, accountLocked, credentialsExpired, nombre, apellidos, email, telefono) VALUES ('test', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', true, false, false, false,  'juan','perez', 'juan@gmail.com', '123');
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_USER');
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_ADMIN');

--INSERT INTO investigadores (investigadorId, centro, departamento, apellidos, email, nombre, telefono) VALUES (1, 'madrid', NULL, 'nieto', NULL, 'david', NULL);
--INSERT INTO investigadores (investigadorId, centro, departamento, apellidos, email, nombre, telefono) VALUES (2, 'madrid', NULL, 'martinez', NULL, 'ivan', NULL);

INSERT INTO investigadores (investigadorId, centro, departamento)  VALUES  (1, 'C1', 'D1');
INSERT INTO investigadores (investigadorId, centro, departamento)  VALUES  (2, 'C2', 'D2');

INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES ('1', '150', 'FA1903', 'Primera Proyecto', '1');
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES ('2', '200', 'atm1903', 'Segunda Proyecto', '1');