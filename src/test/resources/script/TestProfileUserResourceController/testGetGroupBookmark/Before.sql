INSERT INTO "role"
("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted","is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk", "nickname", "password", "persist_date", "role_id")
VALUES
    (100, 'about0', 'city0', '0@gmail.com', 'name0', 'https://img.com/0', false, true, '2023-03-01 13:45:13.42879', 'https://github.com/0', '0.com', 'https://vk.com/0', 'nickname0', '$2a$10$i/0lEwj/muRl8KlMwOxfB.ZrWdn2wGdf2hXk0zDdv1QINHQ/06FLm', '2023-03-01 13:45:13.42879', 200),
    (101, 'about1', 'city1', '1@gmail.com', 'name1', 'https://img.com/1', false, true, '2023-03-01 13:45:13.496799', 'https://github.com/1', '1.com', 'https://vk.com/1', 'nickname1', '$2a$10$n407wDJOGs5GSAV/hSpTReJf3mCGQFCljB3J5P8ejto0Sxc5UNKWa', '2023-03-01 13:45:13.496799', 200),
    (102, 'about2', 'city2', '2@gmail.com', 'name2', 'https://img.com/2', false, true, '2023-03-01 13:45:13.563375', 'https://github.com/2', '2.com', 'https://vk.com/2', 'nickname2', '$2a$10$740WgJpe4J06777v8sMD6exrcjQyafTLNvN56ilAXxF5GqxfKqgjC', '2023-03-01 13:45:13.563375', 200),
    (103, 'about3', 'city3', '3@gmail.com', 'name3', 'https://img.com/3', false, true, '2023-03-01 13:45:13.631383', 'https://github.com/3', '3.com', 'https://vk.com/3', 'nickname3', '$2a$10$6vYbC2Bb8jHtY8mMmPTTo.79Y1ckReyzVQsr10CboktgsoxCxhIFO', '2023-03-01 13:45:13.631383', 200),
    (104, 'about4', 'city4', '4@gmail.com', 'name4', 'https://img.com/4', false, true, '2023-03-01 13:45:13.69844', 'https://github.com/4', '4.com', 'https://vk.com/4', 'nickname4', '$2a$10$9RTuQcqtHMt6J9i7ONyznelYVRCrRp6Z3zKmiUg0ulgULyjs0XIem', '2023-03-01 13:45:13.69844', 200);

INSERT INTO "group_bookmark"
("id", "title", "user_id")
VALUES
(100, 'title_100', 100),
(101, 'title_101', 100),
(102, 'title_102', 101),
(103, 'title_103', 101),
(104, 'title_104', 101);

