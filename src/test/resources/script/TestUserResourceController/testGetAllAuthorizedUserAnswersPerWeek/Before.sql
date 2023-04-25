INSERT INTO "role"
("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');


INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted","is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk", "nickname", "password", "persist_date", "role_id")
VALUES
    (100, 'about0', 'city0', '0@gmail.com', 'fullname0', 'https://img.com/0', false, true, '2023-03-01 13:45:13.42879', 'https://github.com/0', '0.com', 'https://vk.com/0', 'nickname0', '$2a$10$i/0lEwj/muRl8KlMwOxfB.ZrWdn2wGdf2hXk0zDdv1QINHQ/06FLm', '2023-03-01 13:45:13.42879', 200),
    (101, 'about1', 'city1', '1@gmail.com', 'fullname1', 'https://img.com/1', false, true, '2023-03-01 13:45:13.496799', 'https://github.com/1', '1.com', 'https://vk.com/1', 'nickname1', '$2a$10$n407wDJOGs5GSAV/hSpTReJf3mCGQFCljB3J5P8ejto0Sxc5UNKWa', '2023-03-01 13:45:13.496799', 200),
    (102, 'about2', 'city2', '2@gmail.com', 'fullname2', 'https://img.com/2', false, true, '2023-03-01 13:45:13.563375', 'https://github.com/2', '2.com', 'https://vk.com/2', 'nickname2', '$2a$10$740WgJpe4J06777v8sMD6exrcjQyafTLNvN56ilAXxF5GqxfKqgjC', '2023-03-01 13:45:13.563375', 200),
    (103, 'about3', 'city3', '3@gmail.com', 'fullname3', 'https://img.com/3', false, true, '2023-03-01 13:45:13.631383', 'https://github.com/3', '3.com', 'https://vk.com/3', 'nickname3', '$2a$10$6vYbC2Bb8jHtY8mMmPTTo.79Y1ckReyzVQsr10CboktgsoxCxhIFO', '2023-03-01 13:45:13.631383', 200),
    (104, 'about4', 'city4', '4@gmail.com', 'fullname4', 'https://img.com/4', false, true, '2023-03-01 13:45:13.69844', 'https://github.com/4', '4.com', 'https://vk.com/4', 'nickname4', '$2a$10$9RTuQcqtHMt6J9i7ONyznelYVRCrRp6Z3zKmiUg0ulgULyjs0XIem', '2023-03-01 13:45:13.69844', 200);


INSERT INTO "question"
("id", "description", "is_deleted", "last_redaction_date", "persist_date", "title", "user_id")
VALUES (100, 'description1', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title1', 100),
       (101, 'description2', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title2', 101),
       (102, 'description3', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title3', 102),
       (103, 'description4', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title4', 103),
       (104, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 104);

INSERT INTO "tag"
("id", "description", "name", "persist_date")
VALUES (100, 'description1', 'name1', '2023-01-27 13:01:11.245126'),
       (101, 'description2', 'name2', '2023-01-27 13:01:11.245126'),
       (102, 'description3', 'name3', '2023-01-27 13:01:11.245126'),
       (103, 'description4', 'name4', '2023-01-27 13:01:11.245126'),
       (104, 'description5', 'name5', '2023-01-27 13:01:11.245126'),
       (105, 'description5', 'name6', '2023-01-27 13:01:11.245126'),
       (106, 'description5', 'name7', '2023-01-27 13:01:11.245126'),
       (107, 'description5', 'name8', '2023-01-27 13:01:11.245126'),
       (108, 'description5', 'name9', '2023-01-27 13:01:11.245126'),
       (109, 'description5', 'name10', '2023-01-27 13:01:11.245126');

INSERT INTO "question_has_tag"
("question_id", "tag_id")
VALUES (100,100),
       (100,101),
       (101,102),
       (101,103),
       (102,104),
       (102,105),
       (103,106),
       (103,107),
       (104,108),
       (104,109);

INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (100, '2023-01-27 13:01:11.245126', 'html_body1', 'false', 'false', 'true', NOW() - INTERVAL '167 hour', NOW() - INTERVAL '167 hour', 104, 100),
       (101, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 101, 103),
       (102, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days', 102, 100),
       (103, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 100, 101),
       (104, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days', 100, 100),
       (105, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 101, 100),
       (106, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days', 102, 102),
       (107, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', NOW() - INTERVAL '10 days', NOW() - INTERVAL '10 days', 100, 100),
       (108, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', NOW() - INTERVAL '1 days', NOW() - INTERVAL '1 days', 100, 100);

INSERT INTO "votes_on_answers"
("id", "persist_date", "vote", "answer_id", "user_id")
VALUES (100, '2023-01-27 13:01:11.245126', 'UP', 102, 101),
       (101, '2023-01-27 13:01:11.245126', 'UP', 102, 103),
       (102, '2023-01-27 13:01:11.245126', 'UP', 102, 102),
       (103, '2023-01-27 13:01:11.245126', 'DOWN', 102, 101),
       (104, '2023-01-27 13:01:11.245126', 'UP', 101, 100);

INSERT INTO "reputation"
("id", "count", "persist_date", "type", "answer_id", "author_id", "question_id", "sender_id")
VALUES (100, 1, '2023-01-19 15:21:03.527867', 2, 101, 100, 102, 104),
       (101, 2, '2023-01-19 15:21:03.527867', 2, 104, 100, 102, 102),
       (102, 3, '2023-01-19 15:21:03.527867', 2, 103, 100, 103, 103),
       (103, 4, '2023-01-19 15:21:03.527867', 2, 103, 101, 103, 101),
       (104, 5, '2023-01-19 15:21:03.527867', 2, 102, 102, 104, 100);

INSERT INTO "question_viewed"
("id", "persist_date", "question_id", "user_id")
VALUES (100, '2023-01-19 15:21:03.527867', 100, 101),
       (101, '2023-01-19 15:21:03.527867', 100, 104),
       (102, '2023-01-19 15:21:03.527867', 101, 103),
       (103, '2023-01-19 15:21:03.527867', 102, 103),
       (104, '2023-01-19 15:21:03.527867', 103, 102);