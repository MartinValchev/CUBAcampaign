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
);
