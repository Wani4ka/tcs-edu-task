--liquibase formatted sql

--changeset wani4ka:add_triggers

create function clear_unused_links()
    returns void language plpgsql as
$$
    declare
    begin
        delete from link
            where not exists(select null from subscription s where s.link_id = link.id);
    end
$$;

create function clear_unused_chats()
    returns void language plpgsql as
$$
    declare
    begin
        delete from chat
            where not exists(select null from subscription s where s.chat_id = chat.id);
    end
$$;

create function clear_unused()
    returns trigger language plpgsql as
$$
    declare
    begin
        execute clear_unused_links();
        execute clear_unused_chats();
        return new;
    end
$$;

create trigger clear_links_trigger
    after delete
    on subscription
    for each statement
    execute procedure clear_unused();

