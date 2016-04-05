SET character_set_client = utf8;

INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (1, b'0', b'0', 'rico', b'0', 'drv@gmail.com', b'1', 'david', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', '607', 'root');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (2, b'0', b'0', 'ap2', b'0', 'email2@gmail.com', b'1', 'admin1', '$2a$10$n9v06bDngTF/33M9sPdRTeJQjWwTm4pJWqhbGaMYSDumEnXy.T2.G', 'tel2', 'administrador');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (3, b'0', b'0', 'apell3', b'0', 'email3@gmail.com', b'1', 'g1', '$2a$10$tfVFydVKjyHlRWJ/aDZKq.t5Hf/xGA.XwrMS5ZGgF5wFHUDwhPdxG', 'telf3', 'gestor');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (4, b'0', b'0', 'ap1', b'0', 'emai1@gmail.com', b'1', 'David', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', 'tel1', 'invDavid');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (5, b'0', b'0', 'ap5', b'0', '5@gmail.com', b'1', 'Ivan', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', 'tel5', 'invIvan');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (6, b'0', b'0', 'ap6', b'0', '6@gmail.com', b'1', 'Nieto', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', 'tel6', 'invNieto');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (7, b'0', b'0', 'ap7', b'0', '7@gmail.com', b'1', 'Rico', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', 'tel7', 'invRico');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (8, b'0', b'0', 'ap8', b'0', '8@gmail.com', b'1', 'Marcos', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', 'tel8', 'invMarcos');


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

INSERT INTO user_roles (usersId, role) VALUES(6, 'ROLE_INVESTIGADOR');
INSERT INTO user_roles (usersId, role) VALUES(6, 'ROLE_USER');

INSERT INTO user_roles (usersId, role) VALUES(7, 'ROLE_INVESTIGADOR');
INSERT INTO user_roles (usersId, role) VALUES(7, 'ROLE_USER');

INSERT INTO user_roles (usersId, role) VALUES(8, 'ROLE_INVESTIGADOR');
INSERT INTO user_roles (usersId, role) VALUES(8, 'ROLE_USER');

INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (4, 'C1', 'D1');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (5, 'C5', 'D5');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (6, 'C6', 'D6');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (7, 'C7', 'D7');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (8, 'C8', 'D8');


INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (1, '150', 'FA1903', 'Primera Proyecto', 4);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (2, '200', 'atm1903', 'Segunda Proyecto', 4);

INSERT INTO pagos(pagoId, numOrden,fecha,iban,bic,pagador,memoria,relacion, proyecto_id) VALUES (1,111,'05-04-2016','100','150','David','Memoriaaa','La relacion misma',1);

INSERT INTO pago_gastos (pagoId, importe, numFactura, proveedor) VALUES (1, '100.00', '222', 'p1');

