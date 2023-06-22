create table if not exists "answer"
(
    id                      serial8   not null,
    date_accept_time        timestamp,
    html_body               text      not null,
    is_deleted              boolean   not null,
    is_deleted_by_moderator boolean   not null,
    is_helpful              boolean   not null,
    persist_date            timestamp,
    update_date             timestamp not null,
    question_id             int8      not null,
    user_id                 int8      not null,
    primary key (id)
);

create table if not exists "badges"
(
    id                    serial8 not null,
    badge_name            varchar(255),
    description           varchar(255),
    reputations_for_merit int4,
    primary key (id)
);

create table if not exists "bookmarks"
(
    id          serial8 not null,
    question_id int8    not null,
    user_id     int8    not null,
    primary key (id)
);

create table if not exists "chat"
(
    id           serial8 not null,
    chat_type    int2,
    persist_date timestamp,
    title        varchar(255),
    primary key (id)
);


create table if not exists "comment"
(
    id                  serial8      not null,
    comment_type        int2         not null,
    last_redaction_date timestamp,
    persist_date        timestamp,
    text                varchar(255) not null,
    user_id             int8         not null,
    primary key (id)
);

create table if not exists "comment_answer"
(
    comment_id int8 not null,
    answer_id  int8 not null,
    primary key (comment_id)
);

create table if not exists "comment_question"
(
    comment_id  int8 not null,
    question_id int8 not null,
    primary key (comment_id)
);

create table if not exists "group_chat"
(
    chat_id int8 not null,
    primary key (chat_id)
);

create table if not exists "groupchat_has_users"
(
    chat_id int8 not null,
    user_id int8 not null,
    primary key (chat_id, user_id)
);

create table if not exists "message"
(
    id                  serial8   not null,
    last_redaction_date timestamp not null,
    message             text,
    persist_date        timestamp,
    chat_id             int8      not null,
    user_sender_id      int8      not null,
    primary key (id)
);

create table if not exists "question"
(
    id                  serial8      not null,
    description         text         not null,
    is_deleted          boolean,
    last_redaction_date timestamp    not null,
    persist_date        timestamp,
    title               varchar(255) not null,
    user_id             int8         not null,
    primary key (id)
);

create table if not exists "question_has_tag"
(
    question_id int8 not null,
    tag_id      int8 not null
);

create table if not exists "question_viewed"
(
    id           serial8 not null,
    persist_date timestamp,
    question_id  int8,
    user_id      int8,
    primary key (id)
);

create table if not exists "related_tag"
(
    id        serial8 not null,
    child_tag int8    not null,
    main_tag  int8    not null,
    primary key (id)
);

create table if not exists "reputation"
(
    id           serial8 not null,
    count        int4,
    persist_date timestamp,
    type         int4    not null,
    answer_id    int8,
    author_id    int8    not null,
    question_id  int8,
    sender_id    int8,
    primary key (id)
);

create table if not exists "role"
(
    id   serial8 not null,
    name varchar(255),
    primary key (id)
);

create table if not exists "single_chat"
(
    chat_id     int8 not null,
    use_two_id  int8 not null,
    user_one_id int8 not null,
    primary key (chat_id)
);

create table if not exists "tag"
(
    id           serial8      not null,
    description  varchar(255),
    name         varchar(255) not null,
    persist_date timestamp,
    primary key (id)
);

create table if not exists "tag_ignore"
(
    id             serial8 not null,
    persist_date   timestamp,
    ignored_tag_id int8    not null,
    user_id        int8    not null,
    primary key (id)
);

create table if not exists "tag_tracked"
(
    id             serial8 not null,
    persist_date   timestamp,
    tracked_tag_id int8    not null,
    user_id        int8    not null,
    primary key (id)
);

create table if not exists "user_badges"
(
    id        serial8 not null,
    ready     boolean,
    badges_id int8,
    user_id   int8,
    primary key (id)
);

create table if not exists "user_entity"
(
    id                  serial8   not null,
    about               varchar(255),
    city                varchar(255),
    email               varchar(255),
    full_name           varchar(255),
    image_link          varchar(255),
    is_deleted          boolean,
    is_enabled          boolean,
    last_redaction_date timestamp not null,
    link_github         varchar(255),
    link_site           varchar(255),
    link_vk             varchar(255),
    nickname            varchar(255),
    password            varchar(255),
    persist_date        timestamp,
    role_id             int8      not null,
    primary key (id)
);

