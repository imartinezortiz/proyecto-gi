
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (1, b'0', b'0', 'rico', b'0', 'drv@gmail.com', b'1', 'david', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', '607', 'root');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (2, b'0', b'0', 'ap2', b'0', 'email2@gmail.com', b'1', 'admin1', '$2a$10$n9v06bDngTF/33M9sPdRTeJQjWwTm4pJWqhbGaMYSDumEnXy.T2.G', 'tel2', 'administrador');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (3, b'0', b'0', 'apell3', b'0', 'email3@gmail.com', b'1', 'g1', '$2a$10$tfVFydVKjyHlRWJ/aDZKq.t5Hf/xGA.XwrMS5ZGgF5wFHUDwhPdxG', 'telf3', 'gestor');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (4, b'0', b'0', 'ap1', b'0', 'emai1@gmail.com', b'1', 'inv1', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', 'tel1', 'investigador');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (5, b'0', b'0', 'ap5', b'0', '5@gmail.com', b'1', 'inv5', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', 'tel5', 'investigador5');

INSERT INTO user_roles (usersId, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_roles (usersId, role) VALUES(1, 'ROLE_USER');
INSERT INTO user_roles (usersId, role) VALUES(1, 'ROLE_GESTOR');
INSERT INTO user_roles (usersId, role) VALUES(1, 'ROLE_INVESTIGADOR');

INSERT INTO user_roles (usersId, role) VALUES(2, 'ROLE_ADMIN');
INSERT INTO user_roles (usersId, role) VALUES(2, 'ROLE_USER');
INSERT INTO user_roles (usersId, role) VALUES(2, 'ROLE_GESTOR');
INSERT INTO user_roles (usersId, role) VALUES(2, 'ROLE_INVESTIGADOR');

INSERT INTO user_roles (usersId, role) VALUES(3, 'ROLE_GESTOR');
INSERT INTO user_roles (usersId, role) VALUES(3, 'ROLE_USER');
INSERT INTO user_roles (usersId, role) VALUES(3, 'ROLE_INVESTIGADOR');

INSERT INTO user_roles (usersId, role) VALUES(4, 'ROLE_INVESTIGADOR');
INSERT INTO user_roles (usersId, role) VALUES(4, 'ROLE_USER');

INSERT INTO user_roles (usersId, role) VALUES(5, 'ROLE_INVESTIGADOR');
INSERT INTO user_roles (usersId, role) VALUES(5, 'ROLE_USER');

INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (4, 'C1', 'D1');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (5, 'C5', 'D5');

INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (1, '150', 'FA1903', 'Primera Proyecto', 4);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (2, '200', 'atm1903', 'Segunda Proyecto', 4);

