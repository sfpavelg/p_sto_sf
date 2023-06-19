
INSERT INTO "role"
("id", "name")
VALUES (1000, 'ROLE_ADMIN'),
       (2000, 'ROLE_USER');


INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (1000, 'about', 'moscow', '1000@gmail.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$10$z6zd/raH0e0ErNqkGzunbO5i6F8H7vOHgQXtJR5t75HCryqLVYoMK', '2023-01-21 15:17:18.280368', 2000),
       (1001, 'about', 'spb', '1001@gmail.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', '$2a$10$z6zd/raH0e0ErNqkGzunbO5i6F8H7vOHgQXtJR5t75HCryqLVYoMK', '2023-01-21 15:17:18.280368', 2000),
       (1002, 'about', 'NY', '1002@gmail.com', 'name3', null, 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', '$2a$10$z6zd/raH0e0ErNqkGzunbO5i6F8H7vOHgQXtJR5t75HCryqLVYoMK', '2023-01-21 15:17:18.280368', 2000),
       (1003, 'about', 'spb', '1003@gmail.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$10$z6zd/raH0e0ErNqkGzunbO5i6F8H7vOHgQXtJR5t75HCryqLVYoMK', '2023-01-21 15:17:18.280368', 2000),
       (1004, 'about', 'spb', '1004@gmail.com', 'name5', 'http://imagelink5.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$10$z6zd/raH0e0ErNqkGzunbO5i6F8H7vOHgQXtJR5t75HCryqLVYoMK', '2023-01-21 15:17:18.280368', 1000);

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (1000, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 1000),
       (1001, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 1001),
       (1002, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 1002),
       (1003, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 1003),
       (1004, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 1004);

INSERT INTO "tag"
("id", "description", "name", "persist_date")
VALUES (1000, 'description1', 'name1', '2023-01-27 13:01:11.245126'),
       (1001, 'description2', 'name2', '2023-01-27 13:01:11.245126'),
       (1002, 'description3', 'name3', '2023-01-27 13:01:11.245126'),
       (1003, 'description4', 'name4', '2023-01-27 13:01:11.245126'),
       (1004, 'description5', 'name5', '2023-01-27 13:01:11.245126'),
       (1005, 'description5', 'name6', '2023-01-27 13:01:11.245126'),
       (1006, 'description5', 'name7', '2023-01-27 13:01:11.245126'),
       (1007, 'description5', 'name8', '2023-01-27 13:01:11.245126'),
       (1008, 'description5', 'name9', '2023-01-27 13:01:11.245126'),
       (1009, 'description5', 'name10', '2023-01-27 13:01:11.245126');

INSERT INTO "question_has_tag"
("question_id", "tag_id")
VALUES (1000,1000),
       (1000,1001),
       (1001,1002),
       (1001,1003),
       (1002,1004),
       (1002,1005),
       (1003,1006),
       (1003,1007),
       (1004,1008),
       (1004,1009);

INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (1000, '2023-01-27 13:01:11.245126', 'html_body1', 'true', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1004, 1002),
       (1001, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1001, 1003),
       (1002, '2023-01-27 13:01:11.245126', 'html_body3', 'true', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1002, 1004),
       (1003, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1000, 1001),
       (1004, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 1000, 1000);

INSERT INTO "votes_on_answers"
("id", "persist_date", "vote", "answer_id", "user_id")
VALUES (1000, '2023-01-27 13:01:11.245126', 'UP_VOTE', 1002, 1001),
       (1001, '2023-01-27 13:01:11.245126', 'UP_VOTE', 1002, 1002),
       (1002, '2023-01-27 13:01:11.245126', 'UP_VOTE', 1002, 1002),
       (1003, '2023-01-27 13:01:11.245126', 'DOWN_VOTE', 1002, 1001),
       (1004, '2023-01-27 13:01:11.245126', 'UP_VOTE', 1001, 1000);

INSERT INTO "votes_on_questions"
("id", "persist_date", "vote", "question_id", "user_id")
VALUES (1000, (NOW()), 'UP_VOTE', 1002, 1001),
       (1001, '2023-01-27 13:01:11.245126', 'UP_VOTE', 1002, 1002),
       (1002, '2023-01-27 13:01:11.245126', 'UP_VOTE', 1002, 1002),
       (1003, '2023-01-27 13:01:11.245126', 'DOWN_VOTE', 1002, 1001),
       (1004, '2023-01-27 13:01:11.245126', 'UP_VOTE', 1001, 1000);

INSERT INTO "reputation"
("id", "count", "persist_date", "type", "answer_id", "author_id", "question_id", "sender_id")
VALUES (1000, 1, '2023-01-19 15:21:03.527867', 2, 1001, 1000, 1002, 1004),
       (1001, 2, '2023-01-19 15:21:03.527867', 2, 1004, 1000, 1002, 1002),
       (1002, 3, '2023-01-19 15:21:03.527867', 2, 1003, 1000, 1003, 1003),
       (1003, 4, '2023-01-19 15:21:03.527867', 2, 1003, 1001, 1003, 1001),
       (1004, 5, '2023-01-19 15:21:03.527867', 2, 1002, 1002, 1004, 1000);

INSERT INTO "question_viewed"
("id", "persist_date", "question_id", "user_id")
VALUES (1000, '2023-01-19 15:21:03.527867', 1000, 1001),
       (1001, '2023-01-19 15:21:03.527867', 1000, 1004),
       (1002, '2023-01-19 15:21:03.527867', 1001, 1003),
       (1003, '2023-01-19 15:21:03.527867', 1002, 1003),
       (1004, '2023-01-19 15:21:03.527867', 1003, 1002);

