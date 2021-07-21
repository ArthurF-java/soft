create table orders (
                           id serial primary key ,
                           employees_id int,
                           customers_id int,
                           installation BOOLEAN not null,
                           good varchar(50),
                           amount int,
                           order_time date  ,
                           execution_time date,
                           CONSTRAINT fk_employees
                       FOREIGN KEY (employees_id)
                       REFERENCES employees(id),
                           CONSTRAINT fk_customers
                               FOREIGN KEY (customers_id)
                                   REFERENCES customers(id)

)
GO

