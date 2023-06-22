INSERT INTO chat (id, chat_type, persist_date, title)
VALUES (100, 0, current_timestamp, 'SingleChat100'),
       (101, 0, current_timestamp, 'SingleChat101'),
       (102, 0, current_timestamp, 'SingleChat102');

INSERT INTO group_chat (chat_id, is_global)
VALUES (100, false),
       (101, false),
       (102, false);

INSERT INTO "role" ("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (101, 'about', 'moscow', 'email1@domain.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 100),
       (102, 'about', 'spb', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 100),
       (103, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200),
       (104, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200),
       (105, 'about', 'moscow', 'email5@domain.com', 'name5', 'http://imagelink5.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200),
       (106, 'about', 'moscow', 'email6@domain.com', 'name6', 'http://imagelink6.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/6', 'http://site6.com', 'http://vk.com/6',
        'nickname6', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200);


INSERT INTO single_chat(chat_id, use_two_id, user_one_id)
VALUES (100, 101, 102),
       (101, 103, 104),
       (102, 105, 106);

INSERT INTO message (id, last_redaction_date, message, persist_date, chat_id, user_sender_id)
VALUES (101, current_timestamp, 'user 101 message for tests', current_timestamp, 100, 101),
       (102, current_timestamp, 'user 101 message for tests', current_timestamp, 100, 101),
       (103, current_timestamp, 'user 101 message for tests', current_timestamp, 100, 101),
       (104, current_timestamp, 'user 102 message for tests', current_timestamp, 100, 102),
       (105, current_timestamp, 'user 102 message for tests', current_timestamp, 100, 102),
       (106, current_timestamp, 'user 102 message for tests', current_timestamp, 100, 102),
       (107, current_timestamp, 'user 102 message for tests', current_timestamp, 100, 102),
       (108, current_timestamp, 'user 103 message for tests', current_timestamp, 101, 103),
       (109, current_timestamp, 'user 103 message for tests', current_timestamp, 101, 103),
       (110, current_timestamp, 'user 103 message for tests', current_timestamp, 101, 103),
       (111, current_timestamp, 'user 103 message for tests', current_timestamp, 101, 103),
       (112, current_timestamp, 'user 103 message for tests', current_timestamp, 101, 103),
       (113, current_timestamp, 'user 104 message for tests', current_timestamp, 101, 104),
       (114, current_timestamp, 'user 104 message for tests', current_timestamp, 101, 104),
       (115, current_timestamp, 'user 105 message for tests', current_timestamp, 102, 105),
       (116, current_timestamp, 'user 105 message for tests', current_timestamp, 102, 105),
       (117, current_timestamp, 'user 105 message for tests', current_timestamp, 102, 105),
       (118, current_timestamp, 'user 106 message for tests', current_timestamp, 102, 106),
       (119, current_timestamp, 'user 106 message for tests', current_timestamp, 102, 106),
       (120, current_timestamp, 'user 106 message for tests', current_timestamp, 102, 106);