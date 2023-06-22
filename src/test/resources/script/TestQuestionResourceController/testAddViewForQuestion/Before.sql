INSERT INTO "role"
("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');


INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'moscow', '0@gmail.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$10$/Hq12PgE.XuzB3ZizNOIXuBnCjyK/9/oHJ1Y/FRPbJZO8jSBzD1F.', '2023-01-21 15:17:18.280368', 200),
       (101, 'about', 'St. Petersburg', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', 'password', '2023-01-21 15:17:18.280368', 200);

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (100, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 101),
       (101, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 101),
       (102, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 101);