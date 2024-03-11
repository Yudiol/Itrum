
insert into customers ( name, email, phone)
values ('Leo', 'Tolstoy@mail.com', '+7(111)-111-11-11'),
       ('Mark','Twain@mail.com', '+1(111)-111-11-11');

insert into orders (customer_id,address,price,status)
values (1,'Moskow',200, 1),
       (1,'Moskow' ,150, 1),
       (2,'Boston' , 300,2);

insert into products (name,description,price,quantity_in_stock)
values ('Phone','Phone description', 100,5),
       ('Notebook','Notebook description',15 , 12),
       ('Laptop','Laptop description',200, 2);

insert into orders_products (order_id, product_id )
values (1,1),
       (1,2),
       (1,3),
       (2,2),
       (2,3);