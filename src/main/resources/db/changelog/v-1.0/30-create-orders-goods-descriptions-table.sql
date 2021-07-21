create table orders_goods_description (
                        id serial primary key ,
                        orders_id int,
                        width int not null ,
                        height int not null,
                        color varchar(50) not null,
                        comments varchar(255),
                        CONSTRAINT fk_orders
                            FOREIGN KEY (orders_id)
                                REFERENCES orders(id)
)
GO

