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
       (104, 'description5', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title5', 103),
       (105, 'description6', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title6', 100),
       (106, 'description7', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title7', 101),
       (107, 'description8', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title8', 100),
       (108, 'description9', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title9', 103),
       (109, 'description10', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title10', 102),
       (110, 'description11', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title11', 101),
       (111, 'description12', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title12', 101),
       (112, 'description13', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title13', 103),
       (113, 'description14', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title14', 102),
       (114, 'description15', 'false', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 'title15', 102);

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
       (109, 'description5', 'name10', '2023-01-27 13:01:11.245126'),
       (110, 'description5', 'name11', '2023-01-27 13:01:11.245126'),
       (111, 'description5', 'name12', '2023-01-27 13:01:11.245126'),
       (112, 'description5', 'name13', '2023-01-27 13:01:11.245126'),
       (113, 'description5', 'name14', '2023-01-27 13:01:11.245126'),
       (114, 'description5', 'name15', '2023-01-27 13:01:11.245126');

INSERT INTO "question_has_tag"
("question_id", "tag_id")
VALUES (100,100),
       (101,101),
       (102,102),
       (103,103),
       (104,104),
       (105,105),
       (106,106),
       (107,100),
       (108,101),
       (109,102),
       (100,103),
       (101,104),
       (102,105),
       (103,106),
       (104,100),
       (105,101),
       (106,102),
       (107,103),
       (108,104),
       (109,105);

INSERT INTO "answer"
("id", "date_accept_time", "html_body", "is_deleted", "is_deleted_by_moderator", "is_helpful", "persist_date", "update_date", "question_id", "user_id" )
VALUES (100, '2023-01-27 13:01:11.245126', 'html_body1', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 102, 101),
       (101, '2023-01-27 13:01:11.245126', 'html_body2', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 105, 103),
       (102, '2023-01-27 13:01:11.245126', 'html_body3', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 100, 102),
       (103, '2023-01-27 13:01:11.245126', 'html_body4', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 100, 101),
       (104, '2023-01-27 13:01:11.245126', 'html_body5', 'false', 'false', 'true', '2023-01-27 13:01:11.245126', '2023-01-27 13:01:11.245126', 100, 100);