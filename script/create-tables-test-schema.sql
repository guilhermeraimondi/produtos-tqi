create table product (
    id binary(255) not null,
    name varchar(255) unique not null,
    category varchar(255) not null,
    price decimal(19,2) not null,
    active bit not null,
    primary key (id)
)