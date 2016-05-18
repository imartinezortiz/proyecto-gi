SET character_set_client = utf8;

INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (1, b'0', b'0', 'Rico Valverde', b'0', 'drv@gmail.com', b'1', 'David', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', '607606620', 'root');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (2, b'0', b'0', 'Nieto Cobo', b'0', 'email2@gmail.com', b'1', 'David', '$2a$10$n9v06bDngTF/33M9sPdRTeJQjWwTm4pJWqhbGaMYSDumEnXy.T2.G', '612357854', 'administrador');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (3, b'0', b'0', 'Sánchez Torquemada', b'0', 'email3@gmail.com', b'1', 'Julio', '$2a$10$tfVFydVKjyHlRWJ/aDZKq.t5Hf/xGA.XwrMS5ZGgF5wFHUDwhPdxG', '683123654', 'gestor');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (4, b'0', b'0', 'Roca Martínez', b'0', 'emai1@gmail.com', b'1', 'Miguel', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', '612509750', 'invDavid');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (5, b'0', b'0', 'Ramos Atienza', b'0', '5@gmail.com', b'1', 'Pablo', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', '683511224', 'invIvan');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (6, b'0', b'0', 'Gonzalez Prieto', b'0', '6@gmail.com', b'1', 'Ivan', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', '654199009', 'invNieto');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (7, b'0', b'0', 'Garía Romero', b'0', '7@gmail.com', b'1', 'Fernando', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', '688332156', 'invRico');
INSERT INTO users (usersId,accountExpired, accountLocked, apellidos, credentialsExpired,email,enabled, nombre, password, telefono, username) VALUES (8, b'0', b'0', 'Rebollo Fernández', b'0', '8@gmail.com', b'1', 'Marcos', '$2a$10$aY8xNKxmWpuU7Iu632pYpeuYJYOSYYzOvGBEkAuqAqp1XtA0afuJW', '650099888', 'invMarcos');


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

INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (4, 'Informática', 'Sistemas Informáticos y Computación');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (5, 'Matemáticas', 'Análisis Matemático');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (6, 'Físicas', 'Óptica');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (7, 'Informática', 'Inteligencia Artificial');
INSERT INTO investigadores (investigadorId,centro, departamento) VALUES (8, 'Estadística', 'Economía Financiera y Contabilidad');


INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (1, '150', 'CM1101', 'Primer Proyecto', 4);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (2, '200', 'D43', 'Segundo Proyecto', 5);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (3, '250', 'MAT2010', 'Tercer Proyecto', 6);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (4, '300', 'MAT2009', 'Cuarto Proyecto', 7);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (5, '350', 'GR35', 'Quinto Proyecto', 8);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (6, '400', 'GR58', 'Sexto Proyecto', 4);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (7, '450', 'IMCs', 'Septimo Proyecto', 5);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (8, '500', 'Avanade', 'Octavo Proyecto', 6);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (9, '550', 'GTI', 'Noveno Proyecto', 7);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (10, '600', 'Repsol', 'Decimo Proyecto', 8);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (11, '650', 'Campsa', 'Onceavo Proyecto', 4);
INSERT INTO proyectos (id, numContabilidad, referencia, titulo, investigadorID) VALUES (12, '700', 'Petronor', 'Doceavo Proyecto', 5);

INSERT INTO proyectos_investigadores (proyectos_id, investigadores_investigadorId) VALUES (1,4);
INSERT INTO proyectos_investigadores (proyectos_id, investigadores_investigadorId) VALUES (1,5);
INSERT INTO proyectos_investigadores (proyectos_id, investigadores_investigadorId) VALUES (2,6);
INSERT INTO proyectos_investigadores (proyectos_id, investigadores_investigadorId) VALUES (2,7);

INSERT INTO pagos(pagoId, numOrden,fecha,iban,bic,pagador,memoria,relacion, fase, proyecto_id) VALUES (1,111,'2016-04-21','100','150','David García López','Mem1','REL-1','PROCESANDO',1);
INSERT INTO pagos(pagoId, numOrden,fecha,iban,bic,pagador,memoria,relacion, fase, proyecto_id) VALUES (2,222,'2016-05-25','200','250','Iván Velasco Mora','Mem2','REL-2', 'EDICION',1);

INSERT INTO pago_gastos (pagoId, importe, numFactura, proveedor) VALUES (1, '100.00', '111', 'p1');
INSERT INTO pago_gastos (pagoId, importe, numFactura, proveedor) VALUES (2, '200.00', '222', 'p1');

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


--Insertamos inventarios de prueba para el proyecto 1 (Primer Proyecto);
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (1, 'autor1', 'Matemáticas', 'des1', '2016-02-21', 'obs1', 'EDICION',1);
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (2, 'autor2', 'Físicas', 'des2', '2016-03-12', 'obs2', 'EDICION',1);
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (3, 'autor1', 'Matemáticas', 'des1', '2016-04-23', 'obs3', 'EDICION',1);
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (4, 'autor1', 'Informática', 'des1', '2016-06-24', 'obs4', 'PROCESANDO',1);
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (5, 'autor1', 'Matemáticas', 'des1', '2016-07-25', 'obs5', 'PROCESANDO',1);
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (6, 'autor1', 'Informática', 'des1', '2016-08-26', 'obs6', 'PROCESANDO',1);
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (7, 'autor1', 'Informática', 'des1', '2016-09-27', 'obs7', 'PROCESANDO',1);
-- Inventario de proyecto 2
INSERT INTO inventarios (inventarioId, autorizacion, centro, descripcion, fecha, observaciones, fase, proyecto_id) VALUES (8, 'autor1', 'Informática', 'des1', '2016-04-21', 'obs8','EDICION', 2);


--Insertamos viajes de prueba para el proyecto 1 (Primer Proyecto);
INSERT INTO `viajes` (`viajeId`, `dietaID`, `fecha`, `fechaFin`, `fechaInicio`, `importeDietaTotal`, `investigador`, `invitado`, `itinerario`, `miembroProyecto`, `numDietas`, `numOrden`, `objetoDesplazamiento`, `observaciones`, `pagarA`, fase, `proyecto_id`) VALUES ('1', '1', '2016-05-18', '2016-05-22', '2016-05-20', '112.20', '4', '', 'a', b'1', '3', 'a', 'a', 'a', 'a',  'EDICION','1')
INSERT INTO `viajes` (`viajeId`, `dietaID`, `fecha`, `fechaFin`, `fechaInicio`, `importeDietaTotal`, `investigador`, `invitado`, `itinerario`, `miembroProyecto`, `numDietas`, `numOrden`, `objetoDesplazamiento`, `observaciones`, `pagarA`, fase, `proyecto_id`) VALUES ('2', '1', '2016-03-20', '2016-06-22', '2016-06-24', '112.20', '4', '', 'a', b'1', '3', 'a', 'a', 'a', 'a',  'PROCESANDO','1')