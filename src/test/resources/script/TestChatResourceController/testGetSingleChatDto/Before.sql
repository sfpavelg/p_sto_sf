INSERT INTO chat (id, chat_type, persist_date, title)
VALUES (100, 0, current_timestamp, 'SingleChat100'),
       (101, 0, current_timestamp + interval '1 second', 'SingleChat101'),
       (102, 0, current_timestamp + interval '2 seconds', 'SingleChat102');

INSERT INTO "role" ("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (101, 'about', 'moscow', 'email1@domain.com', 'name1', 'http://imagelink1.com', 'f', 't',
        current_timestamp, 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', current_timestamp + interval '1 second', 200),
       (102, 'about', 'spb', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        current_timestamp + interval '2 seconds', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', current_timestamp + interval '3 seconds', 200),
       (103, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        current_timestamp + interval '4 seconds', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', current_timestamp + interval '5 seconds', 200),
       (104, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        current_timestamp + interval '6 seconds', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', current_timestamp + interval '7 seconds', 200),
       (105, 'about', 'moscow', 'email5@domain.com', 'name5', 'http://imagelink5.com', 'f', 't',
        current_timestamp + interval '8 seconds', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', current_timestamp + interval '9 seconds', 200),
       (106, 'about', 'moscow', 'email6@domain.com', 'name6', 'http://imagelink6.com', 'f', 't',
        current_timestamp + interval '10 seconds', 'http://github.com/6', 'http://site6.com', 'http://vk.com/6',
        'nickname6', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', current_timestamp + interval '11 seconds', 200);

INSERT INTO single_chat(chat_id, use_two_id, user_one_id)
VALUES (100, 101, 102),
       (101, 104, 101),
       (102, 105, 106);

INSERT INTO message (id, last_redaction_date, message, persist_date, chat_id, user_sender_id)
VALUES (101, current_timestamp + interval '1 second', 'user 101 message for tests', current_timestamp + interval '2 seconds', 100, 101),
       (102, current_timestamp + interval '3 seconds', 'user 101 message for tests', current_timestamp + interval '4 seconds', 100, 101),
       (103, current_timestamp + interval '5 seconds', 'user 101 message for tests', current_timestamp + interval '6 seconds', 100, 101),
       (104, current_timestamp + interval '7 seconds', 'user 102 message for tests', current_timestamp + interval '8 seconds', 100, 102),
       (105, current_timestamp + interval '9 seconds', 'user 102 message for tests', current_timestamp + interval '10 seconds', 100, 102),
       (106, current_timestamp + interval '11 seconds', 'user 102 message for tests', current_timestamp + interval '12 seconds', 100, 102),
       (107, current_timestamp + interval '13 seconds', 'user 102 LAST message for tests', current_timestamp + interval '14 seconds', 100, 102),
       (108, current_timestamp + interval '15 seconds', 'user 104 message for tests', current_timestamp + interval '16 seconds', 101, 104),
       (109, current_timestamp + interval '17 seconds', 'user 104 message for tests', current_timestamp + interval '18 seconds', 101, 104),
       (110, current_timestamp + interval '19 seconds', 'user 104 message for tests', current_timestamp + interval '20 seconds', 101, 104),
       (111, current_timestamp + interval '21 seconds', 'user 104 message for tests', current_timestamp + interval '22 seconds', 101, 104),
       (112, current_timestamp + interval '23 seconds', 'user 101 message for tests', current_timestamp + interval '24 seconds', 101, 101),
       (113, current_timestamp + interval '25 seconds', 'user 101 message for tests', current_timestamp + interval '26 seconds', 101, 101),
       (114, current_timestamp + interval '27 seconds', 'user 101 LAST message for tests', current_timestamp + interval '28 seconds', 101, 101),
       (115, current_timestamp + interval '29 seconds', 'user 105 message for tests', current_timestamp + interval '30 seconds', 102, 105),
       (116, current_timestamp + interval '31 seconds', 'user 105 message for tests', current_timestamp + interval '32 seconds', 102, 105),
       (117, current_timestamp + interval '33 seconds', 'user 105 message for tests', current_timestamp + interval '34 seconds', 102, 105),
       (118, current_timestamp + interval '35 seconds', 'user 106 message for tests', current_timestamp + interval '36 seconds', 102, 106),
       (119, current_timestamp + interval '37 seconds', 'user 106 message for tests', current_timestamp + interval '38 seconds', 102, 106),
       (120, current_timestamp + interval '39 seconds', 'user 106 LAST message for tests', current_timestamp + interval '40 seconds', 102, 106);
