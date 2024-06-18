
-- DELETE FROM users WHERE username IN ('user', 'admin');


INSERT INTO users (username, password, enabled)
VALUES
    ('user', '{noop}test123', 1),
    ('admin', '{noop}test123', 1)
ON CONFLICT DO NOTHING;



INSERT INTO authorities (username, authority)
VALUES
    ('user', 'ROLE_EMPLOYEE'),
    ('admin', 'ROLE_EMPLOYEE'),
    ('admin', 'ROLE_ADMIN')
ON CONFLICT DO NOTHING;

