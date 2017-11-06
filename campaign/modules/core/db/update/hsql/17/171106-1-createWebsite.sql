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
);
