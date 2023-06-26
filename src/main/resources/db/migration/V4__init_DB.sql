create table if not exists "user_chat_pin"
(
    id serial8 not null,
    user_id int8 not null,
    chat_id int8 not null,
    persist_date timestamp,
    primary key (id)
);