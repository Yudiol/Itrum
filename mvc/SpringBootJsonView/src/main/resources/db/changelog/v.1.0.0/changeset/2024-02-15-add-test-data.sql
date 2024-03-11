
insert into users (name,email)
values ('Bill', 'Bill@mail.com'),
       ('Sam' , 'Sam@mail.com');

insert into orders (foreign_user_id, name, total_amount, status)
values ( 1,'phone' , 20, 'active' ),
       (1,'car', 30, 'active');
