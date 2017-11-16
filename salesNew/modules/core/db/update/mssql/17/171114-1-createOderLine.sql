create table SALESNEW_ODER_LINE (
    ID uniqueidentifier,
    --
    ORDER_ID uniqueidentifier,
    PRODUCT_ID uniqueidentifier,
    QUANTITY decimal(19, 3),
    --
    primary key nonclustered (ID)
);
