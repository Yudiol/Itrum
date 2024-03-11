
insert into customers ( name, email, phone)
values ('Leo', 'Tolstoy@mail.com', '+7(111)-111-11-11'),
       ('Mark','Twain@mail.com', '+1(111)-111-11-11');

insert into orders (customer_id,address,price,status)
values (1,'Moskow',200, 1),
       (1,'Moskow' ,150, 1),
       (2,'Boston' , 300,2);

insert into products (name,description,price,quantity_in_stock)
values ('Phone','Phone description', 100,50),
       ('Notebook','Notebook description',15 , 120),
       ('Laptop','Laptop description',200, 20);

insert into orders_products (order_id, product_id,quantity)
values (1,1,2),
       (1,2,3),
       (1,3,2),
       (2,2,2),
       (2,3,3);