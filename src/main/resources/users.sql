INSERT INTO Users (usersId, username, password, enabled, accountExpired, accountLocked, credentialsExpired) VALUES (0, 'root', 'root', true, false, false, false);

INSERT INTO USER_ROLES (usersId, role) VALUES(0, 'ROLE_USER');
INSERT INTO USER_ROLES (usersId, role) VALUES(0, 'ROLE_ADMIN');
