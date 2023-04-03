INSERT INTO "role"
("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'Moscow', '0@gmail.com', 'name100', 'http://imagelink100.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
        'nickname1', '$2a$10$/Hq12PgE.XuzB3ZizNOIXuBnCjyK/9/oHJ1Y/FRPbJZO8jSBzD1F.', '2023-01-21 15:17:18.280368', 200),
       (101, 'about', 'St. Petersburg', '2@domain.com', 'name101', 'http://imagelink101.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site2.com', 'http://vk.com/2',
        'nickname2', 'password', '2023-01-21 15:17:18.280368', 200),
       (102, 'about', 'Kazan', '3@domain.com', 'name102', 'http://imagelink102.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/3', 'http://site3.com', 'http://vk.com/3',
        'nickname3', 'password', '2023-01-21 15:17:18.280368', 100),
       (103, 'about', 'Ekaterinburg', '4@domain.com', 'name103', 'http://imagelink103.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/4', 'http://site4.com', 'http://vk.com/4',
        'nickname4', 'password', '2023-01-21 15:17:18.280368', 200),
       (104, 'about', 'Samara', '5@domain.com', 'name104', 'http://imagelink104.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/5', 'http://site5.com', 'http://vk.com/5',
        'nickname5', 'password', '2023-01-21 15:17:18.280368', 100);

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (100, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 100),
       (101, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 101),
       (102, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 102),
       (103, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 103),
       (104, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 104);

INSERT INTO "comment"
("id", "comment_type", "last_redaction_date", "persist_date", "text", "user_id")
VALUES (100, 1, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'Comment on the question nickname nickname104',  101),
       (101, 1, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'Comment on the question nickname nickname104',  102),
       (102, 1, '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'Comment on the question nickname nickname104',  103);

INSERT INTO "reputation"
("id", "count", "type", "author_id")
VALUES
    (100, 1, 1, 100),
    (102, 2, 1, 101),
    (103, 3, 1, 103),
    (104, 4, 1, 102),
    (105, 2, 1, 101),
    (106, 3, 1, 100),
    (107, 1, 1, 103),
    (108, 1, 1, 104);


INSERT INTO "comment_question"
("comment_id",  "question_id")
VALUES (100, 104),
       (101, 104),
       (102, 104);