create table if not exists "user_favorite_question"
(
    id           serial8   not null,
    persist_date timestamp not null,
    question_id  int8      not null,
    user_id      int8      not null,
    primary key (id)
);

create table if not exists "votes_on_answers"
(
    id           serial8 not null,
    persist_date timestamp,
    vote         varchar(255),
    answer_id    int8    not null,
    user_id      int8    not null,
    primary key (id)
);

create table if not exists "votes_on_questions"
(
    id           serial8 not null,
    persist_date timestamp,
    vote         varchar(255),
    question_id  int8,
    user_id      int8,
    primary key (id)
);

alter table answer
    add constraint answer_question_fk
        foreign key (question_id)
            references question;

alter table answer
    add constraint answer_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table bookmarks
    add constraint bookmarks_question_fk
        foreign key (question_id)
            references question;

alter table bookmarks
    add constraint bookmarks_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table comment
    add constraint comment_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table comment_answer
    add constraint comment_answer_answer_fk
        foreign key (answer_id)
            references answer;

alter table comment_answer
    add constraint comment_answer_comment_fk
        foreign key (comment_id)
            references comment;

alter table comment_question
    add constraint comment_question_comment_fk
        foreign key (comment_id)
            references comment;

alter table comment_question
    add constraint comment_question_question_fk
        foreign key (question_id)
            references question;

alter table group_chat
    add constraint group_chat_chat_fk
        foreign key (chat_id)
            references chat;

alter table groupchat_has_users
    add constraint groupchat_has_users_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table groupchat_has_users
    add constraint groupchat_has_users_group_chat_fk
        foreign key (chat_id)
            references group_chat;

alter table message
    add constraint message_chat_fk
        foreign key (chat_id)
            references chat;

alter table message
    add constraint message_user_entity_fk
        foreign key (user_sender_id)
            references user_entity;

alter table question
    add constraint question_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table question_has_tag
    add constraint question_has_tag_tag_fk
        foreign key (tag_id)
            references tag;

alter table question_has_tag
    add constraint question_has_tag_question_fk
        foreign key (question_id)
            references question;

alter table question_viewed
    add constraint question_viewed_question_fk
        foreign key (question_id)
            references question;

alter table question_viewed
    add constraint question_viewed_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table related_tag
    add constraint related_tag_child_tag_fk
        foreign key (child_tag)
            references tag;

alter table related_tag
    add constraint related_tag_main_tag_fk
        foreign key (main_tag)
            references tag;

alter table reputation
    add constraint reputation_answer_fk
        foreign key (answer_id)
            references answer;

alter table reputation
    add constraint reputation_author_user_entity_fk
        foreign key (author_id)
            references user_entity;

alter table reputation
    add constraint reputation_question_fk
        foreign key (question_id)
            references question;

alter table reputation
    add constraint reputation_sender_user_entity_fk
        foreign key (sender_id)
            references user_entity;

alter table single_chat
    add constraint single_chat_chat_fk
        foreign key (chat_id)
            references chat;

alter table single_chat
    add constraint single_chat_first_user_entity_fk
        foreign key (use_two_id)
            references user_entity;

alter table single_chat
    add constraint single_chat_second_user_entity_fk
        foreign key (user_one_id)
            references user_entity;

alter table tag_ignore
    add constraint tag_ignore_tag_fk
        foreign key (ignored_tag_id)
            references tag;

alter table tag_ignore
    add constraint tag_ignore_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table tag_tracked
    add constraint tag_tracked_tag_fk
        foreign key (tracked_tag_id)
            references tag;

alter table tag_tracked
    add constraint tag_tracked_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table user_badges
    add constraint user_badges_badges_fk
        foreign key (badges_id)
            references badges;

alter table user_badges
    add constraint user_badges_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table user_entity
    add constraint user_entity_role_fk
        foreign key (role_id)
            references role;

alter table user_favorite_question
    add constraint user_favorite_question_question_fk
        foreign key (question_id)
            references question;

alter table user_favorite_question
    add constraint user_favorite_question_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table votes_on_answers
    add constraint votes_on_answers_answer_fk
        foreign key (answer_id)
            references answer;

alter table votes_on_answers
    add constraint votes_on_answers_user_entity_fk
        foreign key (user_id)
            references user_entity;

alter table votes_on_questions
    add constraint votes_on_questions_question_fk
        foreign key (question_id)
            references question;

alter table votes_on_questions
    add constraint votes_on_questions_user_entity_fk
        foreign key (user_id)
            references user_entity;