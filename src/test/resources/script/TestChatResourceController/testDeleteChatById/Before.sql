INSERT INTO chat (id, chat_type, persist_date, title)
values (500, 1, current_timestamp, 'java1'),
       (501, 1, current_timestamp-interval '2 seconds', 'java2'),
       (502, 0, current_timestamp-interval '2 seconds', 'java3'),
       (503, 0, current_timestamp-interval '2 seconds', 'java4');

INSERT INTO group_chat (chat_id, is_global, image_link)
VALUES (500, false, 'https://cdn-icons-png.flaticon.com/512/1144/1144760.png'),
       (501, false, 'https://cdn-icons-png.flaticon.com/512/1144/1144761.png');

INSERT INTO "role"
("id", "name")
VALUES (200, 'ROLE_USER');

INSERT INTO "user_entity"
("id", "about", "city", "email", "full_name", "image_link", "is_deleted",
 "is_enabled", "last_redaction_date", "link_github", "link_site", "link_vk",
 "nickname", "password", "persist_date", "role_id")
VALUES  (100, 'about', 'moscow', '100@gmail.com', 'name', 'http://imagelink.com', 'f', 't',
         '2023-01-10 15:17:18.280368', 'http://github.com/1', 'http://site1.com', 'http://vk.com/1',
         'nickname', '$2a$12$HOKo4IzsW3HB1.TxDhJT1.nD6PMc3eIrVVhumuOmt8DHbhr6bNWr6', '2023-01-21 15:17:18.280368', 200),
        (101, 'about1', 'moscow1', '101@gmail.com', 'name1', 'http://imagelink1.com', 'f', 't',
         '2023-01-10 15:17:18.280369', 'http://github.com/2', 'http://site2.com', 'http://vk.com/2',
         'nickname1', '$2a$12$xVskCBfOuX8mgHXjaP7uoOUj9vsz/mP6oF5nov.Imu0wXkqhuNjt6', '2023-01-21 15:17:18.280368', 200),
        (102, 'about2', 'moscow2', '102@gmail.com', 'name1', 'http://imagelink2.com', 'f', 't',
         '2023-01-10 15:17:18.280369', 'http://github.com/3', 'http://site2.com', 'http://vk.com/3',
          'nickname1', '$2a$12$xVskCBfOuX8mgHXjaP7uoOUj9vsz/mP6oF5nov.Imu0wXkqhuNjt6', '2023-01-21 15:17:18.280368', 200),
        (103, 'about3', 'moscow3', '103@gmail.com', 'name1', 'http://imagelink3.com', 'f', 't',
          '2023-01-10 15:17:18.280369', 'http://github.com/4', 'http://site2.com', 'http://vk.com/4',
          'nickname1', '$2a$12$xVskCBfOuX8mgHXjaP7uoOUj9vsz/mP6oF5nov.Imu0wXkqhuNjt6', '2023-01-21 15:17:18.280368', 200);

INSERT INTO  groupchat_has_users (chat_id, user_id)
values (500, 100), (500, 101);

INSERT INTO  single_chat (chat_id, use_two_id, user_one_id)
VALUES (502, 100, 102);

INSERT INTO  single_chat (chat_id, use_two_id, user_one_id)
VALUES (503, 101, 103);