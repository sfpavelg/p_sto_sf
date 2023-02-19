INSERT INTO "role"
("id", "name")
VALUES (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'moscow', '4@gmail.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$10$csoFbLmhKaon8rk5JC/CQ.ZkyE3T5lOwiWB/soHnIbmTqGzDLJknK', '2023-01-21 15:17:18.280368', 200);

INSERT INTO "tag"
("id", "description", "name", "persist_date")
VALUES (100, 'description1', 'tag1', '2023-01-27 13:01:11.245126'),
       (101, 'description2', 'tag2', '2023-01-27 13:01:11.245126'),
       (102, 'description2', 'tag3', '2023-01-27 13:01:11.245126'),
       (103, 'description3', 'tag4', '2023-01-27 13:01:11.245126');

INSERT INTO "tag_ignore"
(id, persist_date, ignored_tag_id, user_id)
VALUES (300, '2023-01-27 13:01:11.245126', 100, 100),
       (301, '2023-01-27 13:01:11.245126', 101, 100),
       (302, '2023-01-27 13:01:11.245126', 103, 100);