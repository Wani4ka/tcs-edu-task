--liquibase formatted sql

--changeset wani4ka:last_link_check

alter table link
    add column last_check timestamp with time zone not null default now();
