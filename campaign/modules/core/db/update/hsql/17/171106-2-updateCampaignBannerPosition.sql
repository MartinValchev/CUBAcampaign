alter table CAMPAIGN_CAMPAIGN_BANNER_POSITION add column START_DATE timestamp ^
update CAMPAIGN_CAMPAIGN_BANNER_POSITION set START_DATE = current_timestamp where START_DATE is null ;
alter table CAMPAIGN_CAMPAIGN_BANNER_POSITION alter column START_DATE set not null ;
alter table CAMPAIGN_CAMPAIGN_BANNER_POSITION add column END_DATE timestamp ^
update CAMPAIGN_CAMPAIGN_BANNER_POSITION set END_DATE = current_timestamp where END_DATE is null ;
alter table CAMPAIGN_CAMPAIGN_BANNER_POSITION alter column END_DATE set not null ;
