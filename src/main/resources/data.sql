
-- DELETE FROM users WHERE username IN ('user', 'admin');


-- Inserting users data
TRUNCATE TABLE users,authorities,advert;
-- usuwac zawartosc tabel wazne!!!!

INSERT INTO users (username, enabled, password) VALUES
('user', true, '{noop}test123'),
('admin', true, '{noop}test123');


INSERT INTO authorities (authority, username) VALUES
('ROLE_EMPLOYEE', 'user'),
('ROLE_EMPLOYEE', 'admin'),
('ROLE_ADMIN', 'admin');


INSERT INTO advert (title,information,accepted,username) VALUES
('TestowyTytulUser','ZawartoscUser',true,'user')