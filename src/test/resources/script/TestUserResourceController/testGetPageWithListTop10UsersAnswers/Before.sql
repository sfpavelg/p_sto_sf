INSERT INTO "role"
("id", "name")
VALUES  (100, 'ROLE_ADMIN'),
        (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "city", "email", "full_name", "image_link", "is_deleted","is_enabled", "last_redaction_date", "password", "persist_date", "role_id")
VALUES  (100, 'city0', 'super0@gmail.com', 'superfullname0', 'https://img.com/0', false, true, '2023-03-01 13:45:13.087177', '$2a$10$CedsmtFiFcjwTXltVyPaS.BDiR8BHgu3ibBAE.jvo0s3Skd69LMfm', '2023-03-01 13:45:13.087177', 100),
        (101, 'city1', 'super1@gmail.com', 'superfullname1', 'https://img.com/1', false, true, '2023-03-01 13:45:13.157876', '$2a$10$tzt5G/kpUYKwW1J5poHcG.eOJFnuUWk336HgmLYYqUMAaLsteGj/W', '2023-03-01 13:45:13.157876', 100),
        (102, 'city2', 'super2@gmail.com', 'superfullname2', 'https://img.com/2', false, true, '2023-03-01 13:45:13.228975', '$2a$10$ik1ZSY50/K1610/r1uPCaOqeUk5NS25g9iJbZP2uJ0SyhWjz5Cdq6', '2023-03-01 13:45:13.228975', 100),
        (103, 'city3', 'super3@gmail.com', 'superfullname3', 'https://img.com/3', false, true, '2023-03-01 13:45:13.296487', '$2a$10$137l1tGJAY.cjRAOTT.ATO1dNzezqGtcatZFX21Iqa/1NdStQcUMC', '2023-03-01 13:45:13.296487', 100),
        (104, 'city4', 'super4@gmail.com', 'superfullname4', 'https://img.com/4', false, true, '2023-03-01 13:45:13.362387', '$2a$10$DxNa.GJfhJsSDgjI1exiiOE8v9fp6xty.wAOi8wC0jE3bZtuAkvLm', '2023-03-01 13:45:13.362387', 100),
        (105, 'city0', '0@gmail.com', 'fullname0', 'https://img.com/0', false, true, '2023-03-01 13:45:13.42879',  '$2a$10$i/0lEwj/muRl8KlMwOxfB.ZrWdn2wGdf2hXk0zDdv1QINHQ/06FLm', '2023-03-01 13:45:13.42879', 200),
        (106, 'city1', '1@gmail.com', 'fullname1', 'https://img.com/1', false, true, '2023-03-01 13:45:13.496799', '$2a$10$n407wDJOGs5GSAV/hSpTReJf3mCGQFCljB3J5P8ejto0Sxc5UNKWa', '2023-03-01 13:45:13.496799', 200),
        (107, 'city2', '2@gmail.com', 'fullname2', 'https://img.com/2', false, true, '2023-03-01 13:45:13.563375', '$2a$10$740WgJpe4J06777v8sMD6exrcjQyafTLNvN56ilAXxF5GqxfKqgjC', '2023-03-01 13:45:13.563375', 200),
        (108, 'city3', '3@gmail.com', 'fullname3', 'https://img.com/3', false, true, '2023-03-01 13:45:13.631383', '$2a$10$6vYbC2Bb8jHtY8mMmPTTo.79Y1ckReyzVQsr10CboktgsoxCxhIFO', '2023-03-01 13:45:13.631383', 200),
        (109, 'city4', '4@gmail.com', 'fullname4', 'https://img.com/4', false, true, '2023-03-01 13:45:13.69844', '$2a$10$9RTuQcqtHMt6J9i7ONyznelYVRCrRp6Z3zKmiUg0ulgULyjs0XIem', '2023-03-01 13:45:13.69844', 200),
        (110, 'city5', '5@gmail.com', 'fullname5', 'https://img.com/5', false, true, '2023-03-01 13:45:13.76366', '$2a$10$STxp2IlLSM1EJYG3/1ynuemJe7wvROVBiVJLrkW7GnLGikLW1IXxC', '2023-03-01 13:45:13.76366', 200),
        (111, 'city6', '6@gmail.com', 'fullname6', 'https://img.com/6', false, true, '2023-03-01 13:45:13.829824', '$2a$10$NcWcojN/unz5RiMB24xc7upzmvIqt9AKvJUIzROE2ORbtx/nmvLk6', '2023-03-01 13:45:13.829824', 200),
        (112, 'city7', '7@gmail.com', 'fullname7', 'https://img.com/7', false, true, '2023-03-01 13:45:13.895387', '$2a$10$NxRBiFOwawU0LvqQNK6SqOGdYEKO1fG8Yt3/GdQ.eHoHePC8waOGq', '2023-03-01 13:45:13.895387', 200),
        (113, 'city8', '8@gmail.com', 'fullname8', 'https://img.com/8', false, true, '2023-03-01 13:45:13.960519', '$2a$10$nkvFZqciuEK.0Sn8vv8qjONxGycsoST8FRhco9VBJ0iX2vbIjcTk.', '2023-03-01 13:45:13.960519', 200),
        (114, 'city9', '9@gmail.com', 'fullname9', 'https://img.com/9', false, true, '2023-03-01 13:45:14.026903', '$2a$10$lav1TEyEmcHFj5lByiRrP.ElP5qRaynEDiPlkIRwprqGTHQGagf4i', '2023-03-01 13:45:14.026903', 200);

