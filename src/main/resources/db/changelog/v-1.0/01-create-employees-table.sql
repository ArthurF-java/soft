create table employees (
                       id serial primary key ,
                       name varchar(50) not null,
                       surname varchar(50),
                       phone varchar(50),
                       role varchar(50),
                       authority int
)
GO

-- alter table users
--     add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)
--     GO