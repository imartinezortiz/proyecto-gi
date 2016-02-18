INSERT INTO Users (usersId, username, password, enabled, accountExpired, accountLocked, credentialsExpired) VALUES (0, 'root', '$2a$10$//HU8yTgOoLrZX6z9cgXhuR/IJki.BtS/ZM4Pr8s/zBDCFIT.I3P6', true, false, false, false);
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_USER');
INSERT INTO USER_ROLES (usersId, role) VALUES(1, 'ROLE_ADMIN');

