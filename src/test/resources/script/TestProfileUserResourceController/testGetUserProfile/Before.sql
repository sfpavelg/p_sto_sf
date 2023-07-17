INSERT INTO "role" ("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'moscow', '100@gmail.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$10$/Hq12PgE.XuzB3ZizNOIXuBnCjyK/9/oHJ1Y/FRPbJZO8jSBzD1F.', '2023-01-21 15:17:18.280368', 200),
       (101, 'about', 'spb', 'email2@domain.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', 'password', '2023-01-21 15:17:18.280368', 200),
       (102, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', 'password', '2023-01-21 15:17:18.280368', 100),
       (103, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', 'password', '2023-01-21 15:17:18.280368', 200),
       (104, 'about', 'spb', 'email5@domain.com', 'name5', null, 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', 'password', '2023-01-21 15:17:18.280368', 100),
       (105, 'about', 'spb', 'email6@domain.com', 'name6', null, 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/6', 'http://site6.com', 'http://vk.com/6',
        'nickname6', '$2a$10$/Hq12PgE.XuzB3ZizNOIXuBnCjyK/9/oHJ1Y/FRPbJZO8jSBzD1F.', '2023-01-21 15:17:18.280368', 200);

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

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (100, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 100),
       (101, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 101),
       (102, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 102),
       (103, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 103),
       (104, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 104),
       (105, 'description6', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title6', 100);

INSERT INTO "question_viewed"
("id", "persist_date", "question_id", "user_id")
VALUES (100, '2023-01-19 15:21:03.527867', 100, 101),
       (101, '2023-01-19 15:21:03.527867', 100, 104),
       (102, '2023-01-19 15:21:03.527867', 101, 103),
       (103, '2023-01-19 15:21:03.527867', 102, 103),
       (104, '2023-01-19 15:21:03.527867', 103, 102);

INSERT INTO "tag"
("id", "description", "name", "persist_date")
VALUES (100, 'description1', 'tag with id 100', '2023-01-27 13:01:11.245126'),
       (101, 'description2', 'tag with id 101', '2023-01-27 13:01:11.245126'),
       (102, 'description3', 'tag with id 102', '2023-01-27 13:01:11.245126'),
       (103, 'description4', 'tag with id 103', '2023-01-27 13:01:11.245126'),
       (104, 'description5', 'tag with id 104', '2023-01-27 13:01:11.245126'),
       (105, 'description5', 'tag with id 105', '2023-01-27 13:01:11.245126');

INSERT INTO "question_has_tag"
("question_id", "tag_id")
VALUES (100,101),
       (101,100),
       (102,103),
       (103,104),
       (104,101),
       (105,102);

INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (100, '2023-01-27 13:01:11.245126', 'html_body1', 'false', 'false', 'true', NOW() - INTERVAL '167 hour', NOW() - INTERVAL '167 hour', 104, 100),
    (101, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 101, 103),
    (102, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days', 102, 104),
    (103, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 100, 101),
    (104, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days', 100, 102),
    (105, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 101, 103);
