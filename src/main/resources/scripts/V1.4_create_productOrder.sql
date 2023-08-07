create table ecommerce.product_order
(
    id         bigint         not null auto_increment,
    product_fk bigint         not null,
    order_fk   bigint         not null,
    price      numeric(19, 2) not null,
    quantity   int            not null,
    primary key (id),
    foreign key (product_fk) references ecommerce.product (id),
    foreign key (order_fk) references ecommerce.order (id)
)