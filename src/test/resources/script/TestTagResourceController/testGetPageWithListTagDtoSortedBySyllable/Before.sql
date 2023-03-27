INSERT INTO "role"
("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'Moscow', '0@gmail.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$10$/Hq12PgE.XuzB3ZizNOIXuBnCjyK/9/oHJ1Y/FRPbJZO8jSBzD1F.', '2023-01-21 15:17:18.280368', 200),
       (101, 'about', 'St. Petersburg', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', 'password', '2023-01-21 15:17:18.280368', 200),
       (102, 'about', 'Kazan', 'email3@domain.com', 'name3', null, 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', 'password', '2023-01-21 15:17:18.280368', 100),
       (103, 'about', 'Ekaterinburg', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', 'password', '2023-01-21 15:17:18.280368', 200),
       (104, 'about', 'Samara', 'email5@domain.com', 'name5', 'http://imagelink5.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', 'password', '2023-01-21 15:17:18.280368', 100);

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (100, 'description0', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title0', 100),
       (101, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 101),
       (102, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 102),
       (103, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 103),
       (104, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 104),
       (105, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 100),
       (106, 'description6', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title6', 101),
       (107, 'description7', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title7', 102),
       (108, 'description8', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title8', 103),
       (109, 'description9', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title9', 104),
       (110, 'description10', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title10', 104);

INSERT INTO "tag"
("id", "name", "description", "persist_date")
VALUES  (100, 'Java', 'Java description', '2023-03-17 23:56:11.245126'),
        (101, 'JavaScript', 'JavaScript description', '2023-03-17 23:56:11.245126'),
        (102, 'C#', 'C# description', '2023-03-17 23:56:11.245126'),
        (103, 'HTML', 'HTML description', '2023-03-17 23:56:11.245126'),
        (104, 'Python', 'Python description', '2023-03-17 23:56:11.245126');

INSERT INTO "question_has_tag"
("tag_id", "question_id")
VALUES  (100,100),
        (100,101),
        (101,102),
        (101,103),
        (101,104),
        (101,105),
        (102,106),
        (103,107),
        (103,108),
        (103,100),
        (103,101),
        (103,102),
        (103,109),
        (103,110);