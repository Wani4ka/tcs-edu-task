--liquibase formatted sql

--changeset wani4ka:create_subscriptions

create sequence subscription_id_seq;
create table subscription (
                      id bigint not null primary key default nextval('subscription_id_seq'),
                      link_id bigint,
                      chat_id bigint,
                      constraint fk_url
                          foreign key (link_id) references link(id) on delete cascade,
                      constraint fk_chat
                          foreign key (chat_id) references chat(id) on delete cascade
);
