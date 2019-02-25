create table users(
      username varchar(128) not null primary key,
      password varchar(512) not null,
      enabled boolean not null);

create table authorities (
      username varchar(128) not null,
      authority varchar(128) not null);

create unique index idx_auth_username on authorities (username,authority);

CREATE TABLE PUBLIC.T_OWNER(
    ID BIGINT NOT NULL,
    FIRST_NAME VARCHAR(255) not null,
    LAST_NAME VARCHAR(255) not null
);
ALTER TABLE PUBLIC.T_OWNER ADD CONSTRAINT PUBLIC.CONSTRAINT_1 PRIMARY KEY(ID);

CREATE TABLE PUBLIC.T_PET(
    ID BIGINT NOT NULL,
    NAME VARCHAR(255),
    BIRTH_DATE DATE,
    OWNER_ID BIGINT
);
ALTER TABLE PUBLIC.T_PET ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(ID);

ALTER TABLE PUBLIC.T_PET ADD  CONSTRAINT public.CONSTRAINT_3 FOREIGN KEY(OWNER_ID)  REFERENCES PUBLIC.T_OWNER(ID);

CREATE SEQUENCE PUBLIC.PETCLINIC_SEQUENCE START WITH 100;
