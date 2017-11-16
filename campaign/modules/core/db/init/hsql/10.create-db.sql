-- begin CAMPAIGN_CAMPAIGN
create table CAMPAIGN_CAMPAIGN (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    START_DATE timestamp not null,
    END_DATE timestamp not null,
    --
    primary key (ID)
)^
-- end CAMPAIGN_CAMPAIGN
-- begin CAMPAIGN_BANNER_POSITION
create table CAMPAIGN_BANNER_POSITION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    IMPRESSIONS_LIMIT bigint not null,
    WEBSITE_ID varchar(36),
    --
    primary key (ID)
)^
-- end CAMPAIGN_BANNER_POSITION
-- begin CAMPAIGN_CAMPAIGN_BANNER_POSITION
create table CAMPAIGN_CAMPAIGN_BANNER_POSITION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CAMPAIGN_ID varchar(36) not null,
    BANNER_POSITION_ID varchar(36) not null,
    DAILY_IMPRESSIONS integer not null,
    START_DATE timestamp not null,
    END_DATE timestamp not null,
    --
    primary key (ID)
)^
-- end CAMPAIGN_CAMPAIGN_BANNER_POSITION
-- begin CAMPAIGN_WEBSITE
create table CAMPAIGN_WEBSITE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    WEB_VERSION integer,
    IMPRESSIONS_LIMIT bigint not null,
    --
    primary key (ID)
)^
-- end CAMPAIGN_WEBSITE
