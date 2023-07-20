INSERT INTO chat (id, chat_type, persist_date, title)
VALUES (100, 1, current_timestamp, 'GroupChat100'),
       (101, 1, current_timestamp + interval '1 second', 'GroupChat101'),
       (102, 0, current_timestamp + interval '2 seconds', 'SingleChat102'),
       (103, 0, current_timestamp + interval '3 seconds', 'SingleChat103');

INSERT INTO "role" ("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (101, 'about', 'moscow', 'email1@domain.com', 'name1', 'http://imagelink1.com', 'f', 't',
        current_timestamp, 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '1 second', 200),
       (102, 'about', 'spb', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        current_timestamp + interval '2 seconds', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '3 seconds', 200),
       (103, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        current_timestamp + interval '4 seconds', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '5 seconds', 200),
       (104, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        current_timestamp + interval '6 seconds', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '7 seconds', 200),
       (105, 'about', 'moscow', 'email5@domain.com', 'name5', 'http://imagelink5.com', 'f', 't',
        current_timestamp + interval '8 seconds', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '9 seconds', 200),
       (106, 'about', 'moscow', 'email6@domain.com', 'name6', 'http://imagelink6.com', 'f', 't',
        current_timestamp + interval '10 seconds', 'http://github.com/6', 'http://site6.com', 'http://vk.com/6',
        'nickname6', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '11 seconds', 200),
       (107, 'about', 'moscow', 'email7@domain.com', 'name7', 'http://imagelink7.com', 'f', 't',
        current_timestamp + interval '11 seconds', 'http://github.com/7', 'http://site7.com', 'http://vk.com/7',
        'nickname7', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '12 seconds', 200),
       (108, 'about', 'moscow', 'email8@domain.com', 'name8', 'http://imagelink8.com', 'f', 't',
        current_timestamp + interval '12 seconds', 'http://github.com/8', 'http://site8.com', 'http://vk.com/6',
        'nickname8', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '12 seconds', 200),
       (109, 'about', 'moscow', 'email9@domain.com', 'name9', 'http://imagelink9.com', 'f', 't',
        current_timestamp + interval '13 seconds', 'http://github.com/9', 'http://site9.com', 'http://vk.com/9',
        'nickname9', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '13 seconds', 200),
       (110, 'about', 'moscow', 'email10@domain.com', 'name10', null, 'f', 't',
        current_timestamp + interval '14 seconds', 'http://github.com/10', 'http://site10.com', 'http://vk.com/10',
        'nickname10', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC',
        current_timestamp + interval '14 seconds', 200);

INSERT INTO group_chat (chat_id, is_global)
VALUES (100, true),
       (101, false);

INSERT INTO groupchat_has_users (chat_id, user_id)
VALUES (100, 101),
       (100, 102),
       (100, 103),
       (100, 104),
       (101, 105),
       (101, 106),
       (101, 107),
       (100, 108);

INSERT INTO single_chat(chat_id, use_two_id, user_one_id)
VALUES (102, 101, 102),
       (103, 104, 101);

INSERT INTO message (id, last_redaction_date, message, persist_date, chat_id, user_sender_id)
VALUES (101, current_timestamp + interval '1 second', 'user 101 message for tests',
        current_timestamp + interval '2 seconds', 102, 101),
       (102, current_timestamp + interval '3 seconds', 'user 101 message for tests',
        current_timestamp + interval '4 seconds', 102, 101),
       (103, current_timestamp + interval '5 seconds', 'user 101 message for tests',
        current_timestamp + interval '6 seconds', 102, 101),
       (104, current_timestamp + interval '7 seconds', 'user 102 message for tests',
        current_timestamp + interval '8 seconds', 102, 102),
       (105, current_timestamp + interval '9 seconds', 'user 102 message for tests',
        current_timestamp + interval '10 seconds', 102, 102),
       (108, current_timestamp + interval '15 seconds', 'user 104 message for tests',
        current_timestamp + interval '16 seconds', 103, 104),
       (109, current_timestamp + interval '17 seconds', 'user 104 message for tests',
        current_timestamp + interval '18 seconds', 103, 104),
       (110, current_timestamp + interval '19 seconds', 'user 104 message for tests',
        current_timestamp + interval '20 seconds', 103, 104),
       (111, current_timestamp + interval '21 seconds', 'user 104 message for tests',
        current_timestamp + interval '22 seconds', 103, 104),
       (112, current_timestamp + interval '23 seconds', 'user 101 message for tests',
        current_timestamp + interval '24 seconds', 103, 101),
       (113, current_timestamp + interval '25 seconds', 'user 101 message for tests',
        current_timestamp + interval '26 seconds', 103, 101),
       (114, current_timestamp + interval '27 seconds', 'user 101 LAST message for tests',
        current_timestamp + interval '28 seconds', 103, 101),
       (115, current_timestamp + interval '29 seconds', 'user 105 message for tests',
        current_timestamp + interval '30 seconds', 100, 101),
       (116, current_timestamp + interval '31 seconds', 'user 105 message for tests',
        current_timestamp + interval '32 seconds', 100, 102),
       (117, current_timestamp + interval '33 seconds', 'user 105 message for tests',
        current_timestamp + interval '34 seconds', 100, 103),
       (118, current_timestamp + interval '35 seconds', 'user 106 message for tests',
        current_timestamp + interval '36 seconds', 101, 105),
       (119, current_timestamp + interval '37 seconds', 'user 106 message for tests',
        current_timestamp + interval '38 seconds', 101, 106),
       (120, current_timestamp + interval '39 seconds', 'user 106 LAST message for tests',
        current_timestamp + interval '40 seconds', 101, 107),
       (121, current_timestamp + interval '40 seconds', 'user 110 LAST message for tests',
        current_timestamp + interval '41 seconds', 101, 108);

