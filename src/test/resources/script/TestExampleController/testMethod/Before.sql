INSERT INTO "role"
("id", "name")
VALUES (1, 'ADMIN'),
       (2, 'USER');


INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about0', 'Moscow', 'email0@domain.com', 'name0', 'http://imagelink0.com', 'f', 't',
        '2023-01-01 00:00:00.000000', 'http://github.com/0', 'http://site0.com', 'http://vk.com/0',
        'nickname0', 'password', '2023-01-01 00:00:00.000000', 1),
       (101, 'about1', 'St. Petersburg', 'email1@domain.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-01 01:01:01.111111', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', 'password', '2023-01-01 01:01:01.111111', 2),
       (102, 'about2', 'Kazan', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-01 02:02:02.222222', 'http://github.com/2', 'http://site2.com', 'http://vk.com/2',
        'nickname2', 'password', '2023-01-01 02:02:02.222222', 1),
       (103, 'about3', 'Ekaterinburg', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-01 03:03:03.333333', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', 'password', '2023-01-01 03:03:03.333333', 2),
       (104, 'about4', 'Samara', 'email4@domain.com', 'name4', null, 'f', 't',
        '2023-01-01 04:04:04.444444', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', 'password', '2023-01-01 04:04:04.444444', 1);