INSERT INTO "reputation"
("id", "count", "type", "author_id")
VALUES (100, 0, 1, 100),
       (101, 1, 1, 101),
       (102, 2, 1, 102),
       (103, 3, 1, 103),
       (104, 4, 1, 104),
       (105, 5, 1, 105),
       (106, 6, 1, 106),
       (107, 7, 1, 107),
       (108, 8, 1, 108),
       (109, 9, 1, 109),
       (110, 10, 1, 110),
       (111, 11, 1, 111),
       (112, 12, 1, 112),
       (113, 13, 1, 113),
       (114, 14, 1, 114);

INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (100, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 100),
       (101, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 101),
       (102, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 102),
       (103, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 103),
       (104, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 104);

INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (100, '2023-01-27 13:01:11.245126', 'html_body1', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '2 days', 104, 100),
       (101, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '1 days', NOW() - INTERVAL '2 days', 101, 100),
       (102, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '5 days', 102, 100),
       (103, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '2 days', 100, 101),
       (104, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '5 days', 100, 101),
       (105, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '6 days', NOW() - INTERVAL '2 days', 101, 101),
       (106, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '2 days', 102, 102),
       (107, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '9 days', 100, 102),
       (108, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '1 days', 100, 103),
       (109, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '3 days', 100, 103),
       (110, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '4 days', 100, 114),
       (111, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '9 days', NOW() - INTERVAL '6 days', 100, 114),
       (112, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '1 days', 100, 108),
       (113, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '3 days', 100, 109),
       (114, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '9 days', NOW() - INTERVAL '4 days', 100, 110),
       (115, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '4 days', 100, 114),
       (116, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '9 days', NOW() - INTERVAL '6 days', 100, 113),
       (117, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '1 days', 100, 112),
       (118, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '3 days', NOW() - INTERVAL '3 days', 100, 111),
       (119, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', CURRENT_TIMESTAMP - INTERVAL '5 days', NOW() - INTERVAL '4 days', 100, 106);


INSERT INTO "votes_on_answers"
("id", "persist_date", "vote", "answer_id", "user_id")
VALUES (100, '2023-01-27 13:01:11.245126', 'UP', 100, 101),
       (101, '2023-01-27 13:01:11.245126', 'UP', 104, 103),
       (102, '2023-01-27 13:01:11.245126', 'UP', 102, 102),
       (103, '2023-01-27 13:01:11.245126', 'DOWN', 102, 101),
       (104, '2023-01-27 13:01:11.245126', 'UP', 105, 100),
       (105, '2023-01-27 13:01:11.245126', 'UP', 102, 101),
       (106, '2023-01-27 13:01:11.245126', 'UP', 106, 101),
       (107, '2023-01-27 13:01:11.245126', 'DOWN', 109, 101),
       (108, '2023-01-27 13:01:11.245126', 'UP', 110, 101),
       (109, '2023-01-27 13:01:11.245126', 'DOWN', 111, 101),
       (110, '2023-01-27 13:01:11.245126', 'UP', 103, 101),
       (111, '2023-01-27 13:01:11.245126', 'UP', 103, 101),
       (112, '2023-01-27 13:01:11.245126', 'DOWN', 108, 101),
       (113, '2023-01-27 13:01:11.245126', 'DOWN', 109, 101),
       (114, '2023-01-27 13:01:11.245126', 'UP', 107, 101);