INSERT INTO dietas (pais,importe) VALUES ('España','37.40');
INSERT INTO dietas (pais,importe) VALUES ('Alemania','59.50');
INSERT INTO dietas (pais,importe) VALUES ('Andorra','37.86');
INSERT INTO dietas (pais,importe) VALUES ('Angola','59.50');
INSERT INTO dietas (pais,importe) VALUES ('Arabia Saudita','54.09');
INSERT INTO dietas (pais,importe) VALUES ('Argelia','44.47');
INSERT INTO dietas (pais,importe) VALUES ('Argentina','55.29');
INSERT INTO dietas (pais,importe) VALUES ('Australia','51.09');
INSERT INTO dietas (pais,importe) VALUES ('Austria','58.90');
INSERT INTO dietas (pais,importe) VALUES ('Bélgica','82.94');
INSERT INTO dietas (pais,importe) VALUES ('Bolivia','36.66');
INSERT INTO dietas (pais,importe) VALUES ('Bosnia-Herzegovina','49.88');
INSERT INTO dietas (pais,importe) VALUES ('Brasil','79.33');
INSERT INTO dietas (pais,importe) VALUES ('Bulgaria','37.86');
INSERT INTO dietas (pais,importe) VALUES ('Camerún','48.68');
INSERT INTO dietas (pais,importe) VALUES ('Canadá','51.69');
INSERT INTO dietas (pais,importe) VALUES ('Chile','50.49');
INSERT INTO dietas (pais,importe) VALUES ('China','46.28');
INSERT INTO dietas (pais,importe) VALUES ('Colombia','78.13');
INSERT INTO dietas (pais,importe) VALUES ('Corea','55.29');
INSERT INTO dietas (pais,importe) VALUES ('Costa de Marfil','49.28');
INSERT INTO dietas (pais,importe) VALUES ('Costa Rica','44.47');
INSERT INTO dietas (pais,importe) VALUES ('Croacia','49.88');
INSERT INTO dietas (pais,importe) VALUES ('Cuba','33.06');
INSERT INTO dietas (pais,importe) VALUES ('Dinamarca','64.91');
INSERT INTO dietas (pais,importe) VALUES ('R.Dominicana','36.66');
INSERT INTO dietas (pais,importe) VALUES ('Ecuador','43.27');
INSERT INTO dietas (pais,importe) VALUES ('Egipto','39.07');
INSERT INTO dietas (pais,importe) VALUES ('El Salvador','43.27');
INSERT INTO dietas (pais,importe) VALUES ('Emiratos Árabes Unidos','56.50');
INSERT INTO dietas (pais,importe) VALUES ('Eslovaquia','43.27');
INSERT INTO dietas (pais,importe) VALUES ('Estados Unidos','69.72');
INSERT INTO dietas (pais,importe) VALUES ('Etiopía','37.86');
INSERT INTO dietas (pais,importe) VALUES ('Filipinas','39.67');
INSERT INTO dietas (pais,importe) VALUES ('Finlandia','65.51');
INSERT INTO dietas (pais,importe) VALUES ('Francia','65.51');
INSERT INTO dietas (pais,importe) VALUES ('Gabón','52.89');
INSERT INTO dietas (pais,importe) VALUES ('Ghana','37.26');
INSERT INTO dietas (pais,importe) VALUES ('Grecia','39.07');
INSERT INTO dietas (pais,importe) VALUES ('Guatemala','42.67');
INSERT INTO dietas (pais,importe) VALUES ('Guinea Ecuatorial','50.49');
INSERT INTO dietas (pais,importe) VALUES ('Haití','37.86');
INSERT INTO dietas (pais,importe) VALUES ('Honduras','42.07');
INSERT INTO dietas (pais,importe) VALUES ('Hong Kong','51.69');
INSERT INTO dietas (pais,importe) VALUES ('Hungría','46.28');
INSERT INTO dietas (pais,importe) VALUES ('India','38.46');
INSERT INTO dietas (pais,importe) VALUES ('Indonesia','42.67');
INSERT INTO dietas (pais,importe) VALUES ('Irak','39.07');
INSERT INTO dietas (pais,importe) VALUES ('Irán','44.47');
INSERT INTO dietas (pais,importe) VALUES ('Irlanda','48.08');
INSERT INTO dietas (pais,importe) VALUES ('Israel','56.50');
INSERT INTO dietas (pais,importe) VALUES ('Italia','63.11');
INSERT INTO dietas (pais,importe) VALUES ('Jamaica','46.28');
INSERT INTO dietas (pais,importe) VALUES ('Japón','96.76');
INSERT INTO dietas (pais,importe) VALUES ('Jordania','42.67');
INSERT INTO dietas (pais,importe) VALUES ('Kenia','39.67');
INSERT INTO dietas (pais,importe) VALUES ('Kuwait','44.47');
INSERT INTO dietas (pais,importe) VALUES ('Líbano','34.86');
INSERT INTO dietas (pais,importe) VALUES ('Libia','54.69');
INSERT INTO dietas (pais,importe) VALUES ('Luxemburgo','55.89');
INSERT INTO dietas (pais,importe) VALUES ('Malasia','34.26');
INSERT INTO dietas (pais,importe) VALUES ('Malta','31.85');
INSERT INTO dietas (pais,importe) VALUES ('Marruecos','39.67');
INSERT INTO dietas (pais,importe) VALUES ('Mauritania','39.07');
INSERT INTO dietas (pais,importe) VALUES ('México','43.27');
INSERT INTO dietas (pais,importe) VALUES ('Mozambique','42.67');
INSERT INTO dietas (pais,importe) VALUES ('Nicaragua','52.89');
INSERT INTO dietas (pais,importe) VALUES ('Nigeria','46.88');
INSERT INTO dietas (pais,importe) VALUES ('Noruega','80.54');
INSERT INTO dietas (pais,importe) VALUES ('Nueva Zelanda','40.27');
INSERT INTO dietas (pais,importe) VALUES ('Paises Bajos','64.31');
INSERT INTO dietas (pais,importe) VALUES ('Pakistán','37.26');
INSERT INTO dietas (pais,importe) VALUES ('Panamá','36.66');
INSERT INTO dietas (pais,importe) VALUES ('Paraguay','33.06');
INSERT INTO dietas (pais,importe) VALUES ('Perú','43.27');
INSERT INTO dietas (pais,importe) VALUES ('Polonia','42.67');
INSERT INTO dietas (pais,importe) VALUES ('Portugal','43.87');
INSERT INTO dietas (pais,importe) VALUES ('Reino Unido','82.94');
INSERT INTO dietas (pais,importe) VALUES ('República Checa','43.27');
INSERT INTO dietas (pais,importe) VALUES ('Rumania','38.46');
INSERT INTO dietas (pais,importe) VALUES ('Rusia','73.32');
INSERT INTO dietas (pais,importe) VALUES ('Senegal','45.08');
INSERT INTO dietas (pais,importe) VALUES ('Singapur','48.08');
INSERT INTO dietas (pais,importe) VALUES ('Siria','46.28');
INSERT INTO dietas (pais,importe) VALUES ('Sudáfrica','48.08');
INSERT INTO dietas (pais,importe) VALUES ('Suecia','75.13');
INSERT INTO dietas (pais,importe) VALUES ('Suiza','61.30');
INSERT INTO dietas (pais,importe) VALUES ('Tailandia','39.07');
INSERT INTO dietas (pais,importe) VALUES ('Taiwan','48.68');
INSERT INTO dietas (pais,importe) VALUES ('Tanzania','30.05');
INSERT INTO dietas (pais,importe) VALUES ('Túnez','48.28');
INSERT INTO dietas (pais,importe) VALUES ('Turquía','39.07');
INSERT INTO dietas (pais,importe) VALUES ('Uruguay','41.47');
INSERT INTO dietas (pais,importe) VALUES ('Venezuela','36.06');
INSERT INTO dietas (pais,importe) VALUES ('Yemen','43.27');
INSERT INTO dietas (pais,importe) VALUES ('Yugoslavia','49.88');
INSERT INTO dietas (pais,importe) VALUES ('Zaire/Congo','54.09');
INSERT INTO dietas (pais,importe) VALUES ('Zimbawe','39.07');
INSERT INTO dietas (pais,importe) VALUES ('Resto del mundo','40.87');
