--liquibase formatted sql

--changeset wani4ka:add_unique_constraints

alter table link
    add constraint unique_url unique (url);
alter table subscription
    add constraint unique_pair unique (link_id, chat_id);
