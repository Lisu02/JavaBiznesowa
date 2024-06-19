CREATE TABLE IF NOT EXISTS users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(50) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       PRIMARY KEY (username)
);


CREATE TABLE IF NOT EXISTS authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             UNIQUE (username, authority),
                             CONSTRAINT authorities_fk_1 FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE IF NOT EXISTS advert   (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255),
                        information TEXT,
                        creation_date DATE,
                        expiration_date DATE,
                        accepted BOOLEAN,
                        username VARCHAR(255),
                        CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES users(username)
);