INSERT INTO "role" ("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'moscow', '100@gmail.com', 'name1', 'http://imagelink1.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$10$0vg6ZguXVNVNv/03ZdwYdeRmejNttPdwhTI7TW/BWOXbr39Dcps7y', '2023-01-21 15:17:18.280368', 200),
       (101, 'about', 'spb', '101@gmail.com', 'name2', 'http://imagelink2.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', '$2a$10$0vg6ZguXVNVNv/03ZdwYdeRmejNttPdwhTI7TW/BWOXbr39Dcps7y', '2023-01-21 15:17:18.280368', 200),
       (102, 'about', 'NY', '102@gmail.com', 'name3', 'http://imagelink3.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', '$2a$10$0vg6ZguXVNVNv/03ZdwYdeRmejNttPdwhTI7TW/BWOXbr39Dcps7y', '2023-01-21 15:17:18.280368', 200),
       (103, 'about', 'spb', '103@gmail.com', 'name4', 'http://imagelink4.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', '$2a$10$0vg6ZguXVNVNv/03ZdwYdeRmejNttPdwhTI7TW/BWOXbr39Dcps7y', '2023-01-21 15:17:18.280368', 200),
       (104, 'about', 'spb', '104@gmail.com', 'name5', null, 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', '$2a$10$0vg6ZguXVNVNv/03ZdwYdeRmejNttPdwhTI7TW/BWOXbr39Dcps7y', '2023-01-21 15:17:18.280368', 200),
       (105, 'about', 'spb', '105@gmail.com', 'name6', null, 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/6', 'http://site6.com', 'http://vk.com/6',
        'nickname6', '$2a$10$0vg6ZguXVNVNv/03ZdwYdeRmejNttPdwhTI7TW/BWOXbr39Dcps7y', '2023-01-21 15:17:18.280368', 200);

INSERT INTO "comment"
("id", "comment_type", "last_redaction_date", "persist_date", "text", "user_id")
VALUES (100, 1, '2023-01-19 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment1', 100),
       (101, 1, '2023-01-19 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment2', 101),
       (102, 1, '2023-01-19 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment3', 102),
       (103, 1, '2023-01-19 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment4', 103),
       (104, 1, '2023-01-20 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment5', 100),
       (105, 1, '2023-01-20 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment6', 101),
       (106, 0, '2023-01-20 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment7', 102),
       (107, 0, '2023-01-20 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment8', 103),
       (108, 0, '2023-01-21 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment9', 100),
       (109, 0, '2023-01-21 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment10', 101),
       (110, 0, '2023-01-21 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment11', 102),
       (111, 0, '2023-01-20 15:21:03.527867', '2023-01-19 15:21:03.527866', 'comment12', 103);

INSERT INTO  "question" (id, description, is_deleted, last_redaction_date, persist_date, title, user_id)
VALUES (100, 'Asked by fullname24', false, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245125', 'nickname24''s question', 100),
       (101, 'Asked by fullname24', false, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245125', 'nickname24''s question', 101),
       (102, 'Asked by fullname24', false, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245125', 'nickname24''s question', 102),
       (103, 'Asked by fullname24', false, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245125', 'nickname24''s question', 103),
       (104, 'Asked by fullname24', false, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245125', 'nickname24''s question', 104),
       (105, 'Asked by fullname24', false, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245125', 'nickname24''s question', 100);

INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (100, '2023-01-27 13:01:11.245126', 'html_body1', 'false', 'false', 'true', NOW() - INTERVAL '167 hour', NOW() - INTERVAL '167 hour', 104, 100),
       (101, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 101, 103),
       (102, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days', 102, 104),
       (103, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 100, 101),
       (104, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days', 100, 102),
       (105, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 101, 103);


INSERT INTO "comment_answer" (comment_id, answer_id)
VALUES (100, 100),
       (101, 101),
       (102, 102),
       (103, 103),
       (105, 100),
       (108, 105);

INSERT INTO "comment_question" (comment_id, question_id)

VALUES (100,  101),
       (101,  104),
       (102,  103),
       (103,  103),
       (104,  102);




