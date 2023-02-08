INSERT INTO "role"
("id", "name")
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'moscow', 'email1@domain.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 1),
       (101, 'about', 'spb', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 1),
       (102, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 2),
       (103, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 2),
       (104, 'about', 'moscow', 'email5@domain.com', 'name5', 'http://imagelink5.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$12$4HMf/TZerIaDSGG24Eoa3.mDx.UB6bdb6fGkWIU55rj7wRLgCgYVC', '2023-01-21 15:17:18.280368', 2);

INSERT INTO "reputation"
("id", "count", "persist_date", "type", "answer_id", "author_id", "question_id", "sender_id")
VALUES (100, 1, '2023-01-19 15:21:03.527867', 2, NULL, 100, NULL, NULL),
       (101, 2, '2023-01-19 15:21:03.527867', 2, NULL, 101, NULL, NULL),
       (102, 2, '2023-01-19 15:21:03.527867', 2, NULL, 102, NULL, NULL),
       (103, 3, '2023-01-19 15:21:03.527867', 2, NULL, 103, NULL, NULL),
       (104, 2, '2023-01-20 15:21:03.527867', 2, NULL, 100, NULL, NULL),
       (105, 6, '2023-01-20 15:21:03.527867', 2, NULL, 101, NULL, NULL),
       (106, 5, '2023-01-20 15:21:03.527867', 2, NULL, 102, NULL, NULL),
       (107, 2, '2023-01-20 15:21:03.527867', 2, NULL, 103, NULL, NULL),
       (108, 3, '2023-01-21 15:21:03.527867', 2, NULL, 100, NULL, NULL),
       (109, 6, '2023-01-21 15:21:03.527867', 2, NULL, 101, NULL, NULL),
       (110, 4, '2023-01-21 15:21:03.527867', 2, NULL, 102, NULL, NULL),
       (111, 3, '2023-01-20 15:21:03.527867', 2, NULL, 103, NULL, NULL);