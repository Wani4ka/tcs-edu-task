--liquibase formatted sql

--changeset wani4ka:last_link_event

alter table link
    add column last_event timestamp with time zone not null default now();
