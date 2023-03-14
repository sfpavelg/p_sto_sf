
INSERT INTO "role"
("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');


INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about5', 'Moscow', '5@gmail.com', 'name5', 'http://imagelink5.com', 'f', 't',
        '2023-01-01 00:00:00.000000', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$10$I3vb.sC2pu/SqKopyYMSD.af.gt3ID7l9vybpYDAPjO/mfT1gMD2y', '2023-01-01 00:00:00.000000', 200),
       (101, 'about1', 'St. Petersburg', 'super1@gmail.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-01 01:01:01.111111', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '1pwd', '2023-01-01 01:01:01.111111', 100);
