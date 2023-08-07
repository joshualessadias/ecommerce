create table ecommerce.order
(
    id    bigint         not null auto_increment,
    price numeric(19, 2) not null,
    primary key (id)
)