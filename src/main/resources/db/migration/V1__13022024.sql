CREATE SEQUENCE users_id_seq START 1;

CREATE TABLE users
(

    id       BIGINT PRIMARY KEY DEFAULT NEXTVAL('users_id_seq'),
    email                VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    date_of_registration DATE        not null DEFAULT CURRENT_DATE,
    first_name           VARCHAR(50) NOT NULL

);

CREATE TYPE ROLE AS ENUM ('USER', 'ADMIN');

CREATE SEQUENCE users_roles_id_seq START 1;

CREATE TABLE user_roles
(

    id        BIGINT PRIMARY KEY DEFAULT NEXTVAL('users_roles_id_seq'),
    user_id   BIGINT REFERENCES users (id),
    user_role ROLE NOT NULL      DEFAULT 'USER'

);

CREATE SEQUENCE tasks_id_seq START 1;

CREATE TABLE tasks
(

    id           BIGINT PRIMARY KEY DEFAULT NEXTVAL('tasks_id_seq'),
    header         VARCHAR(50) NOT NULL,
    description    VARCHAR(4096),
    is_completed BOOLEAN            DEFAULT FALSE,
    date_completed DATE,
    user_id        BIGINT REFERENCES users (id)

)