drop index IDX_SALESNEW_ORDER_CUSTOMER on SALESNEW_ORDER ;
alter table SALESNEW_ORDER drop constraint FK_SALES_ORDER_CUSTOMER ;
alter table SALESNEW_ORDER drop column CUSTOMER_ID ;
alter table SALESNEW_ORDER add CUSTOMER_ID bigint ;
