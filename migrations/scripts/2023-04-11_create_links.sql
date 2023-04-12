--liquibase formatted sql

--changeset wani4ka:create_links

create sequence link_id_seq;
create table link (
    id bigint not null primary key default nextval('link_id_seq'),
    url text not null
);
