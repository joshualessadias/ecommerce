create table ecommerce.Picture (
    id bigint not null auto_increment,
    name varchar(150) not null,
    product_fk bigint not null,
    primary key (id),
    foreign key (product_fk) references Product(id)
)