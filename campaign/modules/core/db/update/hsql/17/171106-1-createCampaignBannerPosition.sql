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
    IMPRESSIONS bigint not null,
    --
    primary key (ID)
);
