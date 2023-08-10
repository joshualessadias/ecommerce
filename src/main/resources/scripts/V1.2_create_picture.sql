create table ecommerce.picture (
    id bigint not null auto_increment,
    name varchar(150) not null,
    product_fk bigint not null,
    image_data longblob not null,
    primary key (id),
    foreign key (product_fk) references product(id)
)