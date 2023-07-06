INSERT INTO chat (id, chat_type, persist_date, title)
VALUES (100, 1, current_timestamp, 'GroupChat100'),
       (101, 1, current_timestamp + interval '1 second', 'GroupChat101'),
       (102, 1, current_timestamp + interval '2 seconds', 'GroupChat102');

INSERT INTO group_chat (chat_id, is_global, image_link)
VALUES (100, true, 'http://images/image0.jpeg'),
       (101, true, 'http://images/image1.jpeg'),
       (102, true, 'http://images/image2.jpeg');

INSERT INTO "role"
    ("id", "name")
VALUES (100, 'ROLE_ADMIN'),
       (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES (100, 'about', 'Moscow', 'email0@domain.com', 'name0', 'http://imagelink0.com', 'f', 't',
        '2023-01-10 15:17:18.280368', 'http://github.com/0', 'http://site0.com', 'http://vk.com/0',
        'nickname0', '$2a$12$3f.eGtoIxp./nblMrT3P8uXH0E7OYskjT8NUQailLm4yLNKkgZALa', '2023-01-21 15:17:18.280368', 200);
