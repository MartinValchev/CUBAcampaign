alter table SALESNEW_ODER_LINE add constraint FK_SALESNEW_ODER_LINE_ORDER foreign key (ORDER_ID) references SALESNEW_ORDER(ID);
alter table SALESNEW_ODER_LINE add constraint FK_SALESNEW_ODER_LINE_PRODUCT foreign key (PRODUCT_ID) references SALESNEW_PRODUCT(ID);
create index IDX_SALESNEW_ODER_LINE_ORDER on SALESNEW_ODER_LINE (ORDER_ID);
create index IDX_SALESNEW_ODER_LINE_PRODUCT on SALESNEW_ODER_LINE (PRODUCT_ID);
