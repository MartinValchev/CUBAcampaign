drop index IDX_SALESNEW_ODER_LINE_ORDER on SALESNEW_ODER_LINE ;
alter table SALESNEW_ODER_LINE drop constraint FK_SALESNEW_ODER_LINE_ORDER ;
alter table SALESNEW_ODER_LINE drop column ORDER_ID ;
alter table SALESNEW_ODER_LINE add ORDER_ID bigint ;
drop index IDX_SALESNEW_ODER_LINE_PRODUCT on SALESNEW_ODER_LINE ;
alter table SALESNEW_ODER_LINE drop constraint FK_SALESNEW_ODER_LINE_PRODUCT ;
alter table SALESNEW_ODER_LINE drop column PRODUCT_ID ;
alter table SALESNEW_ODER_LINE add PRODUCT_ID bigint ;
