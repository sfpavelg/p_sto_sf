INSERT INTO "role"
    ("id", "name")
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (1, 'about', 'moscow', 'email1@domain.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', 'password', '2023-01-21 15:17:18.280368', 1),
       (2, 'about', 'spb', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', 'password', '2023-01-21 15:17:18.280368', 1),
       (3, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', 'password', '2023-01-21 15:17:18.280368', 2),
       (4, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', 'password', '2023-01-21 15:17:18.280368', 2);

INSERT INTO "reputation"
("id", "count", "persist_date", "type", "answer_id", "author_id", "question_id", "sender_id")
VALUES (1, 1, '2023-01-19 15:21:03.527867', 2, NULL, 1, NULL, NULL),
       (2, 2, '2023-01-19 15:21:03.527867', 2, NULL, 2, NULL, NULL),
       (3, 2, '2023-01-19 15:21:03.527867', 2, NULL, 3, NULL, NULL),
       (4, 3, '2023-01-19 15:21:03.527867', 2, NULL, 4, NULL, NULL),
       (5, 2, '2023-01-20 15:21:03.527867', 2, NULL, 1, NULL, NULL),
       (6, 6, '2023-01-20 15:21:03.527867', 2, NULL, 2, NULL, NULL),
       (7, 5, '2023-01-20 15:21:03.527867', 2, NULL, 3, NULL, NULL),
       (8, 2, '2023-01-20 15:21:03.527867', 2, NULL, 4, NULL, NULL),
       (9, 3, '2023-01-21 15:21:03.527867', 2, NULL, 1, NULL, NULL),
       (10, 6, '2023-01-21 15:21:03.527867', 2, NULL, 2, NULL, NULL),
       (11, 4, '2023-01-21 15:21:03.527867', 2, NULL, 3, NULL, NULL),
       (12, 3, '2023-01-20 15:21:03.527867', 2, NULL, 4, NULL, NULL);