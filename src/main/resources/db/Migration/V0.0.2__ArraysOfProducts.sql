create table PRODUCTS
(
    PRODUCT_ID  int auto_increment not null,
    NAME        VARCHAR(255)       not null,
    QUANTITY    int                null,
    CATEGORY_ID int                not null,
    constraint PRODUCTS_pk
        primary key (PRODUCT_ID),
    constraint PRODUCTS_CATEGORY_null_fk
        foreign key (CATEGORY_ID) references CATEGORY (CATEGORY_ID)
            on update cascade on delete cascade
);