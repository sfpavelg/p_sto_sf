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
VALUES (501, 'about', 'moscow', 'email1@domain.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 100),
       (502, 'about', 'spb', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 100),
       (503, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200),
       (504, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200),
       (505, 'about', 'moscow', 'email5@domain.com', 'name5', 'http://imagelink5.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200),
       (506, 'about', 'moscow', 'email6@domain.com', 'name6', 'http://imagelink6.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/6', 'http://site6.com', 'http://vk.com/6',
        'nickname6', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 200);


INSERT INTO single_chat(chat_id, use_two_id, user_one_id)
VALUES (100, 501, 502),
       (101, 503, 504),
       (102, 505, 506);

INSERT INTO message (id, last_redaction_date, message, persist_date, chat_id, user_sender_id)
VALUES (1, current_timestamp, 'user 501 message for tests', current_timestamp, 100, 501),
       (2, current_timestamp, 'user 501 message for tests', current_timestamp, 100, 501),
       (3, current_timestamp, 'user 501 message for tests', current_timestamp, 100, 501),
       (4, current_timestamp, 'user 502 message for tests', current_timestamp, 100, 502),
       (5, current_timestamp, 'user 502 message for tests', current_timestamp, 100, 502),
       (6, current_timestamp, 'user 502 message for tests', current_timestamp, 100, 502),
       (7, current_timestamp, 'user 502 message for tests', current_timestamp, 100, 502),
       (8, current_timestamp, 'user 503 message for tests', current_timestamp, 101, 503),
       (9, current_timestamp, 'user 503 message for tests', current_timestamp, 101, 503),
       (10, current_timestamp, 'user 503 message for tests', current_timestamp, 101, 503),
       (11, current_timestamp, 'user 503 message for tests', current_timestamp, 101, 503),
       (12, current_timestamp, 'user 503 message for tests', current_timestamp, 101, 503),
       (13, current_timestamp, 'user 504 message for tests', current_timestamp, 101, 504),
       (14, current_timestamp, 'user 504 message for tests', current_timestamp, 101, 504),
       (15, current_timestamp, 'user 505 message for tests', current_timestamp, 102, 505),
       (16, current_timestamp, 'user 505 message for tests', current_timestamp, 102, 505),
       (17, current_timestamp, 'user 505 message for tests', current_timestamp, 102, 505),
       (18, current_timestamp, 'user 506 message for tests', current_timestamp, 102, 506),
       (19, current_timestamp, 'user 506 message for tests', current_timestamp, 102, 506),
       (20, current_timestamp, 'user 506 message for tests', current_timestamp, 102, 506);