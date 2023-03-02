
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
VALUES (100, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 100),
       (101, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 101),
       (102, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 102),
       (103, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 103),
       (104, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 104);

INSERT INTO "votes_on_questions"
("id", "persist_date", "vote", "question_id", "user_id")
VALUES (100, '2023-01-27 13:01:11.245126', 'UP_VOTE', 102, 101),
       (101, '2023-01-27 13:01:11.245126', 'UP_VOTE', 102, 103),
       (102, '2023-01-27 13:01:11.245126', 'UP_VOTE', 102, 104),
       (103, '2023-01-27 13:01:11.245126', 'DOWN_VOTE', 102, 101),
       (104, '2023-01-27 13:01:11.245126', 'UP_VOTE', 101, 103),
       (105, '2023-01-27 13:01:11.245126', 'UP_VOTE', 100, 103),
       (106, '2023-01-27 13:01:11.245126', 'UP_VOTE', 101, 102),
       (107, '2023-01-27 13:01:11.245126', 'UP_VOTE', 103, 102);


INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (100, '2023-01-27 13:01:11.245126', 'html_body1', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 104, 102),
       (101, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 101, 103),
       (102, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 102, 104),
       (103, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 100, 101),
       (104, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 100, 100);

INSERT INTO "votes_on_answers"
("id", "persist_date", "vote", "answer_id", "user_id")
VALUES (100, '2023-01-27 13:01:11.245126', 'UP_VOTE', 102, 102),
       (101, '2023-01-27 13:01:11.245126', 'UP_VOTE', 102, 103),
       (102, '2023-01-27 13:01:11.245126', 'UP_VOTE', 103, 103),
       (103, '2023-01-27 13:01:11.245126', 'DOWN_VOTE', 102, 100),
       (104, '2023-01-27 13:01:11.245126', 'DOWN_VOTE', 101, 100),
       (105, '2023-01-27 13:01:11.245126', 'DOWN_VOTE', 104, 102);

-- 103 - 5up
-- 102 - 1up
-- 104 - 3up 1d
-- 101 0
-- 100 - 2d