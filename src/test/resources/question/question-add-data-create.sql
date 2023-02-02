
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
        'nickname2', 'password', '2023-01-21 15:17:18.280368', 2),
       (3, 'about', 'NY', 'email3@domain.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', 'password', '2023-01-21 15:17:18.280368', 1),
       (4, 'about', 'spb', 'email4@domain.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', 'password', '2023-01-21 15:17:18.280368', 2),
       (5, 'about', 'spb', 'email5@domain.com', 'name5', null, 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', 'password', '2023-01-21 15:17:18.280368', 1);

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (1, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 1),
       (2, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 2),
       (3, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 3),
       (4, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 4),
       (5, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 5);
--
INSERT INTO "tag"
("id", "description", "name", "persist_date")
VALUES (1, 'description1', 'name1', '2023-01-27 13:01:11.245126'),
       (2, 'description2', 'name2', '2023-01-27 13:01:11.245126'),
       (3, 'description3', 'name3', '2023-01-27 13:01:11.245126'),
       (4, 'description4', 'name4', '2023-01-27 13:01:11.245126'),
       (5, 'description5', 'name5', '2023-01-27 13:01:11.245126'),
       (6, 'description5', 'name6', '2023-01-27 13:01:11.245126'),
       (7, 'description5', 'name7', '2023-01-27 13:01:11.245126'),
       (8, 'description5', 'name8', '2023-01-27 13:01:11.245126'),
       (9, 'description5', 'name9', '2023-01-27 13:01:11.245126'),
       (10, 'description5', 'name10', '2023-01-27 13:01:11.245126');
--
-- INSERT INTO "question_has_tag"
-- ("question_id", "tag_id")
-- VALUES (1,1),
--        (1,2),
--        (2,3),
--        (2,4),
--        (3,5),
--        (3,6),
--        (4,7),
--        (4,8),
--        (4,9),
--        (4,10);
--
INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (1, '2023-01-27 13:01:11.245126', 'html_body1', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 3, 2),
       (2, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 2, 4),
       (3, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1, 3),
       (4, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1, 2),
       (5, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1, 1);

INSERT INTO "votes_on_questions"
("id", "persist_date", "vote", "question_id", "user_id")
VALUES (1, '2023-01-27 13:01:11.245126', 'up', 1, 2),
       (2, '2023-01-27 13:01:11.245126', 'up', 1, 4),
       (3, '2023-01-27 13:01:11.245126', 'up', 1, 3),
       (4, '2023-01-27 13:01:11.245126', 'down', 1, 2),
       (5, '2023-01-27 13:01:11.245126', 'up', 3, 1);

INSERT INTO "reputation"
("id", "count", "persist_date", "type", "answer_id", "author_id", "question_id", "sender_id")
VALUES (1, 1, '2023-01-19 15:21:03.527867', 2, 2, 1, 3, 5),
       (2, 2, '2023-01-19 15:21:03.527867', 2, 5, 1, 3, 3),
       (3, 3, '2023-01-19 15:21:03.527867', 2, 4, 1, 4, 4),
       (4, 4, '2023-01-19 15:21:03.527867', 2, 4, 2, 4, 2),
       (5, 5, '2023-01-19 15:21:03.527867', 2, 3, 3, 3, 1);

INSERT INTO "question_viewed"
("id", "persist_date", "question_id", "user_id")
VALUES (1, '2023-01-19 15:21:03.527867', 1, 2),
       (2, '2023-01-19 15:21:03.527867', 1, 5),
       (3, '2023-01-19 15:21:03.527867', 2, 4),
       (4, '2023-01-19 15:21:03.527867', 3, 4),
       (5, '2023-01-19 15:21:03.527867', 4, 3);



