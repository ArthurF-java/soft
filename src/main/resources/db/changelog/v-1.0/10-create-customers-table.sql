create table customers (
                           id serial primary key ,
                           name varchar(50) not null,
                           surname varchar(50),
                           phone varchar(50),
                           city varchar(50),
                           street varchar(50),
                           house int,
                           flat int
)
GO