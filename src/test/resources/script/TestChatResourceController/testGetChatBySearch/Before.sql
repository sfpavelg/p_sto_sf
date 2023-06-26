INSERT INTO "role"
("id", "name")
VALUES
    (100, 'ROLE_ADMIN'),
    (200, 'ROLE_USER');


INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted","is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk", "nickname", "password", "persist_date", "role_id")
VALUES
    (100, 'about0', 'city0', '0@gmail.com', 'fullname0', 'https://img.com/0', false, true, '2023-03-01 13:45:13.42879', 'https://github.com/0', '0.com', 'https://vk.com/0', 'nickname0', '$2a$10$i/0lEwj/muRl8KlMwOxfB.ZrWdn2wGdf2hXk0zDdv1QINHQ/06FLm', '2023-03-01 13:45:13.42879', 200),
    (101, 'about1', 'city1', '1@gmail.com', 'fullname1', 'https://img.com/1', false, true, '2023-03-01 13:45:13.496799', 'https://github.com/1', '1.com', 'https://vk.com/1', 'nickname1', '$2a$10$n407wDJOGs5GSAV/hSpTReJf3mCGQFCljB3J5P8ejto0Sxc5UNKWa', '2023-03-01 13:45:13.496799', 200),
    (102, 'about2', 'city2', '2@gmail.com', 'fullname2', 'https://img.com/2', false, true, '2023-03-01 13:45:13.563375', 'https://github.com/2', '2.com', 'https://vk.com/2', 'nickname2', '$2a$10$740WgJpe4J06777v8sMD6exrcjQyafTLNvN56ilAXxF5GqxfKqgjC', '2023-03-01 13:45:13.563375', 200),
    (103, 'about3', 'city3', '3@gmail.com', 'fullname3', 'https://img.com/3', false, true, '2023-03-01 13:45:13.631383', 'https://github.com/3', '3.com', 'https://vk.com/3', 'nickname3', '$2a$10$6vYbC2Bb8jHtY8mMmPTTo.79Y1ckReyzVQsr10CboktgsoxCxhIFO', '2023-03-01 13:45:13.631383', 200),
    (104, 'about4', 'city4', '4@gmail.com', 'fullname4', 'https://img.com/4', false, true, '2023-03-01 13:45:13.69844', 'https://github.com/4', '4.com', 'https://vk.com/4', 'nickname4', '$2a$10$9RTuQcqtHMt6J9i7ONyznelYVRCrRp6Z3zKmiUg0ulgULyjs0XIem', '2023-03-01 13:45:13.69844', 200);


INSERT INTO "chat"
("id", "chat_type", "persist_date", "title")
VALUES
    (100, '1', '2023-06-18 13:40:33.134000', 'Java'),
    (101, '1', '2023-06-18 13:40:33.134000', 'JavaScript'),
    (102, '1', '2023-06-18 13:40:33.134000', 'Global chat'),
    (103, '1', '2023-06-18 13:40:33.134000', 'html'),
    (104, '0', '2023-06-18 13:40:33.134000', 'SingleChat0'),
    (105, '0', '2023-06-18 13:40:33.134000', 'SingleChat1'),
    (106, '0', '2023-06-18 13:40:33.134000', 'SingleChat2'),
    (107, '0', '2023-06-18 13:40:33.134000', 'SingleChat3');

INSERT INTO "single_chat"
("chat_id", "use_two_id", "user_one_id")
VALUES
    (104, 100, 101),
    (105, 100, 102),
    (106, 103, 104),
    (107, 104, 100);



INSERT INTO "group_chat"
("chat_id", "is_global")
VALUES
    (100, false),
    (101, false),
    (102, true),
    (103, false);


INSERT INTO "groupchat_has_users"
("chat_id", "user_id")
VALUES
    (100, 100),
    (100, 101),
    (100, 102),
    (100, 103),
    (100, 104),
    (101, 100),
    (101, 101),
    (101, 102),
    (101, 103),
    (101, 104),
    (102, 100),
    (102, 101),
    (102, 102),
    (102, 103),
    (102, 104);


INSERT INTO "message"
("id", "last_redaction_date", "message", "persist_date", "chat_id", "user_sender_id")
VALUES
(100, '2023-06-18 13:40:33.174000', 'Hello 0', '2023-06-18 13:42:33.176000', 100, 100),
(101, '2023-06-18 13:40:33.174000', 'Hello 1', '2023-06-18 13:41:33.174000', 101, 101),
(102, '2023-06-18 13:40:33.174000', 'Hello 2', '2023-06-18 13:40:33.174000', 102, 102),
(103, '2023-06-18 13:40:33.174000', 'Hello 100id', '2023-06-18 15:50:33.174000', 104, 101),
(104, '2023-06-18 13:40:33.174000', 'Hello 100id, i am 102id', '2023-06-18 14:44:33.174000', 105, 102),
(105, '2023-06-18 13:40:33.174000', 'Hello 103id', '2023-06-18 13:40:33.174000', 106, 104),
(106, '2023-06-18 13:40:33.174000', 'Hello 104id', '2023-06-18 13:40:33.174000', 107, 100);