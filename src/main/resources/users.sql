INSERT INTO Users (usersId, username, password, enabled, accountExpired, accountLocked, credentialsExpired) VALUES (0, 'root', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', true, false, false, false);
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_USER');
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_ADMIN');

INSERT INTO PAGOS (pagoId, numOrden, proyecto, numContabilidad, investigadorPrincipal) VALUES (0, '123','TFG', 123456789, 'IVAN');
INSERT INTO PAGO_GASTOS (pagoId, numFactura, provedor, importe)	VALUES (1, '11', 'P1', '5.0');

