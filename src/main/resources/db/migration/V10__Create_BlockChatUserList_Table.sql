create table if not exists "block_chat_user_list"
(
    id           serial8   not null,
    profile_id   int8      not null,
    blocked_id   int8      not null,
    persist_date timestamp not null,
    foreign key (profile_id) references user_entity (id),
    foreign key (blocked_id) references user_entity (id)
);

create sequence if not exists block_chat_user_list_id_seq
    start with 1
    increment by 1
    no minvalue
    no maxvalue
    cache 1;