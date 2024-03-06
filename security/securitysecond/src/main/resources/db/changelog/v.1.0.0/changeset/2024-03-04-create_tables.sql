create TABLE IF NOT EXISTS users
(
    user_id               BIGINT    GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username              VARCHAR   NOT NULL,
    email                 VARCHAR   NOT NULL ,
    password              VARCHAR   NOT NULL,
    login_attempts        INT       CHECK( login_attempts >= 0 )
);

create TABLE IF NOT EXISTS roles
(
    role_id         BIGINT    GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name            VARCHAR   NOT NULL
);

create TABLE IF NOT EXISTS users_roles
(
    user_id         BIGINT    REFERENCES users(user_id),
    role_id          BIGINT    REFERENCES roles(role_id),
    PRIMARY KEY ( user_id,role_id )
);