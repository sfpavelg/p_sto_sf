INSERT INTO "role"
("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');


INSERT INTO "user_entity"
("id", "email", "full_name", "is_deleted", "is_enabled", "last_redaction_date", "nickname", "password", "persist_date", "role_id")
VALUES (100, '0@gmail.com', 'name100', 'f', 't',
        '2023-01-10 15:17:18.280368', 'nickname100', '$2a$10$/Hq12PgE.XuzB3ZizNOIXuBnCjyK/9/oHJ1Y/FRPbJZO8jSBzD1F.', '2023-01-21 15:17:18.280368', 200),
       (101, 'email2@domain.com', 'name101', 'f', 't',
        '2023-01-10 15:17:18.280368', 'nickname101', 'password', '2023-01-21 15:17:18.280368', 200),
       (102, 'email3@domain.com', 'name102', 'f', 't',
        '2023-01-10 15:17:18.280368', 'nickname102', 'password', '2023-01-21 15:17:18.280368', 100),
       (103, 'email4@domain.com', 'name103', 'f', 't',
        '2023-01-10 15:17:18.280368', 'nickname103', 'password', '2023-01-21 15:17:18.280368', 200),
       (104, 'email5@domain.com', 'name104', 'f', 't',
        '2023-01-10 15:17:18.280368', 'nickname104', 'password', '2023-01-21 15:17:18.280368', 100);

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (100, 'description100', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP, 'title100', 100),
       (101, 'description101', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP - INTERVAL '3 days', 'title101', 101),
       (102, 'description102', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP, 'title102', 102),
       (103, 'description103', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP - INTERVAL '3 days', 'title103', 103),
       (104, 'description104', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP, 'title104', 104),
       (105, 'description105', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP - INTERVAL '3 days', 'title105', 100),
       (106, 'description106', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP, 'title106', 101),
       (107, 'description107', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP - INTERVAL '3 days', 'title107', 102),
       (108, 'description108', 'false', '2023-01-27 13:01:11.245126', CURRENT_TIMESTAMP, 'title108', 103);

INSERT INTO "tag"
("id", "description", "name", "persist_date")
VALUES (100, 'description100', 'name1', CURRENT_TIMESTAMP - INTERVAL '9 days'),
       (101, 'description101', 'name2', CURRENT_TIMESTAMP - INTERVAL '8 days'),
       (102, 'description102', 'name3', CURRENT_TIMESTAMP - INTERVAL '7 days'),
       (103, 'description103', 'name5', CURRENT_TIMESTAMP - INTERVAL '6 days'),
       (104, 'description104', 'name4', CURRENT_TIMESTAMP - INTERVAL '5 days'),
       (105, 'description105', 'name6', CURRENT_TIMESTAMP - INTERVAL '4 days'),
       (106, 'description106', 'name7', CURRENT_TIMESTAMP - INTERVAL '3 days'),
       (107, 'description107', 'name9', CURRENT_TIMESTAMP - INTERVAL '2 days'),
       (108, 'description108', 'name8', CURRENT_TIMESTAMP - INTERVAL '1 days');

INSERT INTO "question_has_tag"
("question_id", "tag_id")
VALUES (100,100),
       (101,101),
       (102,101),
       (103,102),
       (104,102),
       (105,102),
       (106,103),
       (107,103),
       (108,103),
       (100,104),
       (101,104),
       (102,104),
       (103,104),
       (104,105),
       (105,106),
       (106,107),
       (107,108),
       (108,108);