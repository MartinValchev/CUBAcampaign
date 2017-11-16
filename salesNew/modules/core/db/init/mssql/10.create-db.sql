-- begin SALESNEW_CUSTOMER
create table SALESNEW_CUSTOMER (
    ID uniqueidentifier,
    --
    NAME varchar(100) not null,
    EMAIL varchar(50) not null,
    --
    primary key nonclustered (ID)
)^
-- end SALESNEW_CUSTOMER
-- begin SALESNEW_ORDER
create table SALESNEW_ORDER (
    ID bigint,
    --
    DATE_ datetime2 not null,
    AMOUNT decimal(19, 2) not null,
    CUSTOMER_ID bigint,
    STATUS integer,
    --
    primary key (ID)
)^
-- end SALESNEW_ORDER
-- begin SALESNEW_PRODUCT
create table SALESNEW_PRODUCT (
    ID uniqueidentifier,
    --
    NAME varchar(255) not null,
    PRICE decimal(19, 3) not null,
    --
    primary key nonclustered (ID)
)^
-- end SALESNEW_PRODUCT
-- begin SALESNEW_ODER_LINE
create table SALESNEW_ODER_LINE (
    ID bigint,
    --
    ORDER_ID bigint,
    PRODUCT_ID bigint,
    QUANTITY decimal(19, 3),
    --
    primary key (ID)
)^
-- end SALESNEW_ODER_LINE
