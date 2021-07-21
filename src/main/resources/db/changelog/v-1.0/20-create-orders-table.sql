create table orders (
                           id serial primary key ,
                           employee_id int,
                           customer_id int,
                           installation BOOLEAN not null,
                           good varchar(50),
                           amount int,
                           order_time date  ,
                           execution_time date,
                           CONSTRAINT fk_employee
                       FOREIGN KEY (employee_id)
                       REFERENCES employee(id),
                           CONSTRAINT fk_customer
                               FOREIGN KEY (customer_id)
                                   REFERENCES customer(id)

)
GO

