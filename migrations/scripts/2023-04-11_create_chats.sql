--liquibase formatted sql

--changeset wani4ka:create_chats

create table chat (
    id bigint not null primary key
);
