alter table SALESNEW_ORDER add DATE_ datetime2 ^
update SALESNEW_ORDER set DATE_ = convert (date, current_timestamp) where DATE_ is null ;
alter table SALESNEW_ORDER alter column DATE_ datetime2 not null ;
alter table SALESNEW_ORDER add STATUS integer ;
alter table SALESNEW_ORDER drop column DATE ;
alter table SALESNEW_ORDER alter column CUSTOMER_ID uniqueidentifier ;
