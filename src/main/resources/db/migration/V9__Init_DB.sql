alter table group_chat add column author_id int8;
alter table group_chat add constraint fk_author foreign key (author_id) references user_entity(id);