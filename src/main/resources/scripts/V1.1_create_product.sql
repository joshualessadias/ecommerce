create table ecommerce.Product (
    id bigint not null auto_increment,
    name varchar(150) not null,
    price numeric(19,2) not null,
    stock int not null,
    primary key (id)
)