create table if not exists "group_bookmark"
(
    id          serial8 not null,
    title       varchar(255),
    user_id     int8    not null,
    bookmark_id int8,
    primary key (id)
);

create table if not exists "group_bookmark_link"
(
    group_bookmark_id int8 not null,
    bookmark_id       int8 not null,
    primary key (group_bookmark_id, bookmark_id)
);

alter table group_bookmark_link
    add constraint group_bookmark_link_bookmark_group_fk
        foreign key (group_bookmark_id)
            references group_bookmark;

alter table group_bookmark_link
    add constraint group_bookmark_link_bookmark_fk
        foreign key (bookmark_id)
            references bookmarks;

alter table group_bookmark
    add constraint group_bookmark_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table group_bookmark
    add constraint group_bookmark_bookmark_fk
        foreign key (bookmark_id)
            references bookmarks;