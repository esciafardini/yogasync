CREATE EXTENSION "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    teacher BOOLEAN NOT NULL,
    admin BOOLEAN NOT NULL
);

INSERT INTO users (username, password, teacher, admin)
VALUES ('toddcage', 'password', FALSE, TRUE);
