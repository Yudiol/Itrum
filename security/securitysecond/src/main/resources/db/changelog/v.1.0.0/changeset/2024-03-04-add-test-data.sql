
insert into users ( username, email, password, login_attempts)
values ('user', 'user@mail.com', '$2a$12$e9X4rlDFmVWuLb8AUvt.puCgbpGCxn4/J7nXEeLb2AP1ikGs41EKW',0),
       ('moderator', 'moderator@mail.com', '$2a$12$ynY5eGKPRqzBXTDu8bGll.BkbAglegqWN6EloMzB1kbMEb8fKTg9y',0),
       ('admin','admin@mail.com', '$2a$12$.D/uUHfUO0bi7yj2gY1lPenauTeBVecM2T2O8e/gUbXGeTPYFFysq',0);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_MODERATOR'),
       ('ROLE_SUPER_ADMIN');


insert into users_roles (user_id, role_id)
values (1,1),
       (2,2),
       (3,3);